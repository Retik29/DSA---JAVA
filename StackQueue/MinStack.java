
// Min Stack implementation - Get minimum element in O(1)
// Time Complexity: O(1) for all operations | Space Complexity: O(n)
import java.util.*;

class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MinStack ms = new MinStack();

        System.out.println("MinStack Operations: 1.Push 2.Pop 3.Top 4.GetMin 5.Exit");
        while (true) {
            System.out.print("Choose option: ");
            int opt = sc.nextInt();
            if (opt == 1) {
                System.out.print("Enter value: ");
                ms.push(sc.nextInt());
            } else if (opt == 2) {
                ms.pop();
                System.out.println("Popped");
            } else if (opt == 3) {
                System.out.println("Top: " + ms.top());
            } else if (opt == 4) {
                System.out.println("Min: " + ms.getMin());
            } else {
                break;
            }
        }
        sc.close();
    }
}
