
// Sliding Window Maximum — max in each window of size k
// Time: O(n) using Deque | Space: O(k)
import java.util.*;

public class SlidingWindowMaximum {

    static int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; i++) {
            // Remove elements outside window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.pollFirst();
            // Remove smaller elements from back
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[i])
                dq.pollLast();
            dq.addLast(i);
            if (i >= k - 1)
                result[i - k + 1] = arr[dq.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print("Enter window size k: ");
        int k = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Window maxima: " + Arrays.toString(maxSlidingWindow(arr, k)));

        sc.close();
    }
}
