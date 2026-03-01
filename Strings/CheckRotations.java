
/**
 * Check Rotations
 * 
 * Problem: Check if string s2 is a rotation of string s1.
 * Example: "abcd" and "cdab" are rotations of each other.
 * 
 * Algorithm: Concatenation Logic
 * 1. If lengths of s1 and s2 are different, they cannot be rotations.
 * 2. Concatenate s1 with itself: temp = s1 + s1.
 * 3. If s2 is a substring of temp, then s2 is a rotation of s1.
 * 
 * Why it works:
 * All possible rotations of a string s1 of length n are present as substrings 
 * of length n in (s1 + s1).
 * 
 * Time Complexity : O(n) - where n is the length of strings (due to contains() method).
 * Space Complexity: O(n) - to store the concatenated string.
 */
import java.util.*;

public class CheckRotations {

    /**
     * Checks if s2 is a rotation of s1.
     * 
     * @param s1 First string
     * @param s2 Second string (potential rotation)
     * @return true if s2 is a rotation of s1, false otherwise
     */
    static boolean areRotations(String s1, String s2) {
        // Base case: lengths must be same
        if (s1.length() != s2.length())
            return false;

        // s2 must be a substring of (s1 + s1)
        return (s1 + s1).contains(s2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String s1 = sc.nextLine();
        System.out.print("Enter second string: ");
        String s2 = sc.nextLine();

        // Perform check and output result
        if (areRotations(s1, s2)) {
            System.out.println("Result: '" + s2 + "' IS a rotation of '" + s1 + "'.");
        } else {
            System.out.println("Result: '" + s2 + "' is NOT a rotation of '" + s1 + "'.");
        }

        sc.close();
    }
}
