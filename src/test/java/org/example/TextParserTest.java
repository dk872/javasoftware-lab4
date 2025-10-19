package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for the TextParser class.
 * This class verifies all parsing logic, including whitespace handling,
 * sentence and word construction, and edge cases.
 */
@DisplayName("Tests for TextParser")
class TextParserTest {

    private TextParser parser;

    @BeforeEach
    void setUp() {
        // Initialize a new parser before each test to ensure isolation.
        parser = new TextParser();
    }

    @Test
    @DisplayName("Should parse a simple sentence ending with a period")
    void testParseSimpleSentence() {
        String input = "Hello world.";
        Text text = parser.parse(input);

        assertNotNull(text, "Text should not be null");
        assertEquals(1, text.getSentences().size(), "Text should contain one sentence");

        Sentence sentence = text.getSentences().getFirst();
        List<SentenceElement> elements = sentence.getElements();

        assertEquals(4, elements.size(), "Sentence should contain four elements (Word, Space, Word, Punctuation)");
        assertInstanceOf(Word.class, elements.get(0), "First element should be a Word");
        assertEquals("Hello", elements.get(0).toString());
        assertInstanceOf(Punctuation.class, elements.get(1), "Second element should be Punctuation (space)");
        assertEquals(" ", elements.get(1).toString());
        assertInstanceOf(Word.class, elements.get(2), "Third element should be a Word");
        assertEquals("world", elements.get(2).toString());
        assertInstanceOf(Punctuation.class, elements.get(3), "Fourth element should be Punctuation (period)");
        assertEquals(".", elements.get(3).toString());
    }

    @Test
    @DisplayName("Should handle multiple spaces and tabs between words")
    void testParsingWithMultipleWhitespaceAndTabs() {
        String input = "This   is \t a   \t\t test.";
        Text text = parser.parse(input);

        assertEquals("This is a test.", text.toString(), "All whitespace sequences should be replaced by a single space");
        assertEquals(1, text.getSentences().size());
    }

    @Test
    @DisplayName("Should handle leading and trailing whitespace")
    void testParsingWithLeadingAndTrailingWhitespace() {
        String input = "  Trim test.  ";
        Text text = parser.parse(input);

        assertEquals("Trim test.", text.toString(), "Leading and trailing whitespace should be removed");
    }

    @Test
    @DisplayName("Should parse text with multiple sentences")
    void testParsingMultipleSentences() {
        String input = "First sentence. Second one! Is this the third?";
        Text text = parser.parse(input);

        assertEquals(3, text.getSentences().size(), "Text should contain three sentences");
        assertEquals("First sentence.", text.getSentences().get(0).toString());
        assertEquals(" Second one!", text.getSentences().get(1).toString());
        assertEquals(" Is this the third?", text.getSentences().get(2).toString());
    }

    @Test
    @DisplayName("Should parse text that does not end with a terminator")
    void testParsingTextWithoutFinalTerminator() {
        String input = "This is an incomplete sentence";
        Text text = parser.parse(input);

        assertEquals(1, text.getSentences().size(), "Should form one sentence even without a period");
        assertEquals("This is an incomplete sentence", text.toString());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for null input")
    void testThrowsExceptionForNullInput() {
        assertThrows(IllegalArgumentException.class, () ->
                parser.parse(null), "Method should throw for null input");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for empty input")
    void testThrowsExceptionForEmptyInput() {
        assertThrows(IllegalArgumentException.class, () ->
                parser.parse(""), "Method should throw for an empty string");
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for whitespace-only input")
    void testThrowsExceptionForWhitespaceOnlyInput() {
        assertThrows(IllegalArgumentException.class, () ->
                parser.parse("   \t   "), "Method should throw for a whitespace-only string");
    }

    @Test
    @DisplayName("Should parse a sentence with commas")
    void testParsingWithCommas() {
        String input = "Hello, world.";
        Text text = parser.parse(input);

        assertEquals("Hello, world.", text.toString());
        Sentence sentence = text.getSentences().getFirst();
        List<SentenceElement> elements = sentence.getElements();

        assertEquals(5, elements.size());
        assertInstanceOf(Word.class, elements.get(0));
        assertEquals("Hello", elements.get(0).toString());
        assertInstanceOf(Punctuation.class, elements.get(1));
        assertEquals(",", elements.get(1).toString());
        assertInstanceOf(Punctuation.class, elements.get(2)); // Space
        assertInstanceOf(Word.class, elements.get(3));
        assertEquals("world", elements.get(3).toString());
        assertInstanceOf(Punctuation.class, elements.get(4)); // Period
    }
}