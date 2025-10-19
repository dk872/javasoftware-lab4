package org.example;

/**
 * Represents a punctuation mark or a delimiter (like a space) within a sentence.
 * This class implements the {@link SentenceElement} interface.
 *
 * <p>This class is immutable.
 */
public final class Punctuation implements SentenceElement {

    /** The character symbol for the punctuation or delimiter. */
    private final char symbol;

    /**
     * Constructs a Punctuation object with a specific symbol.
     *
     * @param symbol The character for this punctuation mark.
     */
    public Punctuation(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the character symbol.
     *
     * @return The punctuation character.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the string representation of this punctuation mark.
     *
     * @return The symbol of this punctuation as a {@code String}.
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
