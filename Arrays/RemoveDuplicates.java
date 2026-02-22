
// Remove Duplicates from Sorted Array — in-place
// Time: O(n) | Space: O(1)
import java.util.*;

public class RemoveDuplicates {

    // Returns new length after removing duplicates
    static int removeDuplicates(int[] arr) {
        if (arr.length == 0)
            return 0;
        int j = 0; // position of last unique
        for (int i = 1; i < arr.length; i++)
            if (arr[i] != arr[j])
                arr[++j] = arr[i];
        return j + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter sorted array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        int newLen = removeDuplicates(arr);
        System.out.println("After removing duplicates: " + Arrays.toString(Arrays.copyOf(arr, newLen)));

        sc.close();
    }
}
