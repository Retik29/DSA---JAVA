
/**
 * Invert (Mirror) a Binary Tree
 * 
 * Problem: Given the root of a binary tree, invert (mirror) the tree
 * by swapping the left and right children of every node.
 * 
 * Approach:
 * 1. If the tree is empty, return null.
 * 2. Recursively invert the left subtree.
 * 3. Recursively invert the right subtree.
 * 4. Swap the left and right children of the current node.
 * 5. Return the root.
 * 
 * Time Complexity : O(n) - Every node is visited exactly once.
 * Space Complexity: O(h) - Recursion stack, where h is the height.
 */
import java.util.*;

public class InvertTree {

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
     * Inverts (mirrors) the binary tree by swapping left and right children
     * of every node recursively.
     * 
     * @param root Root of the binary tree
     * @return Root of the inverted tree
     */
    static Node invertTree(Node root) {
        // Base case: empty node, nothing to invert
        if (root == null)
            return null;

        // Recursively invert the left subtree
        Node invertedLeft = invertTree(root.left);

        // Recursively invert the right subtree
        Node invertedRight = invertTree(root.right);

        // Swap: left child becomes the inverted right, and vice versa
        root.left = invertedRight;
        root.right = invertedLeft;

        // Return the root of the inverted tree
        return root;
    }

    /**
     * Utility: Level order traversal to visualize the tree.
     */
    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.data);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(level);
        }
        return result;
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
        System.out.println("--- Invert (Mirror) Binary Tree ---");

        // Test 1:
        // Before: 4 After: 4
        // / \ / \
        // 2 7 7 2
        // / \ / \ / \ / \
        // 1 3 6 9 9 6 3 1
        Integer[] treeData = { 4, 2, 7, 1, 3, 6, 9 };
        Node root = buildTree(treeData);

        System.out.println("Before inversion: " + levelOrder(root));

        // Invert the tree
        invertTree(root);

        System.out.println("After inversion:  " + levelOrder(root));

        // Test 2: Two-node tree
        // Before: 2 After: 2
        // / \
        // 1 1
        Integer[] treeData2 = { 2, 1 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nBefore inversion: " + levelOrder(root2));
        invertTree(root2);
        System.out.println("After inversion:  " + levelOrder(root2));
    }
}
