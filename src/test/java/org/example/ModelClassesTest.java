package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Collections;
import java.util.List;

/**
 * Unit tests for the model classes (Word, Sentence, Text, etc.).
 * These tests ensure that constructors and basic methods like toString() and getLength()
 * work as expected.
 */
@DisplayName("Tests for Model Classes (Word, Sentence, Text)")
class ModelClassesTest {

    @Test
    @DisplayName("Should create Letter and verify its state")
    void testLetter() {
        Letter letter = new Letter('a');
        assertEquals('a', letter.getCharacter());
        assertEquals("a", letter.toString());
    }

    @Test
    @DisplayName("Should create Punctuation and verify its state")
    void testPunctuation() {
        Punctuation dot = new Punctuation('.');
        assertEquals('.', dot.getSymbol());
        assertEquals(".", dot.toString());
    }

    @Test
    @DisplayName("Should verify Word constructor, getLength(), and toString()")
    void testWordMethods() {
        String wordStr = "Java";
        Word word = new Word(wordStr);
        assertEquals(4, word.getLength(), "Word length should be 4");
        assertEquals(wordStr, word.toString(), "toString() should return the original word string");
        assertEquals(4, word.getLetters().size(), "The list of letters should have a size of 4");
    }

    @Test
    @DisplayName("Word constructor should throw IllegalArgumentException for null input")
    void testWordConstructor_NullInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Word(null));
    }

    @Test
    @DisplayName("Sentence toString() should correctly assemble words and punctuation")
    void testSentenceToString() {
        List<SentenceElement> elements = List.of(
                new Word("This"),
                new Punctuation(' '),
                new Word("is"),
                new Punctuation(' '),
                new Word("a"),
                new Punctuation(' '),
                new Word("test"),
                new Punctuation('.')
        );
        Sentence sentence = new Sentence(elements);
        assertEquals("This is a test.", sentence.toString());
    }

    @Test
    @DisplayName("Sentence constructor should throw IllegalArgumentException for null input")
    void testSentenceConstructor_NullInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Sentence(null));
    }

    @Test
    @DisplayName("Text toString() should correctly assemble multiple sentences")
    void testTextToString() {
        Sentence s1 = new Sentence(List.of(new Word("Sentence"), new Punctuation(' '), new Word("one"), new Punctuation('.')));
        Sentence s2 = new Sentence(List.of(new Punctuation(' '), new Word("Sentence"), new Punctuation(' '), new Word("two"), new Punctuation('!')));

        Text text = new Text(List.of(s1, s2));

        assertEquals("Sentence one. Sentence two!", text.toString());
    }

    @Test
    @DisplayName("Text constructor should throw IllegalArgumentException for null input")
    void testTextConstructor_NullInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Text(null));
    }

    @Test
    @DisplayName("toString() for empty models should return an empty string")
    void testEmptyModelsToString() {
        Sentence emptySentence = new Sentence(Collections.emptyList());
        assertEquals("", emptySentence.toString());

        Text emptyText = new Text(Collections.emptyList());
        assertEquals("", emptyText.toString());

        Text textWithEmptySentence = new Text(List.of(emptySentence));
        assertEquals("", textWithEmptySentence.toString());
    }
}