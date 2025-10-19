package org.example;

/**
 * The main entry point for the text processing application. This class demonstrates the parsing of
 * a string into a structured text object, processing it, and printing the results.
 */
public class Main {

    /**
     * The main method that runs the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            // 1. Initial data
            String initialText = "Write    tests first, then \t build the features. A five-letter word.";
            int wordLength = 4;
            String replacementString = "$$$";

            System.out.println("Initial text string: \n" + initialText);
            System.out.println("-------------------------------------");

            // 2. Create service objects
            TextParser parser = new TextParser();
            TextProcessor processor = new TextProcessor();

            // 3. Parse the raw string into a structured Text object
            Text textObject = parser.parse(initialText);
            System.out.println("Parsed and reconstructed text: \n" + textObject);
            System.out.println("-------------------------------------");

            // 4. Perform the word replacement operation
            Word replacementWord = new Word(replacementString);
            Text processedText = processor.replaceWordsOfLength(textObject, wordLength, replacementWord);

            // 5. Print the final result
            System.out.println(
                    "Text after replacing words of length " + wordLength + " with '" + replacementString + "':");
            System.out.println(processedText);

        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
