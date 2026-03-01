
/**
 * Majority Element (Boyer-Moore Voting Algorithm)
 * 
 * Problem: Given an array of size 'n', find the majority element. 
 * The majority element is the element that appears more than floor(n/2) times.
 * 
 * Logic (Two Phases):
 * 1. Find a Candidate (Voting):
 *    - Initialize a candidate and a count.
 *    - Traverse the array:
 *      - If count is 0, pick current element as candidate and set count to 1.
 *      - If current element equals candidate, increment count.
 *      - Else, decrement count (this simulates "canceling" a pair of distinct elements).
 *    
 * 2. Verification:
 *    - The first phase only gives a POTENTIAL candidate. We must traverse the 
 *      array again to count actual occurrences of the candidate and check 
 *   If (actualCount > n/2).
 * 
 * Why it works:
 * If a majority element exists, it will appear more than all other elements combined. 
 * Even if it is canceled out by every other element, it will still remain as the candidate 
 * at the end because of its overwhelming frequency.
 * 
 * Complexity:
 * - Time Complexity : O(n) - Two linear passes.
 * - Space Complexity: O(1) - Only a few variables used.
 */
import java.util.*;

public class MajorityElement {

    /**
     * Finds the majority element using Boyer-Moore Voting Algorithm.
     * 
     * @return The majority element, or -1 if none exists.
     */
    static int findMajority(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return -1;

        // Phase 1: Find the candidate
        int candidate = arr[0];
        int count = 1;

        for (int i = 1; i < n; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Phase 2: Verify the candidate
        int actualCount = 0;
        for (int num : arr) {
            if (num == candidate) {
                actualCount++;
            }
        }

        return (actualCount > n / 2) ? candidate : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Majority Element Finder ---");
        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Execution
        int res = findMajority(arr);

        // Result Output
        System.out.println("\n--- Result Summary ---");
        System.out.println("Input Array: " + Arrays.toString(arr));
        if (res != -1) {
            System.out.println("Majority Element: " + res + " (appears more than " + (n / 2) + " times)");
        } else {
            System.out.println("No majority element found (no element appears more than " + (n / 2) + " times).");
        }

        sc.close();
    }
}
