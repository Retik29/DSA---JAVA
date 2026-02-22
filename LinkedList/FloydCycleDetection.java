
// Floyd's Cycle Detection — Tortoise and Hare
// Time: O(n) | Space: O(1)
import java.util.*;

public class FloydCycleDetection {

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
        }
    }

    // Detect loop using slow (1 step) and fast (2 steps) pointers
    static boolean hasLoop(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                return true;
        }
        return false;
    }

    // Find start of loop: reset slow to head, move both 1 step
    static Node findLoopStart(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if (fast == null || fast.next == null)
            return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // Remove loop by finding the node before loop start
    static void removeLoop(Node head) {
        Node start = findLoopStart(head);
        if (start == null)
            return;
        Node cur = start;
        while (cur.next != start)
            cur = cur.next;
        cur.next = null;
    }

    // Build list from user input and optionally create loop
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter list size: ");
        int n = sc.nextInt();
        Node[] nodes = new Node[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            nodes[i] = new Node(sc.nextInt());
        for (int i = 0; i < n - 1; i++)
            nodes[i].next = nodes[i + 1];

        System.out.print("Enter loop-back index (-1 for no loop): ");
        int loopIdx = sc.nextInt();
        if (loopIdx >= 0 && loopIdx < n)
            nodes[n - 1].next = nodes[loopIdx];

        System.out.println("Has loop? " + hasLoop(nodes[0]));
        Node start = findLoopStart(nodes[0]);
        System.out.println("Loop start: " + (start != null ? start.data : "none"));

        if (start != null) {
            removeLoop(nodes[0]);
            System.out.println("Loop removed. Has loop? " + hasLoop(nodes[0]));
        }

        sc.close();
    }
}
