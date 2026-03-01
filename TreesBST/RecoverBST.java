
import java.util.*;

/**
 * Recover BST (Fix Two Swapped Nodes)
 * 
 * Problem: Two nodes of a BST are swapped by mistake. Recover the tree
 * without changing its structure. Only the values of two nodes are swapped.
 * 
 * Approach (Inorder Traversal):
 * 1. Inorder of a valid BST is strictly sorted.
 * 2. If two nodes are swapped, there will be either:
 * - One or two violations (where prev.data > current.data).
 * 3. Find the two violating nodes and swap their values.
 * 
 * Case 1 (adjacent swap): One violation → first = prev, second = current.
 * Case 2 (non-adjacent swap): Two violations →
 * first = prev at first violation, second = current at second violation.
 * 
 * Time: O(n) | Space: O(h) recursion stack (O(1) with Morris traversal)
 */
public class RecoverBST {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Globals to track the two swapped nodes and the previous node
    static Node first = null; // First incorrect node
    static Node second = null; // Second incorrect node
    static Node prev = null; // Previously visited node in inorder

    // Recover the BST by finding and swapping the two incorrect nodes
    static void recoverTree(Node root) {
        // Reset globals
        first = second = prev = null;

        // Find the two swapped nodes using inorder traversal
        inorder(root);

        // Swap their values to fix the BST
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }

    // Inorder traversal to detect violations
    static void inorder(Node root) {
        if (root == null)
            return;

        // Recurse left
        inorder(root.left);

        // Check for violation: prev > current (breaking sorted order)
        if (prev != null && prev.data > root.data) {
            if (first == null) {
                // First violation found
                first = prev; // The larger node is the first incorrect
            }
            // Always update second to current at every violation
            second = root; // The smaller node is the second incorrect
        }
        // Update prev
        prev = root;

        // Recurse right
        inorder(root.right);
    }

    // Utility: print inorder
    static void printInorder(Node root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
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
        System.out.println("--- Recover BST ---");

        // Correct BST: [1, 2, 3] -> Swapped: [1, 3, 2] (nodes 3 and 2 swapped)
        // Tree structure: 3 After fix: 2
        // / \ / \
        // 1 2 1 3
        Integer[] arr = { 3, 1, 2 };
        Node root = buildTree(arr);

        System.out.print("Before recovery (inorder): ");
        printInorder(root);
        System.out.println();

        recoverTree(root);

        System.out.print("After recovery (inorder):  ");
        printInorder(root);
        System.out.println();
    }
}
