
/**
 * Min Stack
 * 
 * Problem: Design a stack that supports push, pop, top, and retrieving 
 * the minimum element in constant time (O(1)).
 * 
 * Logic: Dual-Stack Approach
 * 1. Main Stack: Stores all the elements normally.
 * 2. Min Stack: Stores the minimum element encountered so far. 
 *    - When pushing 'x': 
 *      If minStack is empty or x <= current minimum (minStack.peek()), push x onto minStack.
 *    - When popping:
 *      If the element being popped from the main stack is the current minimum, 
 *      pop it from the minStack as well.
 * 
 * Time Complexity : O(1) for push, pop, top, and getMin.
 * Space Complexity: O(n) - In the worst case (elements in decreasing order), the minStack stores n elements.
 */
import java.util.*;

class MinStack {
    // Standard stack to hold all values
    private Stack<Integer> stack = new Stack<>();
    // Auxiliary stack to keep track of minimums
    private Stack<Integer> minStack = new Stack<>();

    /**
     * Pushes element onto the stack.
     * Updates minStack if the new element is the new minimum.
     */
    public void push(int x) {
        stack.push(x);
        // If x is smaller than or equal to current min, it becomes a new min
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    /**
     * Removes the top element from the stack.
     * Removes from minStack if the element being popped is the current minimum.
     */
    public void pop() {
        if (stack.isEmpty())
            return;

        // Use .equals() for object comparison (Integer)
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    /**
     * Returns the top element of the stack.
     */
    public int top() {
        return stack.peek();
    }

    /**
     * Retrieves the minimum element in the stack in O(1) time.
     */
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MinStack ms = new MinStack();

        System.out.println("--- Min Stack Interactive Demo ---");
        System.out.println("Options: 1.Push  2.Pop  3.Top  4.GetMin  5.Exit");

        while (true) {
            System.out.print("\nChoose option: ");
            int opt = sc.nextInt();

            if (opt == 1) {
                System.out.print("Enter value to push: ");
                ms.push(sc.nextInt());
                System.out.println("Successfully pushed.");
            } else if (opt == 2) {
                ms.pop();
                System.out.println("Popped top element.");
            } else if (opt == 3) {
                System.out.println("Current Top: " + ms.top());
            } else if (opt == 4) {
                System.out.println("Minimum in Stack: " + ms.getMin());
            } else {
                break;
            }
        }
        System.out.println("Demo finished.");
        sc.close();
    }
}
