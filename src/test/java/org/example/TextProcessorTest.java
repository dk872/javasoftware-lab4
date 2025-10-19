package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TextProcessor class.
 * This class verifies the core business logic of replacing words based on their length.
 */
@DisplayName("Tests for TextProcessor")
class TextProcessorTest {

    private TextParser parser;
    private TextProcessor processor;

    @BeforeEach
    void setUp() {
        parser = new TextParser();
        processor = new TextProcessor();
    }

    @Test
    @DisplayName("Should replace words of a given length in a single sentence")
    void testReplaceWordsOfGivenLength_SimpleCase() {
        String original = "This is a simple test.";
        Text text = parser.parse(original);
        Word replacement = new Word("REPLACED");

        Text result = processor.replaceWordsOfLength(text, 6, replacement);

        assertEquals("This is a REPLACED test.", result.toString());
    }

    @Test
    @DisplayName("Should replace multiple words of the same length")
    void testReplaceMultipleWordsOfSameLength() {
        String original = "brown fox jumps over the lazy dog.";
        Text text = parser.parse(original);
        Word replacement = new Word("#####");

        Text result = processor.replaceWordsOfLength(text, 5, replacement);

        assertEquals("##### fox ##### over the lazy dog.", result.toString());
    }

    @Test
    @DisplayName("Should not change text when no words of target length exist")
    void testReplaceWhenNoWordsOfTargetLengthExist() {
        String original = "No words of length three.";
        Text text = parser.parse(original);
        Word replacement = new Word("XXX");

        Text result = processor.replaceWordsOfLength(text, 3, replacement);

        assertEquals(original, result.toString(), "Text should not change if no target words are found");
    }

    @Test
    @DisplayName("Should replace words across multiple sentences")
    void testReplaceWordsAcrossMultipleSentences() {
        String original = "First sentence has a word. Second sentence also has.";
        Text text = parser.parse(original);
        Word replacement = new Word("REPLACED");

        Text result = processor.replaceWordsOfLength(text, 3, replacement);

        assertEquals("First sentence REPLACED a word. Second sentence also REPLACED.", result.toString());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for null text input")
    void testReplaceWords_NullText_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                processor.replaceWordsOfLength(null, 5, new Word("a")));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for zero length")
    void testReplaceWords_ZeroLength_ThrowsException() {
        Text text = parser.parse("Some text.");
        assertThrows(IllegalArgumentException.class, () ->
                processor.replaceWordsOfLength(text, 0, new Word("a")));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for negative length")
    void testReplaceWords_NegativeLength_ThrowsException() {
        Text text = parser.parse("Some text.");
        assertThrows(IllegalArgumentException.class, () ->
                processor.replaceWordsOfLength(text, -1, new Word("a")));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for null replacement")
    void testReplaceWords_NullReplacement_ThrowsException() {
        Text text = parser.parse("Some text.");
        assertThrows(IllegalArgumentException.class, () ->
                processor.replaceWordsOfLength(text, 4, null));
    }

    @Test
    @DisplayName("Should handle text containing only punctuation")
    void testReplaceOnTextWithOnlyPunctuation() {
        String original = ". , ! ? .";
        Text text = parser.parse(original);
        Word replacement = new Word("WORD");

        Text result = processor.replaceWordsOfLength(text, 4, replacement);

        assertEquals(original, result.toString(), "Text should not change");
    }
}