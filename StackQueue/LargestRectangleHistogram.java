
// Largest Rectangle in Histogram — Stack-based approach
// Time: O(n) | Space: O(n)
import java.util.*;

public class LargestRectangleHistogram {

    static int largestRectangle(int[] heights) {
        int n = heights.length, maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>(); // stores indices

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }


//     public int largestRectangleArea(int[] heights) {
//         int n = heights.length;
//         if (n == 0) return 0;

//         // Using an array as a stack is faster than Deque/Stack classes
//         int[] stack = new int[n + 1]; 
//         int top = -1;
//         int maxArea = 0;

//         for (int i = 0; i <= n; i++) {
//             // Use 0 as the sentinel height for the end of the array
//             int currentHeight = (i == n) ? 0 : heights[i];

//             while (top != -1 && currentHeight < heights[stack[top]]) {
//                 int h = heights[stack[top--]];
//                 // If stack is empty, width is i; otherwise, i - new top - 1
//                 int w = (top == -1) ? i : i - stack[top] - 1;
//                 maxArea = Math.max(maxArea, h * w);
//             }
//             stack[++top] = i;
//         }
//         return maxArea;
//     }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of bars: ");
        int n = sc.nextInt();
        int[] heights = new int[n];
        System.out.print("Enter " + n + " heights: ");
        for (int i = 0; i < n; i++)
            heights[i] = sc.nextInt();

        System.out.println("Heights: " + Arrays.toString(heights));
        System.out.println("Largest rectangle area: " + largestRectangle(heights));

        sc.close();
    }
}
