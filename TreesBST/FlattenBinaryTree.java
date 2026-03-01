
/**
 * Flatten Binary Tree to Linked List (In-Place)
 * 
 * Problem: Given the root of a binary tree, flatten the tree into a 
 * "linked list" using the right pointers (left should be null).
 * The linked list should be in the same order as a pre-order traversal.
 * 
 * Approach (Morris-style / Reverse Postorder):
 * 1. For each node, if it has a left subtree:
 *    a. Find the rightmost node of the left subtree (predecessor in preorder).
 *    b. Connect it to the current node's right child.
 *    c. Move the left subtree to the right.
 *    d. Set left to null.
 * 2. Move to the right child and repeat.
 * 
 * This approach is O(n) time and O(1) space (no recursion/stack needed).
 * 
 * Time Complexity : O(n) - Each node is visited at most twice.
 * Space Complexity: O(1) - No extra space used (in-place).
 */
import java.util.*;

public class FlattenBinaryTree {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    /**
     * Flattens the binary tree to a linked list in-place.
     * The resulting list follows preorder traversal order.
     * Uses the Morris-traversal inspired approach (O(1) space).
     * 
     * @param root Root of the binary tree
     */
    static void flatten(Node root) {
        // Start from the root
        Node current = root;

        // Process each node
        while (current != null) {
            // If the current node has a left child
            if (current.left != null) {
                // Step 1: Find the rightmost node in the left subtree
                // This is the predecessor of current.right in preorder
                Node predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // Step 2: Connect the predecessor's right to the current's right child
                // This preserves the right subtree by attaching it after the left subtree
                predecessor.right = current.right;

                // Step 3: Move the left subtree to the right
                current.right = current.left;

                // Step 4: Set left to null (linked list has no left pointers)
                current.left = null;
            }

            // Move to the next node in the flattened list
            current = current.right;
        }
    }

    /**
     * Prints the flattened linked list (following right pointers).
     */
    static void printFlattenedList(Node root) {
        Node current = root;
        while (current != null) {
            System.out.print(current.data);
            // Print arrow if there's a next node
            if (current.right != null) {
                System.out.print(" -> ");
            }
            current = current.right;
        }
        System.out.println();
    }

    /**
     * Utility: Preorder traversal for comparison.
     */
    static void printPreorder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        // Demo tree: 1
        // / \
        // 2 5
        // / \ \
        // 3 4 6
        // After flattening: 1 -> 2 -> 3 -> 4 -> 5 -> 6
        System.out.println("--- Flatten Binary Tree to Linked List ---");

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.right = new Node(6);

        System.out.print("Preorder (before flatten): ");
        printPreorder(root);
        System.out.println();

        // Flatten the tree
        flatten(root);

        System.out.print("Flattened list:            ");
        printFlattenedList(root);
    }
}
