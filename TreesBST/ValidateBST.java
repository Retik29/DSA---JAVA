
import java.util.*;

/**
 * Check if Binary Tree is a BST (Validate BST)
 * 
 * A valid BST means for every node:
 * - All nodes in the left subtree have values strictly less than the node.
 * - All nodes in the right subtree have values strictly greater than the node.
 * 
 * Approach 1 (Range-based): Pass allowed [min, max] range for each node.
 * Approach 2 (Inorder): Inorder traversal of a BST gives sorted order.
 * Track the previous value and ensure strictly increasing.
 * 
 * Time: O(n) | Space: O(h)
 */
public class ValidateBST {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // ---------- Approach 1: Range-based validation ----------

    // Main function to validate BST using range approach
    static boolean isValidBST(Node root) {
        // Root can have any value: [Long.MIN, Long.MAX]
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Recursive validation with min/max bounds
    static boolean validate(Node root, long min, long max) {
        // Null node is valid
        if (root == null)
            return true;
        // Current value must be strictly within (min, max)
        if (root.data <= min || root.data >= max)
            return false;
        // Left subtree values must be < root.data
        // Right subtree values must be > root.data
        return validate(root.left, min, root.data)
                && validate(root.right, root.data, max);
    }

    // ---------- Approach 2: Inorder traversal validation ----------

    // Global variable to track the previously visited node's value
    static long prev;

    // Main function using inorder approach
    static boolean isValidBSTInorder(Node root) {
        prev = Long.MIN_VALUE; // Initialize to smallest value
        return inorderCheck(root);
    }

    // Inorder traversal checking strictly increasing order
    static boolean inorderCheck(Node root) {
        if (root == null)
            return true;
        // Check left subtree first
        if (!inorderCheck(root.left))
            return false;
        // Current node must be greater than the previous value
        if (root.data <= prev)
            return false;
        // Update prev to current value
        prev = root.data;
        // Check right subtree
        return inorderCheck(root.right);
    }

    static Node buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null)
            return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != null) {
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- Validate BST ---");

        // Valid BST: [2, 1, 3]
        Integer[] arr1 = { 2, 1, 3 };
        Node root1 = buildTree(arr1);
        System.out.println("Tree [2,1,3] - Range: " + isValidBST(root1)
                + " | Inorder: " + isValidBSTInorder(root1));

        // Invalid BST: [5, 1, 4, null, null, 3, 6] (4 < 5 but in right subtree)
        Integer[] arr2 = { 5, 1, 4, null, null, 3, 6 };
        Node root2 = buildTree(arr2);
        System.out.println("Tree [5,1,4,n,n,3,6] - Range: " + isValidBST(root2)
                + " | Inorder: " + isValidBSTInorder(root2));
    }
}
