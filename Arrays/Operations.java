
// Array Operations — Insert, Delete, Search
// Time: O(n) for insert/delete, O(n) for search | Space: O(1)
import java.util.*;

public class Operations {

    // Insert element at given index, return new effective size
    static int insert(int[] arr, int n, int idx, int val) {
        for (int i = n; i > idx; i--)
            arr[i] = arr[i - 1]; // shift right
        arr[idx] = val;
        return n + 1;
    }

    // Delete element at index, return new effective size
    static int delete(int[] arr, int n, int idx) {
        for (int i = idx; i < n - 1; i++)
            arr[i] = arr[i + 1]; // shift left
        return n - 1;
    }

    // Linear search
    static int search(int[] arr, int n, int key) {
        for (int i = 0; i < n; i++)
            if (arr[i] == key)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n + 10]; // extra space for inserts
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(Arrays.copyOf(arr, n)));

        System.out.print("Search for element: ");
        int key = sc.nextInt();
        System.out.println("Found at index: " + search(arr, n, key));

        sc.close();
    }
}
