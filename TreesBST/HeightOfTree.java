
/**
 * Height of a Binary Tree
 * 
 * Problem: Find the height of a binary tree. Height is defined as the
 * number of nodes on the longest path from the root to a leaf.
 * (Some definitions use edges instead of nodes; here we count nodes.)
 * 
 * Approach:
 * 1. If the tree is empty (null), height is 0.
 * 2. Otherwise, height = 1 + max(height of left subtree, height of right subtree).
 * 3. This is a simple recursive post-order style computation.
 * 
 * Time Complexity : O(n) - Every node is visited exactly once.
 * Space Complexity: O(h) - Recursion stack, where h is the height of the tree.
 */
import java.util.*;

public class HeightOfTree {

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
     * Calculates the height of the binary tree.
     * Height = number of nodes on the longest root-to-leaf path.
     * 
     * @param root Root of the binary tree
     * @return Height of the tree (0 if empty)
     */
    static int height(Node root) {
        // Base case: an empty tree has height 0
        if (root == null)
            return 0;

        // Recursively compute the height of the left subtree
        int leftHeight = height(root.left);

        // Recursively compute the height of the right subtree
        int rightHeight = height(root.right);

        // Height of current tree = 1 (for this node) + the taller subtree
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
        System.out.println("--- Height of Binary Tree ---");

        // Test 1: Balanced tree
        // 1
        // / \
        // 2 3
        // / \
        // 4 5
        // Height = 3 (path: 1 -> 2 -> 4 or 1 -> 2 -> 5)
        Integer[] treeData1 = { 1, 2, 3, 4, 5 };
        Node root1 = buildTree(treeData1);
        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Height: " + height(root1));

        // Test 2: Left-skewed tree
        // 1
        // /
        // 2
        // /
        // 3
        // Height = 3
        Integer[] treeData2 = { 1, 2, null, 3 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, null, 3]");
        System.out.println("Height: " + height(root2));

        // Test 3: Single node
        Integer[] treeData3 = { 1 };
        Node root3 = buildTree(treeData3);
        System.out.println("\nTree: [1]");
        System.out.println("Height: " + height(root3));

        // Test 4: Empty tree
        System.out.println("\nEmpty tree");
        System.out.println("Height: " + height(null));
    }
}
