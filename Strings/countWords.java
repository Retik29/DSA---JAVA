/**

* Program: Count Occurrences of a Target Word in a String (No Spaces Required)
*
* Description:
* This Java program takes a string and a target word as input from the user
* and counts how many times the target appears in the string.
*
* Key Features:
* * Works even if the string has no spaces.
* * Supports overlapping matches.
* Example: "aaaa" with "aa" → 3 matches.
* * Case-insensitive matching is applied for better usability.
*
* Approach:
* 1. Convert both input string and target word to lowercase.
* 2. Traverse the string from index 0 to (length - target length).
* 3. Extract substring of target length at each position.
* 4. Compare with target word.
* 5. Increment count if match is found.
*
* Time Complexity: O(n * m)
* where n = length of string, m = length of target
  */

import java.util.Scanner;

class WordOccurrenceCounter {

public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    // Taking input from user
    System.out.print("Enter the main string: ");
    String text = sc.nextLine();

    System.out.print("Enter the target word: ");
    String target = sc.nextLine();

    // Convert both to lowercase for case-insensitive matching
    text = text.toLowerCase();
    target = target.toLowerCase();

    // Call function to count occurrences
    int result = countOccurrences(text, target);

    // Display result
    System.out.println("Number of occurrences: " + result);

    sc.close(); // close scanner to avoid resource leak
}

/**
 * This method counts how many times the target appears in the text
 */
public static int countOccurrences(String text, String target) {

    int count = 0;

    // Loop through each possible starting index
    for (int i = 0; i <= text.length() - target.length(); i++) {

        // Extract substring of same length as target
        String sub = text.substring(i, i + target.length());

        // Compare substring with target
        if (sub.equals(target)) {
            count++; // increment count when match is found
        }
    }

    return count;
}

}
