// Trie Implementation (Prefix Tree)
// Time Complexity: Insert: O(L), Search: O(L) | Space Complexity: O(N * L)
// where L is the length of the string and N is the number of strings.
import java.util.*;

public class TrieImplementation {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < 26; i++)
                children[i] = null;
        }
    }

    static TrieNode root;

    static void insert(String key) {
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (curr.children[index] == null)
                curr.children[index] = new TrieNode();
            curr = curr.children[index];
        }
        curr.isEndOfWord = true;
    }

    static boolean search(String key) {
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (curr.children[index] == null)
                return false;
            curr = curr.children[index];
        }
        return curr != null && curr.isEndOfWord;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        root = new TrieNode();

        System.out.print("Enter number of words to insert: ");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " words:");
        for (int i = 0; i < n; i++) {
            insert(sc.next().toLowerCase());
        }

        System.out.print("Enter word to search: ");
        String query = sc.next().toLowerCase();
        if (search(query))
            System.out.println(query + " found in Trie");
        else
            System.out.println(query + " NOT found in Trie");

        sc.close();
    }
}
