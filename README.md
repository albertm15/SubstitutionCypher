# Substitution Cipher (Java Project)

## Description
This project is a Java implementation of a **Substitution Cipher**, a classic encryption method where each unit of plaintext is replaced by the ciphertext according to a fixed system.

Developed as a university assignment, this project focuses on algorithmic logic, string manipulation, and data validation in Java. It utilizes the **ACM Java Task Force** library (`CommandLineProgram`) to run a suite of internal unit tests that verify the correctness of the encryption and decryption processes.

## Features
* **Encryption & Decryption**: clear logic to transform text based on a substitution key and revert it back to its original form.
* **Key Management**:
    * **Creation**: Generates a valid key pair from two input strings.
    * **Validation**: Ensures the key is a valid 1-to-1 mapping (bijection) with no duplicate characters.
    * **Inversion**: Algorithm to invert the key for the decryption process.
* **Robust Testing**: The `run()` method executes a comprehensive series of internal tests to validate every step of the algorithm (e.g., `testAllPairs`, `testUnique`, `testEncodeText`).
* **Console Output**: Uses `acm.program.CommandLineProgram` to display formatted test results (OK/ERROR) in the console.

## Project Structure

The project consists of a single main class containing the logic and the verification tests:

* **`SubstitutionCypher.java`**: The core class. It extends `CommandLineProgram` and includes:
    * **`run()`**: The entry point that executes the test suite.
    * **Cryptographic Methods**:
        * `createKey(String left, String right)`: Builds the mapping key.
        * `isValid(char[][] key)`: Validates that the key is well-formed.
        * `encodeText(char[][] key, String clearText)`: Encrypts a string.
        * `decodeText(char[][] key, String encodedText)`: Decrypts a string by inverting the key.
    * **Helper Methods**: `allPairs`, `getColumn`, `unique`, etc., to handle data manipulation.
    * **Test Methods**: A collection of methods (e.g., `testEncodeChar`, `testIsValid`) that assert the logic works as expected.

## Prerequisites

* **Java Development Kit (JDK)** (Version 8 or higher).
* **ACM Library (`acm.jar`)**: This project relies on the ACM Java Task Force library for the console interface and program structure.

## How to Run

1.  Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, etc.).
2.  Ensure `acm.jar` is added to your project's classpath/dependencies.
3.  Navigate to `src/SubstitutionCypher.java`.
4.  Run the `main` method.

## How it Works

Currently, the application is configured to run in **Test Mode**. When you execute the program, it will:

1.  Initialize the cipher logic.
2.  Run a sequence of predefined tests covering:
    * Key structure validation.
    * Uniqueness of characters.
    * Inversion of keys (swapping columns).
    * Encoding individual characters and full strings.
    * Decoding strings.
3.  Print the results to the console. You will see green **OK** messages for passed tests or red **ERROR** messages if a logic mismatch occurs.

## Code Example

Although the main execution runs tests, the core logic can be used as follows:

```java
SubstitutionCypher cipher = new SubstitutionCypher();

// 1. Create a key mapping 'a'->'b' and 'c'->'d'
char[][] key = cipher.createKey("ac", "bd");

// 2. Encrypt a message
String secret = cipher.encodeText(key, "acca"); // Returns "bddb"

// 3. Decrypt the message
String original = cipher.decodeText(key, secret); // Returns "acca"
