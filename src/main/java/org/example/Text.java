package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a full text, which is composed of a sequence of Sentence objects. This is the
 * top-level container for the text structure.
 *
 * <p>This class is immutable.
 */
public final class Text {

    /** The list of sentences that form the text. */
    private final List<Sentence> sentences;

    /**
     * Constructs a Text object from a list of Sentence objects.
     *
     * @param sentences The list of sentences that make up the text. Must not be null.
     */
    public Text(List<Sentence> sentences) {
        if (sentences == null) {
            throw new IllegalArgumentException("List of sentences cannot be null.");
        }
        this.sentences = new ArrayList<>(sentences);
    }

    /**
     * Returns the list of sentences in the text.
     *
     * @return An unmodifiable list of Sentence objects.
     */
    public List<Sentence> getSentences() {
        return java.util.Collections.unmodifiableList(sentences);
    }

    /**
     * Returns the string representation of the entire text.
     * This is constructed by concatenating the string representations of all its
     * {@link Sentence} objects in order.
     *
     * @return The full text as a {@code String}.
     */
    @Override
    public String toString() {
        return sentences.stream().map(Object::toString).collect(Collectors.joining());
    }
}
