package Lab1;

import java.util.Scanner;

public class Spellcheck {
    // Main method to run the spellcheck program
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        WordList wordList = new WordList();
        System.out.println("Welcome to the Spellcheck Program!");
        System.out.println("The word list contains " + wordList.size() + " words.");

        while (true) {
            System.out.print("Enter a word to check (or 'exit' to quit): ");
            String input = in.nextLine().trim().toLowerCase();
            
            if (input.equals("exit")) break;
            if (wordList.contains(input)) 
                System.out.println("'" + input + "' is spelled correctly.");
            else {
                System.out.println("'" + input + "' is NOT spelled correctly.");
                System.out.println("Did you mean:");

                delete_suggestion(input, wordList);
                insert_suggestion(input, wordList);
                swap_suggestion(input, wordList); 
                swap_suggestion2(input, wordList);
                insert_space_suggestion(input, wordList);
            }
        }
        in.close();
    }

    // Suggestion methods

    // Suggest words by deleting one character at a time
    public static void delete_suggestion (String input, WordList wordList) {
        for (int i = 0; i < input.length(); i++) {
            StringBuilder example = new StringBuilder(input);
            example.deleteCharAt(i);
            if (wordList.contains(example.toString())) 
                System.out.println("  - " + example.toString());
        }
    }

    // Suggest words by inserting one character at a time
    public static void insert_suggestion (String input, WordList wordList) {
        for (char c = 'a'; c <= 'z'; c++) 
            for (int i = 0; i <= input.length(); i++) {
                StringBuilder example = new StringBuilder(input);
                example.insert(i, c);
                if (wordList.contains(example.toString())) 
                    System.out.println("  - " + example.toString());
            }
    }

    // Suggest words by swapping each character with every other Letter
    public static void swap_suggestion (String input, WordList wordList) {
        for (int i = 0; i < input.length(); i++) 
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == input.charAt(i)) continue;
                StringBuilder example = new StringBuilder(input);
                example.setCharAt(i, c);
                if (wordList.contains(example.toString())) 
                    System.out.println("  - " + example.toString());

            }
    }

    // Suggest words by swapping adjacent characters
    public static void swap_suggestion2 (String input, WordList wordList) {
        for (int i = 0; i < input.length() - 1; i++) {
            StringBuilder example = new StringBuilder(input);
            char temp = example.charAt(i);
            example.setCharAt(i, example.charAt(i + 1));
            example.setCharAt(i + 1, temp);
            if (wordList.contains(example.toString())) 
                System.out.println("  - " + example.toString());
        }
    }

    // Suggest words by inserting a space at every possible position
    public static void insert_space_suggestion (String input, WordList wordList) {
        for (int i = 1; i < input.length(); i++) {
            String firstPart = input.substring(0, i);
            String secondPart = input.substring(i);
            if (wordList.contains(firstPart) && wordList.contains(secondPart)) 
                System.out.println("  - " + firstPart + " " + secondPart);
        }
    }

}