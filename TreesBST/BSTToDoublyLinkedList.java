
import java.util.*;

/**
 * Convert BST to Sorted Doubly Linked List (In-Place)
 * 
 * Problem: Convert a BST into a sorted circular doubly linked list in-place.
 * The left pointer should point to the previous node and the right pointer
 * should point to the next node.
 * 
 * Approach (Inorder Traversal):
 * 1. Perform inorder traversal of the BST.
 * 2. During traversal, link the current node with the previously visited node.
 * 3. After traversal, connect the head and tail to make it circular.
 * 
 * Time: O(n) | Space: O(h) recursion stack
 */
public class BSTToDoublyLinkedList {

    static class Node {
        int data;
        Node left, right; // left = prev, right = next in DLL

        Node(int d) {
            data = d;
        }
    }

    // Global variables to track head and the previously visited node
    static Node head = null;
    static Node prev = null;

    // Convert BST to sorted circular doubly linked list
    static Node bstToDLL(Node root) {
        // Reset globals
        head = null;
        prev = null;

        // Perform inorder traversal and link nodes
        inorder(root);

        // Make it circular: connect head and tail
        if (head != null && prev != null) {
            head.left = prev; // Head's prev points to the last node
            prev.right = head; // Tail's next points to the head
        }

        return head;
    }

    // Inorder traversal that links nodes
    static void inorder(Node root) {
        if (root == null)
            return;

        // Recurse on left subtree
        inorder(root.left);

        // Process current node
        if (prev == null) {
            // First node in inorder — this is the head
            head = root;
        } else {
            // Link prev node's right to current
            prev.right = root;
            // Link current node's left to prev
            root.left = prev;
        }
        // Update prev to current
        prev = root;

        // Recurse on right subtree
        inorder(root.right);
    }

    // Print the doubly linked list (forward)
    static void printDLL(Node head) {
        if (head == null) {
            System.out.println("Empty");
            return;
        }
        Node cur = head;
        do {
            System.out.print(cur.data + " <-> ");
            cur = cur.right;
        } while (cur != head);
        System.out.println("(back to " + head.data + ")");
    }

    static Node insert(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- BST to Sorted Doubly Linked List ---");

        Node root = null;
        int[] vals = { 4, 2, 6, 1, 3, 5, 7 };
        for (int v : vals)
            root = insert(root, v);

        System.out.println("BST values: " + Arrays.toString(vals));
        Node dllHead = bstToDLL(root);

        System.out.print("DLL (forward): ");
        printDLL(dllHead);
    }
}
