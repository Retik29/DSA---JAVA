
// Majority Element — Boyer-Moore Voting Algorithm
// Time: O(n) | Space: O(1)
import java.util.*;

public class MajorityElement {

    // Find candidate then verify
    static int majorityElement(int[] arr) {
        // Phase 1: find candidate
        int candidate = arr[0], count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (count == 0) {
                candidate = arr[i];
                count = 1;
            } else if (arr[i] == candidate)
                count++;
            else
                count--;
        }
        // Phase 2: verify
        count = 0;
        for (int num : arr)
            if (num == candidate)
                count++;
        return (count > arr.length / 2) ? candidate : -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int res = majorityElement(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println(res != -1 ? "Majority element: " + res : "No majority element");

        sc.close();
    }
}
