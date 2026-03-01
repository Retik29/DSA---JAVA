
/**
 * Stack Implementation
 * 
 * A Stack is a Linear Data Structure that follows the LIFO (Last In First Out) principle.
 * Operations:
 * 1. Push: Add an element to the top.
 * 2. Pop: Remove and return the top element.
 * 3. Peek: Return the top element without removing it.
 * 4. isEmpty: Check if the stack is empty.
 * 
 * Time Complexity : O(1) for all operations.
 * Space Complexity: O(N) where N is the number of elements.
 */
import java.util.*;

public class StackImplementation {

    /**
     * Array-based implementation of Stack.
     * Pros: O(1) time complexity, cache-friendly.
     * Cons: Fixed capacity, potential for overflow.
     */
    static class ArrayStack {
        int[] arr;
        int top = -1; // Pointer to the top element

        ArrayStack(int cap) {
            arr = new int[cap];
        }

        // Adds an element to the stack
        void push(int x) {
            if (top == arr.length - 1) {
                System.out.println("Stack Overflow");
                return;
            }
            arr[++top] = x;
        }

        // Removes and returns the top element
        int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            return arr[top--];
        }

        // Returns the top element without removing it
        int peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty");
                return -1;
            }
            return arr[top];
        }

        // Checks if the stack is empty
        boolean isEmpty() {
            return top == -1;
        }

        // Returns the current number of elements
        int size() {
            return top + 1;
        }
    }

    /**
     * Linked List-based implementation of Stack.
     * Pros: Dynamic size, no overflow (until memory runs out).
     * Cons: Extra memory for pointers, not as cache-friendly as arrays.
     */
    static class LinkedStack {
        private class Node {
            int data;
            Node next;

            Node(int d) {
                data = d;
            }
        }

        Node top; // The 'head' of the linked list is the 'top' of the stack
        int size = 0;

        // Adds an element to the top
        void push(int x) {
            Node n = new Node(x);
            n.next = top;
            top = n;
            size++;
        }

        // Removes and returns the top element
        int pop() {
            if (isEmpty()) {
                System.out.println("Stack Underflow");
                return -1;
            }
            int d = top.data;
            top = top.next;
            size--;
            return d;
        }

        // Returns the top element
        int peek() {
            if (isEmpty())
                return -1;
            return top.data;
        }

        // Checks if the stack is empty
        boolean isEmpty() {
            return top == null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements to push: ");
        int n = sc.nextInt();
        ArrayStack stack = new ArrayStack(n);

        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            stack.push(sc.nextInt());

        // Demonstrate operations
        System.out.println("--- Stack Operations ---");
        System.out.println("Peek original top: " + stack.peek());
        System.out.println("Popped element:    " + stack.pop());
        System.out.println("New size:         " + stack.size());

        // Pop all remaining elements
        System.out.print("Remaining elements (LIFO order): ");
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();

        sc.close();
    }
}
