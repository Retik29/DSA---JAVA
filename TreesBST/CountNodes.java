
/**
 * Count Total Nodes in a Binary Tree
 * 
 * Problem: Given a binary tree, count the total number of nodes.
 * 
 * Approach:
 * 1. If the tree is empty (null), count is 0.
 * 2. Otherwise: count = 1 (current node) + count(left) + count(right).
 * 
 * Time Complexity : O(n) - Every node is visited exactly once.
 * Space Complexity: O(h) - Recursion stack, where h is the height.
 */
import java.util.*;

public class CountNodes {

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
     * Recursively counts all nodes in the binary tree.
     * 
     * @param root Root of the binary tree
     * @return Total number of nodes
     */
    static int countNodes(Node root) {
        // Base case: an empty subtree has 0 nodes
        if (root == null)
            return 0;

        // Count = 1 (this node) + all nodes in left subtree + all nodes in right
        // subtree
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);

        return 1 + leftCount + rightCount;
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
        System.out.println("--- Count Nodes in Binary Tree ---");

        // Test 1: Full tree with 5 nodes
        // 1
        // / \
        // 2 3
        // / \
        // 4 5
        Integer[] treeData1 = { 1, 2, 3, 4, 5 };
        Node root1 = buildTree(treeData1);
        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Node Count: " + countNodes(root1));

        // Test 2: Complete binary tree with 7 nodes
        Integer[] treeData2 = { 1, 2, 3, 4, 5, 6, 7 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Node Count: " + countNodes(root2));

        // Test 3: Single node
        Integer[] treeData3 = { 1 };
        Node root3 = buildTree(treeData3);
        System.out.println("\nTree: [1]");
        System.out.println("Node Count: " + countNodes(root3));

        // Test 4: Empty tree
        System.out.println("\nEmpty tree");
        System.out.println("Node Count: " + countNodes(null));
    }
}
