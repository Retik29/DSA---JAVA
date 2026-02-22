
// Singly Linked List — insert, delete, search, reverse
// Insert head: O(1), Insert tail: O(n), Search: O(n), Reverse: O(n)
import java.util.*;

public class SinglyLinkedList {

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
        }
    }

    Node head;
    int size = 0;

    void insertAtHead(int d) {
        Node n = new Node(d);
        n.next = head;
        head = n;
        size++;
    }

    void insertAtTail(int d) {
        Node n = new Node(d);
        if (head == null) {
            head = n;
        } else {
            Node cur = head;
            while (cur.next != null)
                cur = cur.next;
            cur.next = n;
        }
        size++;
    }

    int deleteFromHead() {
        int d = head.data;
        head = head.next;
        size--;
        return d;
    }

    boolean deleteByValue(int val) {
        if (head == null)
            return false;
        if (head.data == val) {
            head = head.next;
            size--;
            return true;
        }
        Node cur = head;
        while (cur.next != null && cur.next.data != val)
            cur = cur.next;
        if (cur.next != null) {
            cur.next = cur.next.next;
            size--;
            return true;
        }
        return false;
    }

    int search(int val) {
        Node cur = head;
        int pos = 0;
        while (cur != null) {
            if (cur.data == val)
                return pos;
            cur = cur.next;
            pos++;
        }
        return -1;
    }

    void reverse() {
        Node prev = null, cur = head;
        while (cur != null) {
            Node nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        head = prev;
    }

    void print() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data + " -> ");
            cur = cur.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            list.insertAtTail(sc.nextInt());

        list.print();
        System.out.println("Size: " + list.size);

        list.reverse();
        System.out.print("Reversed: ");
        list.print();

        System.out.print("Search for: ");
        int key = sc.nextInt();
        System.out.println("Found at index: " + list.search(key));

        sc.close();
    }
}
