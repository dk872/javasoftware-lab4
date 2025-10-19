package org.example;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a word, which is composed of a sequence of Letter objects.
 * This class implements the {@link SentenceElement} interface.
 *
 * <p>This class is immutable.
 */
public final class Word implements SentenceElement {

    /** A list of Letter objects that form the word. */
    private final List<Letter> letters;

    /**
     * A convenience constructor to create a Word from a String. Each character of the string will be
     * converted into a Letter object.
     *
     * @param wordString The string representation of the word. Must not be null.
     */
    public Word(String wordString) {
        if (wordString == null) {
            throw new IllegalArgumentException("Word string cannot be null.");
        }

        // Create a temporary list to fill in
        List<Letter> lettersList = new ArrayList<>();

        // Go through each character in the string
        for (char character : wordString.toCharArray()) {
            // Add a new Letter object to the list
            lettersList.add(new Letter(character));
        }

        // Assign an immutable list
        this.letters = lettersList;
    }

    /**
     * Returns the list of letters that make up this word.
     *
     * @return An unmodifiable list of Letter objects.
     */
    public List<Letter> getLetters() {
        return java.util.Collections.unmodifiableList(letters);
    }

    /**
     * Returns the number of letters in the word.
     *
     * @return The length of the word.
     */
    public int getLength() {
        return letters.size();
    }

    /**
     * Returns the string representation of the word.
     * This is constructed by concatenating the characters of all its {@link Letter} objects.
     *
     * @return The full word as a {@code String}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Letter letter : this.letters) {
            builder.append(letter);
        }

        return builder.toString();
    }
}
