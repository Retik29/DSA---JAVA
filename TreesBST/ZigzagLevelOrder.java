
/**
 * Zigzag (Spiral) Level Order Traversal
 * 
 * Problem: Given a binary tree, return the zigzag level order traversal
 * of its nodes' values. (i.e., from left to right, then right to left
 * for the next level, and alternate between.)
 * 
 * Approach:
 * 1. Use standard BFS (Queue-based level order traversal).
 * 2. Maintain a boolean flag 'leftToRight' that toggles per level.
 * 3. For each level, if leftToRight is true, add elements left to right.
 *    Otherwise, add elements right to left (using index-based insertion).
 * 
 * Time Complexity : O(n) - Every node is visited exactly once.
 * Space Complexity: O(n) - Queue can hold at most n/2 nodes (last level).
 */
import java.util.*;

public class ZigzagLevelOrder {

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
     * Performs zigzag level order traversal on the given binary tree.
     * 
     * @param root Root of the binary tree
     * @return List of lists, where each inner list is one level in zigzag order
     */
    static List<List<Integer>> zigzagLevelOrder(Node root) {
        // Result list to hold all levels
        List<List<Integer>> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // Queue for BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Direction flag: true = left-to-right, false = right-to-left
        boolean leftToRight = true;

        // Process level by level
        while (!queue.isEmpty()) {
            // Number of nodes at this level
            int levelSize = queue.size();

            // Array to hold values for the current level
            // Using an array so we can insert at specific indices
            Integer[] levelValues = new Integer[levelSize];

            for (int i = 0; i < levelSize; i++) {
                // Remove the front node from the queue
                Node node = queue.poll();

                // Determine the index to place this node's value
                // If leftToRight: place at index i (normal order)
                // If rightToLeft: place at index (levelSize - 1 - i) (reverse order)
                int index = leftToRight ? i : (levelSize - 1 - i);

                // Store the value at the computed index
                levelValues[index] = node.data;

                // Enqueue left child for the next level
                if (node.left != null)
                    queue.add(node.left);

                // Enqueue right child for the next level
                if (node.right != null)
                    queue.add(node.right);
            }

            // Convert array to list and add to result
            result.add(Arrays.asList(levelValues));

            // Toggle direction for the next level
            leftToRight = !leftToRight;
        }

        return result;
    }

    /**
     * Builds a binary tree from a level-order array.
     * null entries represent absent nodes.
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

            // Assign left child
            if (i < arr.length && arr[i] != null) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Assign right child
            if (i < arr.length && arr[i] != null) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        // Demo tree: 3
        // / \
        // 9 20
        // / \
        // 15 7
        // Zigzag: [[3], [20, 9], [15, 7]]
        System.out.println("--- Zigzag Level Order Traversal ---");
        Integer[] treeData = { 3, 9, 20, null, null, 15, 7 };
        Node root = buildTree(treeData);

        // Compute and display the result
        List<List<Integer>> result = zigzagLevelOrder(root);
        System.out.println("Tree: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Zigzag Order: " + result);

        // Test 2: Full binary tree
        Integer[] treeData2 = { 1, 2, 3, 4, 5, 6, 7 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Zigzag Order: " + zigzagLevelOrder(root2));
    }
}
