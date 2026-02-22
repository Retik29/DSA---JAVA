
// Check if Array is Sorted — iterative and recursive
// Time: O(n) | Space: O(1) iterative, O(n) recursive
import java.util.*;

public class CheckSorted {

    // Iterative: compare adjacent pairs
    static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++)
            if (arr[i] < arr[i - 1])
                return false;
        return true;
    }

    // Check sort order: ascending, descending, or not sorted
    static String checkSortOrder(int[] arr) {
        boolean asc = true, desc = true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1])
                asc = false;
            if (arr[i] > arr[i - 1])
                desc = false;
        }
        return asc && desc ? "All equal" : asc ? "Ascending" : desc ? "Descending" : "Not sorted";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Is sorted? " + isSorted(arr));
        System.out.println("Sort order: " + checkSortOrder(arr));

        sc.close();
    }
}
