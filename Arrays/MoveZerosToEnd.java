
// Move Zeros to End — maintain relative order of non-zero
// Time: O(n) | Space: O(1)
import java.util.*;

public class MoveZerosToEnd {

    // Two-pointer: place non-zeros at front, fill rest with 0
    static void moveZeros(int[] arr) {
        int j = 0; // position for next non-zero
        for (int num : arr)
            if (num != 0)
                arr[j++] = num;
        while (j < arr.length)
            arr[j++] = 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Original: " + Arrays.toString(arr));
        moveZeros(arr);
        System.out.println("After:    " + Arrays.toString(arr));

        sc.close();
    }
}
