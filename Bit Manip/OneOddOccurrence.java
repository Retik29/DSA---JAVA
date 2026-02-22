
// Find the One Element Occurring Odd Times — XOR all elements
// Time: O(n) | Space: O(1)
import java.util.*;

public class OneOddOccurrence {

    // XOR of equals is 0, so all pairs cancel leaving the odd one
    static int findOdd(int[] arr) {
        int xor = 0;
        for (int num : arr)
            xor ^= num;
        return xor;
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
        System.out.println("Element with odd occurrence: " + findOdd(arr));

        sc.close();
    }
}
