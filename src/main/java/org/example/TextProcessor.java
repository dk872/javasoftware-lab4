package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * A class containing methods to process a structured Text object.
 * This class performs non-destructive operations on the text by manipulating its component objects.
 */
public class TextProcessor {

    /**
     * Replaces all words of a specified length in a {@link Text} object with a given replacement word.
     * This method orchestrates the validation and processing, returning a new Text object.
     *
     * @param text The input Text object to process.
     * @param length The length of words to be replaced.
     * @param replacement The Word to use as a replacement.
     * @return A new {@link Text} object with the specified words replaced.
     * @throws IllegalArgumentException if any of the inputs are invalid.
     */
    public Text replaceWordsOfLength(Text text, int length, Word replacement) {
        validateInputs(text, length, replacement);

        List<Sentence> processedSentences = new ArrayList<>();
        for (Sentence originalSentence : text.getSentences()) {
            Sentence processedSentence = processSentence(originalSentence, length, replacement);
            processedSentences.add(processedSentence);
        }

        return new Text(processedSentences);
    }

    /**
     * Processes a single sentence, creating a new sentence with words of the specified length replaced.
     *
     * @param sentence The original sentence to process.
     * @param length The target length of words to replace.
     * @param replacement The word to use as a replacement.
     * @return A new {@link Sentence} object with the transformations applied.
     */
    private Sentence processSentence(Sentence sentence, int length, Word replacement) {
        List<SentenceElement> newSentenceElements = new ArrayList<>();
        for (SentenceElement element : sentence.getElements()) {
            newSentenceElements.add(processElement(element, length, replacement));
        }
        return new Sentence(newSentenceElements);
    }

    /**
     * Processes a single element from a sentence. If the element is a {@link Word} of the target
     * length, it returns the replacement. Otherwise, it returns the original element.
     *
     * @param element The sentence element to process.
     * @param length The target length for replacement.
     * @param replacement The replacement word.
     * @return The original or replacement {@link SentenceElement}.
     */
    private SentenceElement processElement(SentenceElement element, int length, Word replacement) {
        if (element instanceof Word currentWord) {
            return (currentWord.getLength() == length) ? replacement : currentWord;
        }
        return element; // Return Punctuation or any other element type unchanged.
    }

    /**
     * Validates the inputs for the word replacement operation.
     *
     * @param text The Text object.
     * @param length The target word length.
     * @param replacement The replacement Word.
     * @throws IllegalArgumentException if text or replacement is null, or if length is not positive.
     */
    private void validateInputs(Text text, int length, Word replacement) {
        if (text == null || text.length() == 0) {
            throw new IllegalArgumentException("Input text cannot be empty or null.");
        }
        if (length <= 0) {
            throw new IllegalArgumentException("Word length must be positive.");
        }
        if (replacement == null) {
            throw new IllegalArgumentException("Replacement word cannot be null.");
        }
    }
}