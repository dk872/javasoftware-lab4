package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sentence, which is composed of a sequence of {@link SentenceElement} objects.
 *
 * <p>This class is immutable.
 */
public final class Sentence {

    /** The list of elements (Words and Punctuation) that form the sentence. */
    private final List<SentenceElement> elements;

    /**
     * Constructs a Sentence from a list of {@link SentenceElement} objects.
     *
     * @param elements The list of elements that make up the sentence. Must not be null.
     */
    public Sentence(List<SentenceElement> elements) {
        if (elements == null) {
            throw new IllegalArgumentException("List of elements cannot be null.");
        }
        this.elements = new ArrayList<>(elements);
    }

    /**
     * Returns the list of elements in this sentence.
     *
     * @return An unmodifiable list of {@link SentenceElement} objects.
     */
    public List<SentenceElement> getElements() {
        return java.util.Collections.unmodifiableList(elements);
    }

    /**
     * Returns the string representation of the sentence.
     * This is constructed by concatenating the string representations of all its
     * elements (words and punctuation marks) in order.
     *
     * @return The full sentence as a {@code String}.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Object element : this.elements) {
            builder.append(element);
        }

        return builder.toString();
    }
}
