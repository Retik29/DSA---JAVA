
import java.util.*;

/**
 * Largest BST Subtree in a Binary Tree
 * 
 * Problem: Given a binary tree, find the size of the largest subtree
 * which is also a Binary Search Tree (BST).
 * 
 * Approach (Bottom-Up):
 * For each node, return a tuple: (isBST, size, min, max).
 * - If both left and right subtrees are BSTs, and current node's value
 * is in valid range, then current subtree is also a BST.
 * - Otherwise, return that it's not a BST but propagate the max size found.
 * 
 * Time: O(n) | Space: O(h)
 */
public class LargestBSTSubtree {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Info class to store subtree information
    static class Info {
        boolean isBST; // Is this subtree a valid BST?
        int size; // Size of the largest BST in this subtree
        int min; // Minimum value in this subtree
        int max; // Maximum value in this subtree

        Info(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }

    // Find the size of the largest BST subtree
    static int largestBST(Node root) {
        return solve(root).size;
    }

    // Bottom-up recursive function
    static Info solve(Node root) {
        // Base case: null node is a valid BST of size 0
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // Get info from left and right subtrees
        Info left = solve(root.left);
        Info right = solve(root.right);

        // Check if current subtree forms a valid BST
        // Conditions: both subtrees are BSTs, root > left.max, root < right.min
        if (left.isBST && right.isBST
                && root.data > left.max && root.data < right.min) {
            // Current subtree is a valid BST
            int size = 1 + left.size + right.size;
            int min = Math.min(root.data, left.min);
            int max = Math.max(root.data, right.max);
            return new Info(true, size, min, max);
        }

        // Current subtree is NOT a BST
        // Return the larger BST found in either subtree
        return new Info(false, Math.max(left.size, right.size), 0, 0);
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
        System.out.println("--- Largest BST Subtree ---");
        // 10
        // / \
        // 5 15
        // / \ \
        // 1 8 7 <-- 7 violates BST (7 < 15)
        // Largest BST: subtree rooted at 5 (nodes: 1, 5, 8), size = 3
        Integer[] arr = { 10, 5, 15, 1, 8, null, 7 };
        Node root = buildTree(arr);

        System.out.println("Tree: [10, 5, 15, 1, 8, null, 7]");
        System.out.println("Largest BST Subtree Size: " + largestBST(root));
    }
}
