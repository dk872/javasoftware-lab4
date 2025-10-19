# Java_software-lab4

## Description
The core purpose of this project is to deconstruct a plain string into a meaningful object model. Instead of treating text as a simple sequence of characters, it builds a tree-like structure where a Text object contains a list of Sentence objects, and each Sentence contains a list of SentenceElement objects (which can be either a Word or a Punctuation mark).

This object-oriented approach makes the text easy to analyze, manipulate, and transform in a structured way, rather than relying on complex regular expressions or string-splitting logic. The project includes a TextParser to handle the conversion from a string to the model and a TextProcessor to perform operations on the model, such as replacing words of a specific length.

## Features
- **Object-Oriented Text Model**: represents text as a clean hierarchy of Java objects (Text, Sentence, Word, Punctuation, Letter).
- **Type-Safe Sentence Structure**: uses the SentenceElement interface to ensure that sentences are composed of valid, known types (Word and Punctuation), improving code robustness and readability.
- **Advanced Text Parser**: converts any raw String into the structured text model, automatically trims leading/trailing whitespace and collapses multiple spaces and tabs into a single space, correctly identifies sentence boundaries based on terminators like `.`, `!`, and `?`.
- **Text Manipulation Logic**: includes a TextProcessor class with methods to perform operations on the text model, such as replacing all words of a specific length with a substitute word.
- **Immutability**: the model classes (Word, Sentence, etc.) are designed to be immutable, which makes the code safer and more predictable, especially in multi-threaded environments.

## How to run
First, clone the repository and navigate into the project directory:
```
git clone https://github.com/dk872/javasoftware-lab4
```
```
cd javasoftware-lab4
```

Compile the code:
```
javac src/main/java/org/example/*.java
```

Run the program:
```
java -cp src/main/java org.example.Main
```

## Unit tests
This project includes **27** unit tests using JUnit 5 to ensure the reliability and correctness of its components. The tests are organized into logical groups to cover every part of the application.

**TextParserTest**

This is the most comprehensive test suite, covering all aspects of the parsing logic:

- Parsing simple and complex sentences.
- Handling various whitespace scenarios (multiple spaces, tabs, leading/trailing spaces).
- Correctly identifying sentence boundaries.
- Testing edge cases like empty strings, null input, and whitespace-only strings to ensure the parser is robust.

**TextProcessorTest**

These tests validate the business logic of the application:

- Verifying that words of a specific length are correctly replaced.
- Ensuring the text remains unchanged when no words of the target length are found.
- Testing functionality across multiple sentences.
- Validating input by ensuring that null or invalid arguments throw IllegalArgumentException.

**ModelClassesTest**

This suite contains basic sanity checks for the model classes:

- Verifying that constructors work as expected.
- Ensuring that toString() methods correctly reconstruct the string representation of each component.
- Testing that getLength() for a Word returns the correct number of letters.

### How to run tests
Make sure you have JUnit 5 configured, then run the tests with your preferred method:
  - From command line
  ```
  mvn test
  ```
  - In an IDE like IntelliJ IDEA or Eclipse using the test runner.

### Documentation
The project includes generated **Javadoc documentation**.  
You can browse it here: [Project Documentation](https://dk872.github.io/javasoftware-lab4/)

To regenerate the documentation, run:
```
javadoc -d docs -sourcepath src/main/java -subpackages org.example -private
```

## Author info
Dmytro Kulyk, a student of group IM-32.
