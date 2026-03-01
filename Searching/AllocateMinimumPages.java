
/**
 * Allocate Minimum Pages
 * 
 * Problem: Given an array of pages of N books and M students. Every student is 
 * assigned to read some consecutive books. The task is to minimize the maximum 
 * number of pages assigned to a student.
 * 
 * Strategy: Binary Search on Answer
 * 1. Define the range for the answer:
 *    - Lower Bound (lo): The maximum single book size (a student must read at least the largest book).
 *    - Upper Bound (hi): The sum of all pages (one student reads all books).
 * 2. Perform Binary Search in the range [lo, hi].
 * 3. For each 'mid', check if it's possible to allocate books such that no 
 *    student reads more than 'mid' pages.
 * 4. If feasible, try for a smaller maximum (move hi = mid - 1).
 * 5. If not feasible, we need a larger maximum (move lo = mid + 1).
 * 
 * Time Complexity : O(n * log(sum - max))
 * Space Complexity: O(1)
 */
import java.util.*;

public class AllocateMinimumPages {

    /**
     * Checks if it's possible to allocate books such that no student gets more than
     * 'mid' pages.
     * 
     * @param pages    Array of book pages
     * @param students Number of students
     * @param mid      Current maximum page limit to check
     * @return true if allocation is feasible, false otherwise
     */
    static boolean isFeasible(int[] pages, int students, int mid) {
        int studentCount = 1;
        int currentSum = 0;

        for (int p : pages) {
            // If adding this book exceeds the current limit, allocate to next student
            if (currentSum + p > mid) {
                studentCount++;
                currentSum = p;
            } else {
                currentSum += p;
            }
        }

        // If required students <= available students, it's feasible
        return studentCount <= students;
    }

    /**
     * Finds the minimum of the maximum pages allocated.
     * 
     * @param pages    Array of book pages
     * @param students Number of students
     * @return Minimum maximum pages
     */
    static int allocatePages(int[] pages, int students) {
        // Base case: more students than books
        if (students > pages.length)
            return -1;

        // Binary search range setup
        int lo = 0;
        int hi = 0;
        for (int p : pages) {
            lo = Math.max(lo, p); // lo = maximum element
            hi += p; // hi = total sum
        }

        int res = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (isFeasible(pages, students, mid)) {
                res = mid; // Mid is potentially the answer
                hi = mid - 1; // Try for a smaller maximum
            } else {
                lo = mid + 1; // Need to increase the maximum page limit
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input gathering
        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        int[] pages = new int[n];

        System.out.print("Enter pages of " + n + " books: ");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        System.out.print("Enter number of students: ");
        int students = sc.nextInt();

        // Output results
        System.out.println("--- Page Allocation ---");
        System.out.println("Books: " + Arrays.toString(pages));

        int result = allocatePages(pages, students);
        if (result == -1) {
            System.out.println("Allocation not possible (students > books).");
        } else {
            System.out.println("Result: The minimum possible value of maximum pages is: " + result);
        }

        sc.close();
    }
}
