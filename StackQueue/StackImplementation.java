
// Stack Implementation — Array, Linked List, and Dynamic Array
// Push/Pop/Peek: O(1)
import java.util.*;

public class StackImplementation {

    // Array-based stack (fixed capacity)
    static class ArrayStack {
        int[] arr;
        int top = -1;

        ArrayStack(int cap) {
            arr = new int[cap];
        }

        void push(int x) {
            arr[++top] = x;
        }

        int pop() {
            return arr[top--];
        }

        int peek() {
            return arr[top];
        }

        boolean isEmpty() {
            return top == -1;
        }

        int size() {
            return top + 1;
        }
    }

    // Linked-list stack (dynamic)
    static class LinkedStack {
        private class Node {
            int data;
            Node next;

            Node(int d) {
                data = d;
            }
        }

        Node top;
        int size = 0;

        void push(int x) {
            Node n = new Node(x);
            n.next = top;
            top = n;
            size++;
        }

        int pop() {
            int d = top.data;
            top = top.next;
            size--;
            return d;
        }

        int peek() {
            return top.data;
        }

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

        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop:  " + stack.pop());
        System.out.println("Size: " + stack.size());

        // Pop all remaining
        System.out.print("Remaining (LIFO): ");
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
        System.out.println();

        sc.close();
    }
}
