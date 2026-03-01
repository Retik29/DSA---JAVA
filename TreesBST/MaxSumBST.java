
import java.util.*;

/**
 * Maximum Sum BST in a Binary Tree
 * 
 * Problem: Given a binary tree, find the maximum sum of all keys of any
 * subtree which is also a Binary Search Tree (BST).
 * 
 * Approach (Bottom-Up, similar to Largest BST Subtree):
 * For each node, return: (isBST, sum, min, max).
 * If the subtree is a valid BST, update the global maxSum with its sum.
 * 
 * Time: O(n) | Space: O(h)
 */
public class MaxSumBST {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    static class Info {
        boolean isBST; // Is this subtree a valid BST?
        int sum; // Sum of all nodes in this subtree
        int min; // Minimum value in this subtree
        int max; // Maximum value in this subtree

        Info(boolean isBST, int sum, int min, int max) {
            this.isBST = isBST;
            this.sum = sum;
            this.min = min;
            this.max = max;
        }
    }

    // Global variable to track the maximum BST sum found
    static int maxSum;

    static int maxSumBST(Node root) {
        maxSum = 0; // At minimum, an empty BST has sum 0
        solve(root);
        return maxSum;
    }

    static Info solve(Node root) {
        // Base case: null is a valid BST with sum 0
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // Get info from children
        Info left = solve(root.left);
        Info right = solve(root.right);

        // Check if current subtree is a valid BST
        if (left.isBST && right.isBST
                && root.data > left.max && root.data < right.min) {
            // Valid BST — compute sum
            int sum = root.data + left.sum + right.sum;
            // Update global maximum
            maxSum = Math.max(maxSum, sum);
            int min = Math.min(root.data, left.min);
            int max = Math.max(root.data, right.max);
            return new Info(true, sum, min, max);
        }

        // Not a valid BST
        return new Info(false, 0, 0, 0);
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
        System.out.println("--- Maximum Sum BST in Binary Tree ---");
        // 1
        // / \
        // 4 3
        // / \ \
        // 2 4 5
        // \
        // 6
        Integer[] arr = { 1, 4, 3, 2, 4, null, 5, null, null, null, null, null, 6 };
        Node root = buildTree(arr);
        System.out.println("Max Sum BST: " + maxSumBST(root));
    }
}
