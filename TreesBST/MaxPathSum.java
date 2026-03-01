
/**
 * Maximum Path Sum in a Binary Tree
 * 
 * Problem: A path in a binary tree is a sequence of nodes where each pair
 * of adjacent nodes in the sequence has an edge connecting them. A node can
 * only appear in the sequence at most once. The path does NOT need to pass
 * through the root. Find the maximum sum of any such path.
 * 
 * Approach:
 * 1. For each node, calculate the maximum "single-branch" gain (the max
 *    sum path starting at this node and going down through one child).
 * 2. At each node, consider the path that passes through it:
 *    path_sum = node.data + leftGain + rightGain.
 * 3. Update the global maximum with this path_sum.
 * 4. Return the single-branch gain upward: node.data + max(leftGain, rightGain).
 *    (Only one branch can be part of the path when we report upward.)
 * 
 * Time Complexity : O(n) - Each node is visited once.
 * Space Complexity: O(h) - Recursion stack, where h is the height of the tree.
 */
import java.util.*;

public class MaxPathSum {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // Global variable to track the maximum path sum found so far
    static int maxSum;

    /**
     * Computes the maximum gain obtainable from this node going downward.
     * Also updates the global maxSum considering paths through this node.
     * 
     * @param root Current node being processed
     * @return Maximum single-branch gain from this node downward
     */
    static int maxGain(Node root) {
        // Base case: null node contributes 0 gain
        if (root == null) {
            return 0;
        }

        // Recursively get the maximum gain from the left subtree
        // Use Math.max with 0 to ignore negative gains (don't take the branch)
        int leftGain = Math.max(maxGain(root.left), 0);

        // Recursively get the maximum gain from the right subtree
        int rightGain = Math.max(maxGain(root.right), 0);

        // The price of the path that PASSES THROUGH this node
        // This path uses both left and right branches
        int pathThroughNode = root.data + leftGain + rightGain;

        // Update the global maximum if this path is the best found so far
        maxSum = Math.max(maxSum, pathThroughNode);

        // Return the maximum gain if we continue from this node upward
        // We can only pick ONE branch (left or right) when going upward
        return root.data + Math.max(leftGain, rightGain);
    }

    /**
     * Entry point to find the maximum path sum in the tree.
     * 
     * @param root Root of the binary tree
     * @return Maximum path sum
     */
    static int maxPathSum(Node root) {
        // Initialize to the smallest possible value
        maxSum = Integer.MIN_VALUE;

        // Start the recursive computation from the root
        maxGain(root);

        // Return the answer
        return maxSum;
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
        Scanner sc = new Scanner(System.in);

        // Demo tree: -10
        // / \
        // 9 20
        // / \
        // 15 7
        // Best path: 15 -> 20 -> 7 = 42
        System.out.println("--- Maximum Path Sum ---");
        Integer[] treeData = { -10, 9, 20, null, null, 15, 7 };
        Node root = buildTree(treeData);

        // Compute and display the result
        int result = maxPathSum(root);
        System.out.println("Tree: [-10, 9, 20, null, null, 15, 7]");
        System.out.println("Maximum Path Sum: " + result);

        // Additional test: All negative values
        Integer[] treeData2 = { -3, -2, -1 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [-3, -2, -1]");
        System.out.println("Maximum Path Sum: " + maxPathSum(root2));

        sc.close();
    }
}
