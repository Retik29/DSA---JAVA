
// Linked List Operations — middle, remove duplicates, merge, nth from end
// Time: O(n) each | Space: O(1)
import java.util.*;

public class LinkedListOperations {

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
        }
    }

    // Find middle (slow-fast pointer)
    static Node findMiddle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Remove duplicates from sorted list
    static void removeDuplicates(Node head) {
        Node cur = head;
        while (cur != null && cur.next != null) {
            if (cur.data == cur.next.data)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
    }

    // Merge two sorted lists
    static Node mergeSorted(Node l1, Node l2) {
        Node dummy = new Node(0), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Nth node from end (two-pointer)
    static Node nthFromEnd(Node head, int n) {
        Node first = head, second = head;
        for (int i = 0; i < n; i++)
            first = first.next;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    // Helpers
    static Node createList(Scanner sc) {
        System.out.print("Enter list size: ");
        int n = sc.nextInt();
        if (n == 0)
            return null;
        System.out.print("Enter " + n + " elements: ");
        Node head = new Node(sc.nextInt()), cur = head;
        for (int i = 1; i < n; i++) {
            cur.next = new Node(sc.nextInt());
            cur = cur.next;
        }
        return head;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node list = createList(sc);
        System.out.print("List: ");
        printList(list);
        System.out.println("Middle: " + findMiddle(list).data);

        System.out.print("Enter n for nth from end: ");
        int n = sc.nextInt();
        System.out.println(n + "th from end: " + nthFromEnd(list, n).data);

        sc.close();
    }
}
