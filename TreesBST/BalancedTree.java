
/**
 * Check if a Binary Tree is Height-Balanced
 * 
 * Problem: A binary tree is height-balanced if, for every node, the
 * absolute difference between the heights of the left and right
 * subtrees is at most 1.
 * 
 * Approach (Single Pass — O(n)):
 * Use a single recursive function that returns:
 *   - The height of the subtree if it IS balanced.
 *   - -1 if the subtree is NOT balanced (early termination signal).
 * 
 * At each node:
 * 1. Get the left height. If -1, the left subtree is unbalanced → return -1.
 * 2. Get the right height. If -1, the right subtree is unbalanced → return -1.
 * 3. If |leftHeight - rightHeight| > 1, this node is unbalanced → return -1.
 * 4. Otherwise, return 1 + max(leftHeight, rightHeight).
 * 
 * No separate helper function needed — the single function serves both
 * purposes (height computation + balance check) by using -1 as a sentinel.
 * 
 * Time Complexity : O(n) - Every node is visited at most once.
 * Space Complexity: O(h) - Recursion stack, where h is the height.
 */
import java.util.*;

public class BalancedTree {

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
     * Checks if the tree is balanced by computing height.
     * Returns -1 if unbalanced, otherwise returns the height.
     * 
     * This single function eliminates the need for a helper:
     * - Positive return = height (tree is balanced so far)
     * - Return of -1 = unbalanced (propagates upward immediately)
     * 
     * @param root Root of the current subtree
     * @return Height if balanced, -1 if unbalanced
     */
    static int checkHeight(Node root) {
        // Base case: empty tree is balanced with height 0
        if (root == null)
            return 0;

        // Compute the height of the left subtree
        int leftHeight = checkHeight(root.left);

        // If left subtree is unbalanced, propagate -1 immediately (early exit)
        if (leftHeight == -1)
            return -1;

        // Compute the height of the right subtree
        int rightHeight = checkHeight(root.right);

        // If right subtree is unbalanced, propagate -1 immediately
        if (rightHeight == -1)
            return -1;

        // Check if the current node violates the balance condition
        // Balance condition: |leftHeight - rightHeight| <= 1
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        // Tree is balanced at this node, return its height
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Builds a binary tree from a level-order array.
     * null entries represent absent nodes.
     */
    static Node buildTree(Integer[] arr) {
        // Empty array or null root means no tree
        if (arr.length == 0 || arr[0] == null)
            return null;

        // Create the root node from the first element
        Node root = new Node(arr[0]);

        // Queue for level-order construction
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Index to track current position in the array
        int i = 1;

        while (!queue.isEmpty() && i < arr.length) {
            // Get the next parent node from the queue
            Node current = queue.poll();

            // Assign left child if value is not null
            if (i < arr.length && arr[i] != null) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++; // Move to the next element

            // Assign right child if value is not null
            if (i < arr.length && arr[i] != null) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++; // Move to the next element
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- Balanced Binary Tree Check ---");

        // Test 1: Balanced tree
        // 3
        // / \
        // 9 20
        // / \
        // 15 7
        Integer[] treeData1 = { 3, 9, 20, null, null, 15, 7 };
        Node root1 = buildTree(treeData1);
        // checkHeight returns height if balanced, -1 if not
        boolean balanced1 = checkHeight(root1) != -1;
        System.out.println("Tree: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Is Balanced? " + balanced1);

        // Test 2: Unbalanced tree
        // 1
        // /
        // 2
        // /
        // 3
        Integer[] treeData2 = { 1, 2, null, 3 };
        Node root2 = buildTree(treeData2);
        boolean balanced2 = checkHeight(root2) != -1;
        System.out.println("\nTree: [1, 2, null, 3]");
        System.out.println("Is Balanced? " + balanced2);

        // Test 3: Unbalanced at a non-root node
        // 1
        // / \
        // 2 2
        // / \
        // 3 3
        // / \
        // 4 4
        Integer[] treeData3 = { 1, 2, 2, 3, 3, null, null, 4, 4 };
        Node root3 = buildTree(treeData3);
        boolean balanced3 = checkHeight(root3) != -1;
        System.out.println("\nTree: [1, 2, 2, 3, 3, null, null, 4, 4]");
        System.out.println("Is Balanced? " + balanced3);

        // Test 4: Empty tree (balanced by definition)
        System.out.println("\nEmpty tree");
        System.out.println("Is Balanced? " + (checkHeight(null) != -1));
    }
}
