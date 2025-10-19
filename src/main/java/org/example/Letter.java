package org.example;

/**
 * Represents a single letter character. This is the most basic building block of a word.
 *
 * <p>This class is immutable.
 */
public final class Letter {

    /** The character value of the letter. */
    private final char character;

    /**
     * Constructs a Letter with a specific character.
     *
     * @param character The character for this letter.
     */
    public Letter(char character) {
        this.character = character;
    }

    /**
     * Returns the character value of this letter.
     *
     * @return The character.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Returns the string representation of this letter.
     *
     * @return The character of this letter as a {@code String}.
     */
    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
