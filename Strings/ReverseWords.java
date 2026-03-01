
/**
 * Reverse Words in a String
 * 
 * Problem: Given a string, reverse the order of characters in each word 
 * within a sentence while still maintaining the original word order, 
 * OR reverse the order of the words themselves. 
 * This implementation focuses on reversing the ORDER of the words.
 * 
 * Example: "the sky is blue" -> "blue is sky the"
 */
import java.util.*;

public class ReverseWords {

    /**
     * Method 1: Using Split and Reverse Iteration.
     * Logic: Split the string into an array of words, then build a new string
     * starting
     * from the last word.
     * 
     * Time Complexity : O(n) - where n is length of string.
     * Space Complexity: O(n) - for storing the words array and StringBuilder.
     */
    static String reverseWords(String s) {
        // Trim and split by one or more whitespace characters
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        // Traverse words array from end to start
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            // Add space between words
            if (i > 0)
                sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Method 2: Reverse Entire String then Reverse Each Word (Pseudo In-Place).
     * Logic:
     * 1. Reverse the entire character array.
     * 2. Traverse the reversed array and reverse each individual word.
     * 
     * Time Complexity : O(n) - Two passes over the character array.
     * Space Complexity: O(n) - Character array used (true in-place is not possible
     * with Java Strings).
     */
    static String reverseWordsInPlace(String s) {
        char[] arr = s.toCharArray();

        // Step 1: Reverse the whole string
        // "the sky" -> "yks eht"
        reverse(arr, 0, arr.length - 1);

        // Step 2: Reverse each word in the reversed string
        // "yks eht" -> "sky the"
        int start = 0;
        for (int end = 0; end <= arr.length; end++) {
            // Check for space or end of string
            if (end == arr.length || arr[end] == ' ') {
                reverse(arr, start, end - 1);
                start = end + 1; // Start of next word
            }
        }
        return new String(arr);
    }

    /**
     * Utility method to reverse a portion of a character array.
     */
    private static void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String s = sc.nextLine();

        System.out.println("--- Reverse Word Order ---");
        System.out.println("Method 1 (Split):    \"" + reverseWords(s) + "\"");
        System.out.println("Method 2 (In-place): \"" + reverseWordsInPlace(s.trim()) + "\"");

        sc.close();
    }
}
