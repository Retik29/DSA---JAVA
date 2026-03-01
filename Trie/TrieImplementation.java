
/**
 * Trie Implementation (Prefix Tree)
 * 
 * A Trie is a specialized tree-based data structure used to store a dynamic set 
 * or associative array where the keys are usually strings.
 * 
 * Key Features:
 * 1. Efficient Retrieval: Search and Insert operations take O(L) time.
 * 2. Prefix Matching: Ideal for autocomplete, spell checkers, and IP routing.
 * 
 * Data Structure:
 * - Each node contains an array of children (covering 'a' to 'z').
 * - isEndOfWord flag marks the completion of a valid word.
 * 
 * Time Complexity : O(L) for Insert and Search, where L is word length.
 * Space Complexity: O(ALPHABET_SIZE * N * L) in the worst case.
 */
import java.util.*;

public class TrieImplementation {

    /**
     * Internal class representing a single node in the Trie.
     */
    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // Indices 0-25 represent 'a'-'z'
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            // Children array is initialized to null by default in Java
        }
    }

    static TrieNode root;

    /**
     * Inserts a word into the Trie.
     * Logic: Iterate through the string, create nodes if they don't exist.
     */
    static void insert(String key) {
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a'; // Map 'a' -> 0, 'b' -> 1...

            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        // Mark the last node as the end of a valid word
        curr.isEndOfWord = true;
    }

    /**
     * Searches for a word in the Trie.
     * Logic: Follow the path based on characters. If a null node is hit,
     * or the last node isn't marked as end-of-word, the word doesn't exist.
     */
    static boolean search(String key) {
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';

            if (curr.children[index] == null) {
                return false; // Path doesn't exist
            }
            curr = curr.children[index];
        }
        // Word matches only if the current node is marked as endOfWord
        return curr != null && curr.isEndOfWord;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        root = new TrieNode(); // Initialize root

        System.out.println("--- Trie (Prefix Tree) Demo ---");
        System.out.print("Enter number of words to insert: ");
        int n = sc.nextInt();

        System.out.println("Enter " + n + " words line by line:");
        for (int i = 0; i < n; i++) {
            insert(sc.next().toLowerCase());
        }

        System.out.println("\nWords inserted successfully.");

        // Interactive search
        System.out.print("Enter word to search: ");
        String query = sc.next().toLowerCase();

        if (search(query)) {
            System.out.println("Result: '" + query + "' was FOUND in the Trie.");
        } else {
            System.out.println("Result: '" + query + "' was NOT found.");
        }

        sc.close();
    }
}
