/**
 * Finding and Printing Duplicate Characters in a String
 * 
 * Problem:
 * 1. Find all duplicate characters within a given string.
 * 2. Print the occurrences (frequencies) of those duplicate characters.
 * 
 * Approach: Optimized Frequency Array (Hashing Technique)
 * 1. Instead of using a HashMap (which has overhead of object creation and hashing),
 *    we use a fixed-size integer array of size 256 (assuming ASCII characters).
 * 2. In the first pass: Traverse the string and increment the count of each character 
 *    in the count array using the character's ASCII value as the index.
 *    This gives us O(1) access time and avoids hash collisions.
 * 3. In the second pass: To find duplicates, check which characters have a count > 1.
 *    - To print them in the order of their first appearance, traverse the string again.
 *    - Print the character and its frequency only if its frequency is > 1.
 *    - Set the count of that character to 0 after printing to avoid duplicate prints.
 * 
 * Time Complexity : O(n) - where n is the length of the string.
 *                    We do a constant number of linear passes (two passes).
 * Space Complexity: O(1) - Constant space is used for the integer array of size 256.
 */
import java.util.*;

public class PrintDuplicates {

    /**
     * Finds and returns a list of duplicate characters in the string.
     * 
     * @param str The input string
     * @return List of duplicate characters
     */
    public static List<Character> findDuplicates(String str) {
        // Initialize an array to store the frequency of each of the 256 ASCII characters
        int[] count = new int[256];
        
        // List to store the duplicate characters found in the string
        List<Character> duplicates = new ArrayList<>();

        // Loop through each character of the string to populate the frequency array
        for (int i = 0; i < str.length(); i++) {
            // Increment the count of the character at index str.charAt(i)
            count[str.charAt(i)]++;
        }

        // Loop through the string again to find characters with count > 1
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            
            // If the character occurs more than once, it is a duplicate
            if (count[currentChar] > 1) {
                // Add the character to our list of duplicates
                duplicates.add(currentChar);
                
                // Set its count to 0 in the array so we do not add it again
                count[currentChar] = 0;
            }
        }

        // Return the list of unique duplicate characters
        return duplicates;
    }

    /**
     * Prints the duplicate characters in the string along with their number of occurrences.
     * 
     * @param str The input string
     */
    public static void printDuplicateOccurrences(String str) {
        // Initialize an array to store the frequency of each of the 256 ASCII characters
        int[] count = new int[256];

        // First pass: Count occurrences of each character in the string
        for (int i = 0; i < str.length(); i++) {
            // Get character at index i and increment its corresponding index in the count array
            count[str.charAt(i)]++;
        }

        // Flag to check if any duplicate character is found
        boolean foundDuplicate = false;

        System.out.println("\nDuplicate characters and their occurrences:");

        // Second pass: Traverse the string to print duplicates in the order of their occurrence
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // If the count of the character is greater than 1, it means it is a duplicate
            if (count[currentChar] > 1) {
                // Print the character and its frequency count
                System.out.println("'" + currentChar + "' -> Occurs " + count[currentChar] + " times");
                
                // Set the character's count to 0 to prevent it from being printed again
                count[currentChar] = 0;
                
                // Set the flag to true since we found at least one duplicate
                foundDuplicate = true;
            }
        }

        // If no duplicates were found, inform the user
        if (!foundDuplicate) {
            System.out.println("No duplicate characters found in the string.");
        }
    }

    public static void main(String[] args) {
        // Scanner object to read input from standard input stream
        Scanner sc = new Scanner(System.in);

        // Prompt the user for input
        System.out.print("Enter a string: ");
        // Read the full line entered by the user
        String str = sc.nextLine();

        // 1. Finding duplicates and displaying the list
        List<Character> duplicatesList = findDuplicates(str);
        System.out.println("List of duplicates found: " + duplicatesList);

        // 2. Printing occurrences of those duplicates
        printDuplicateOccurrences(str);

        // Close the scanner to release resources
        sc.close();
    }
}
