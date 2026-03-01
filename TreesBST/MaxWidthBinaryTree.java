
/**
 * Maximum Width of a Binary Tree
 * 
 * Problem: Given the root of a binary tree, return the maximum width of the tree.
 * The width of one level is defined as the length between the leftmost and
 * rightmost non-null nodes (including null nodes in between).
 * 
 * Approach:
 * 1. Use BFS with index assignment.
 * 2. For a node at index 'i':
 *    - Left child is at index 2*i + 1
 *    - Right child is at index 2*i + 2
 * 3. For each level, width = (index of last node) - (index of first node) + 1.
 * 4. To prevent integer overflow, normalize indices at each level by
 *    subtracting the minimum index of that level.
 * 
 * Time Complexity : O(n) - Every node is visited once.
 * Space Complexity: O(n) - Queue can hold at most n/2 nodes.
 */
import java.util.*;

public class MaxWidthBinaryTree {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // Pair class to hold a node and its positional index
    static class Pair {
        Node node; // The tree node
        int index; // Positional index in the complete binary tree

        Pair(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    /**
     * Returns the maximum width of the binary tree.
     * 
     * @param root Root of the binary tree
     * @return Maximum width among all levels
     */
    static int maxWidth(Node root) {
        // Edge case: empty tree has width 0
        if (root == null)
            return 0;

        // Variable to track the maximum width
        int maxWidth = 0;

        // BFS queue storing (node, index) pairs
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0)); // Root is at index 0

        while (!queue.isEmpty()) {
            // Number of nodes at this level
            int levelSize = queue.size();

            // Get the first index of this level (for normalization)
            int minIndex = queue.peek().index;

            // Track the first and last index of this level
            int first = 0, last = 0;

            for (int i = 0; i < levelSize; i++) {
                // Dequeue the front node
                Pair pair = queue.poll();
                Node node = pair.node;

                // Normalize the index to prevent overflow
                // Subtract the minimum index of this level
                int normalizedIndex = pair.index - minIndex;

                // Record the first index
                if (i == 0)
                    first = normalizedIndex;

                // Record the last index
                if (i == levelSize - 1)
                    last = normalizedIndex;

                // Enqueue left child with index 2*normalizedIndex + 1
                if (node.left != null) {
                    queue.add(new Pair(node.left, 2 * normalizedIndex + 1));
                }

                // Enqueue right child with index 2*normalizedIndex + 2
                if (node.right != null) {
                    queue.add(new Pair(node.right, 2 * normalizedIndex + 2));
                }
            }

            // Width of this level = last - first + 1
            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return maxWidth;
    }

    /**
     * Builds a binary tree from a level-order array.
     */
    static Node buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null)
            return null;

        Node root = new Node(arr[0]);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < arr.length) {
            Node current = queue.poll();

            if (i < arr.length && arr[i] != null) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;

            if (i < arr.length && arr[i] != null) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Demo tree: 1
        // / \
        // 3 2
        // / \ \
        // 5 3 9
        // Width at level 0: 1 (just 1)
        // Width at level 1: 2 (3 and 2)
        // Width at level 2: 4 (5 to 9, counting nulls in between)
        // Max width: 4
        System.out.println("--- Maximum Width of Binary Tree ---");
        Integer[] treeData = { 1, 3, 2, 5, 3, null, 9 };
        Node root = buildTree(treeData);

        System.out.println("Tree: [1, 3, 2, 5, 3, null, 9]");
        System.out.println("Max Width: " + maxWidth(root));

        // Test 2: All left
        Integer[] treeData2 = { 1, 2, null, 3, null };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, null, 3]");
        System.out.println("Max Width: " + maxWidth(root2));
    }
}
