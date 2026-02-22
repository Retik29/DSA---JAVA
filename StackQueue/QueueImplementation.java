
// Queue Implementation — Array (Circular) and Linked List
// Enqueue/Dequeue/Peek: O(1)
import java.util.*;

public class QueueImplementation {

    // Circular array queue
    static class ArrayQueue {
        int[] arr;
        int front = 0, rear = -1, size = 0, cap;

        ArrayQueue(int c) {
            cap = c;
            arr = new int[c];
        }

        void enqueue(int x) {
            rear = (rear + 1) % cap;
            arr[rear] = x;
            size++;
        }

        int dequeue() {
            int d = arr[front];
            front = (front + 1) % cap;
            size--;
            return d;
        }

        int peek() {
            return arr[front];
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    // Linked-list queue
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

        void enqueue(int x) {
            Node n = new Node(x);
            if (rear != null)
                rear.next = n;
            rear = n;
            if (front == null)
                front = rear;
            size++;
        }

        int dequeue() {
            int d = front.data;
            front = front.next;
            if (front == null)
                rear = null;
            size--;
            return d;
        }

        int peek() {
            return front.data;
        }

        boolean isEmpty() {
            return front == null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements to enqueue: ");
        int n = sc.nextInt();
        ArrayQueue q = new ArrayQueue(n);
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            q.enqueue(sc.nextInt());

        System.out.println("Peek:    " + q.peek());
        System.out.println("Dequeue: " + q.dequeue());

        System.out.print("Remaining (FIFO): ");
        while (!q.isEmpty())
            System.out.print(q.dequeue() + " ");
        System.out.println();

        sc.close();
    }
}
