
/**
 * Largest Rectangle in Histogram
 * 
 * Problem: Given an array of integers 'heights' representing the histogram's 
 * bar height where the width of each bar is 1, find the area of the largest 
 * rectangle in the histogram.
 * 
 * Algorithm: Monotonic Stack (Increasing)
 * 1. Logic: For each bar, we want to find the largest rectangle that includes 
 *    this bar as its shortest height. This rectangle extends to the left as 
 *    long as bars are >= heights[i] and to the right as long as bars are >= heights[i].
 * 
 * 2. Implementation: 
 *    - Maintain a stack of indices such that the heights corresponding to 
 *      these indices are in non-decreasing order.
 *    - When we encounter a bar 'h' smaller than the bar at the top of the 
 *      stack, we know that the bar at the top can't extend its height further 
 *      to the right.
 *    - We pop the top, calculate the area of the rectangle with that popped 
 *      height as the height of the rectangle. 
 *    - The width is determined by the distance between the current index and 
 *       the index below it in the stack.
 * 
 * Time Complexity : O(n) - Each index is pushed and popped exactly once.
 * Space Complexity: O(n) - To store indices in the stack.
 */
import java.util.*;

public class LargestRectangleHistogram {

    /**
     * Calculates the area of the largest rectangle in a histogram.
     * 
     * @param heights Array of bar heights
     * @return Maximum area found
     */
    static int largestRectangle(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        // Stack to store indices of bars in non-decreasing order of heights
        Deque<Integer> stack = new ArrayDeque<>();

        // We iterate up to n to handle the remaining elements in the stack
        // by treating heights[n] as 0.
        for (int i = 0; i <= n; i++) {
            // Use 0 as a sentinel height to force popping all elements at the end
            int currentHeight = (i == n) ? 0 : heights[i];

            // While the current bar is shorter than the bar at stack top
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                // Popped height is the shortest height for the rectangle we are considering
                int h = heights[stack.pop()];

                // Calculate width:
                // If stack is empty, it means 'h' was the smallest seen so far,
                // so it extends from index 0 to i-1 (width = i).
                // Otherwise, it extends from (new top + 1) to i-1.
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;

                maxArea = Math.max(maxArea, h * w);
            }
            // Push current index onto stack
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter number of bars: ");
        int n = sc.nextInt();
        int[] heights = new int[n];

        System.out.print("Enter " + n + " bar heights: ");
        for (int i = 0; i < n; i++)
            heights[i] = sc.nextInt();

        // Perform calculation
        int result = largestRectangle(heights);

        // Output results
        System.out.println("\n--- Largest Rectangle Analysis ---");
        System.out.println("Histogram: " + Arrays.toString(heights));
        System.out.println("Result (Max Area): " + result);

        sc.close();
    }
}
