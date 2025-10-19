package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A parser that converts a raw string into a structured {@link Text} object.
 * It uses a stateful approach to build sentences and words character by character.
 * This class is designed to be reusable but is not thread-safe.
 */
public class TextParser {

    /** Stores all completed sentences found during parsing. */
    private List<Sentence> allSentences;

    /** Stores {@link SentenceElement} objects for the sentence currently being built. */
    private List<SentenceElement> currentSentenceElements;

    /** Accumulates characters to form the word currently being built. */
    private StringBuilder currentWordBuilder;

    /**
     * Parses a raw string into a structured {@link Text} object. This is the main entry point.
     * The method first normalizes the input text, then processes it character by character
     * to build a hierarchical model of the text.
     *
     * @param rawText The input string to parse.
     * @return A {@link Text} object representing the structured content of the input string.
     * @throws IllegalArgumentException if the {@code rawText} is null or empty.
     */
    public Text parse(String rawText) {
        if (rawText == null || rawText.isBlank()) {
            throw new IllegalArgumentException("Input text cannot be null, empty, or blank.");
        }

        String normalizedText = normalizeText(rawText);

        // Initialize state for the new parsing operation
        this.allSentences = new ArrayList<>();
        this.currentSentenceElements = new ArrayList<>();
        this.currentWordBuilder = new StringBuilder();

        for (char c : normalizedText.toCharArray()) {
            processCharacter(c);
        }

        // Finalize parsing by adding any remaining elements
        addRemainingElements();

        return new Text(allSentences);
    }

    /**
     * Normalizes the input string by trimming leading/trailing whitespace and
     * replacing any sequence of tabs and spaces with a single space.
     *
     * @param text The raw text string.
     * @return A clean, normalized string ready for parsing.
     */
    private String normalizeText(String text) {
        return text.trim().replaceAll("[\\t ]+", " ");
    }

    /**
     * Processes a single character from the text. It determines if the character
     * is part of a word and delegates to the appropriate handler method.
     *
     * @param currentChar The character to process.
     */
    private void processCharacter(char currentChar) {
        if (isWordCharacter(currentChar)) {
            currentWordBuilder.append(currentChar);
        } else {
            handleNonWordCharacter(currentChar);
        }
    }

    /**
     * Handles a character that is not a part of a word (e.g., punctuation or space).
     * This method first flushes any pending word, then adds the punctuation mark,
     * and finally checks if this mark terminates the current sentence.
     *
     * @param currentChar The non-word character to handle.
     */
    private void handleNonWordCharacter(char currentChar) {
        flushWord(); // Save the word that came before this punctuation
        currentSentenceElements.add(new Punctuation(currentChar));

        if (isSentenceTerminator(currentChar)) {
            flushSentence(); // If it's a sentence-ending punctuation, save the sentence
        }
    }

    /**
     * Checks if a word is currently being built in {@code currentWordBuilder}.
     * If so, it creates a new {@link Word} object, adds it to the current sentence's elements,
     * and resets the builder to start a new word.
     */
    private void flushWord() {
        if (currentWordBuilder.length() > 0) {
            currentSentenceElements.add(new Word(currentWordBuilder.toString()));
            currentWordBuilder.setLength(0); // Reset for the next word
        }
    }

    /**
     * Checks if the current sentence has any elements. If so, it creates a new
     * {@link Sentence} object, adds it to the main list of sentences, and resets the
     * list of elements to begin a new sentence.
     */
    private void flushSentence() {
        if (!currentSentenceElements.isEmpty()) {
            allSentences.add(new Sentence(currentSentenceElements));
            currentSentenceElements = new ArrayList<>(); // Start a new empty sentence
        }
    }

    /**
     * A cleanup method called after the main parsing loop finishes. It ensures that any
     * remaining word or sentence that wasn't terminated by punctuation is properly
     * saved to the text structure.
     */
    private void addRemainingElements() {
        flushWord();      // Save the very last word if it exists.
        flushSentence();  // Save the very last sentence.
    }

    /**
     * Utility method to check if a character is considered part of a word.
     * In this context, a word character is a letter, a digit, or an apostrophe.
     *
     * @param currentChar The character to check.
     * @return {@code true} if the character is part of a word, {@code false} otherwise.
     */
    private boolean isWordCharacter(char currentChar) {
        return Character.isLetterOrDigit(currentChar) || currentChar == '\'' || currentChar == '’';
    }

    /**
     * Utility method to check if a character is a sentence terminator.
     *
     * @param currentChar The character to check.
     * @return {@code true} if the character is a period, exclamation mark, or question mark.
     */
    private boolean isSentenceTerminator(char currentChar) {
        return currentChar == '.' || currentChar == '!' || currentChar == '?';
    }
}
