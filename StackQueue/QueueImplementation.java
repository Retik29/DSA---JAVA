
/**
 * Queue Implementation
 * 
 * A Queue is a Linear Data Structure that follows the FIFO (First In First Out) principle.
 * Operations:
 * 1. Enqueue: Add an element to the rear (end).
 * 2. Dequeue: Remove and return the element from the front (start).
 * 3. Peek: Return the front element without removing it.
 * 4. isEmpty: Check if the queue is empty.
 * 
 * Time Complexity : O(1) for all operations.
 * Space Complexity: O(N) where N is the number of elements.
 */
import java.util.*;

public class QueueImplementation {

    /**
     * Circular Array-based Queue.
     * Logic: Uses modular arithmetic (index % capacity) to make the array
     * "circular",
     * allowing the reuse of empty spaces left after dequeue operations.
     */
    static class ArrayQueue {
        int[] arr;
        int front = 0; // Index of the front element
        int rear = -1; // Index of the last element
        int size = 0; // Current number of elements
        int capacity; // Maximum possible size

        ArrayQueue(int c) {
            this.capacity = c;
            arr = new int[c];
        }

        // Adds an element to the rear
        void enqueue(int x) {
            if (size == capacity) {
                System.out.println("Queue Overflow");
                return;
            }
            // Move rear forward circularly
            rear = (rear + 1) % capacity;
            arr[rear] = x;
            size++;
        }

        // Removes and returns the front element
        int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue Underflow");
                return -1;
            }
            int data = arr[front];
            // Move front forward circularly
            front = (front + 1) % capacity;
            size--;
            return data;
        }

        // Returns the front element
        int peek() {
            if (isEmpty())
                return -1;
            return arr[front];
        }

        // Checks if empty
        boolean isEmpty() {
            return size == 0;
        }
    }

    /**
     * Linked List-based Queue.
     * Logic: Maintains two pointers, 'front' and 'rear', to the head and
     * tail of the linked list respectively.
     */
    static class LinkedQueue {
        private class Node {
            int data;
            Node next;

            Node(int d) {
                data = d;
            }
        }

        Node front, rear;
        int size = 0;

        // Adds to the tail (rear)
        void enqueue(int x) {
            Node newNode = new Node(x);
            // If queue is empty, both front and rear point to new node
            if (isEmpty()) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        // Removes from the head (front)
        int dequeue() {
            if (isEmpty()) {
                System.out.println("Queue Underflow");
                return -1;
            }
            int data = front.data;
            front = front.next;
            // If we dequeued the last element, set rear to null
            if (front == null) {
                rear = null;
            }
            size--;
            return data;
        }

        int peek() {
            if (isEmpty())
                return -1;
            return front.data;
        }

        boolean isEmpty() {
            return front == null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Demo using ArrayQueue
        System.out.print("Enter capacity of the Queue: ");
        int n = sc.nextInt();
        ArrayQueue q = new ArrayQueue(n);

        System.out.println("Enter " + n + " elements to enqueue:");
        for (int i = 0; i < n; i++)
            q.enqueue(sc.nextInt());

        // Output results
        System.out.println("\n--- Queue Operations ---");
        System.out.println("Front Element (Peek): " + q.peek());
        System.out.println("Dequeued Element:     " + q.dequeue());

        System.out.print("Remaining Elements (FIFO order): ");
        while (!q.isEmpty()) {
            System.out.print(q.dequeue() + " ");
        }
        System.out.println();

        sc.close();
    }
}
