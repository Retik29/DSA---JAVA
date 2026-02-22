
// Next Greater Element using Stack
// Time: O(n) | Space: O(n)
import java.util.*;

public class NextGreaterElement {

    static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i])
                stack.pop();
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
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

        int[] nge = nextGreater(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("NGE:   " + Arrays.toString(nge));

        sc.close();
    }
}
