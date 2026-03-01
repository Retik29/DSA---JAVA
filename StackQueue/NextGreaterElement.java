
/**
 * Next Greater Element (NGE)
 * 
 * Problem: For each element in an array, find the first element to its right 
 * that is strictly greater than it. If no such element exists, store -1.
 * 
 * Logic: Monotonic Stack (Decreasing from top to bottom)
 * 1. Traverse the array from right to left.
 * 2. Maintain a stack of elements that could potentially be the 'Next Greater' 
 *    for elements to the left.
 * 3. For the current element arr[i]:
 *    - Pop elements from the stack that are smaller than or equal to arr[i]. 
 *      Why? Because they are "shadowed" by arr[i] and can never be the next 
 *      greater element for any element further to the left.
 *    - If the stack is empty, there is no greater element to the right -> -1.
 *    - If the stack is not empty, the top of the stack is the Next Greater Element.
 *    - Push the current element onto the stack.
 * 
 * Time Complexity : O(n) - Each element is pushed and popped at most once.
 * Space Complexity: O(n) - For the stack and the result array.
 */
import java.util.*;

public class NextGreaterElement {

    /**
     * Finds the Next Greater Element for all items in the array.
     * 
     * @param arr Input array
     * @return Array containing NGE for each position
     */
    static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        // Stack to maintain elements in a monotonic way
        Deque<Integer> stack = new ArrayDeque<>();

        // Traversal from Right to Left
        for (int i = n - 1; i >= 0; i--) {
            // Remove elements that are not greater than current element
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            // Determine the NGE
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }

            // Push current element as a potential NGE for elements to its left
            stack.push(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        // Perform calculation
        int[] result = nextGreater(arr);

        // Output results
        System.out.println("\n--- Next Greater Element (NGE) Summary ---");
        System.out.println("Original Array:        " + Arrays.toString(arr));
        System.out.println("Next Greater Elements: " + Arrays.toString(result));

        sc.close();
    }
}
