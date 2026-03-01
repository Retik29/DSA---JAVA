
import java.util.*;

/**
 * Count Nodes in a Complete Binary Tree
 * 
 * A complete binary tree has all levels fully filled except possibly the last,
 * which is filled from left to right.
 * 
 * Naive: O(n) - count all nodes.
 * Optimized: O(log^2 n) - exploit the complete tree property.
 * 
 * Optimized Approach:
 * 1. Compute left height (always go left) and right height (always go right).
 * 2. If leftHeight == rightHeight, the tree is a PERFECT binary tree.
 * Node count = 2^h - 1 (no need to recurse further).
 * 3. Otherwise, recurse on left and right subtrees: 1 + left + right.
 * 
 * Time: O(log^2 n) | Space: O(log n) recursion stack
 */
public class CountNodesComplete {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Count nodes in a complete binary tree (optimized)
    static int countNodes(Node root) {
        // Base case
        if (root == null)
            return 0;

        // Compute left height (go all the way left)
        int leftHeight = 0;
        Node temp = root;
        while (temp != null) {
            leftHeight++;
            temp = temp.left;
        }

        // Compute right height (go all the way right)
        int rightHeight = 0;
        temp = root;
        while (temp != null) {
            rightHeight++;
            temp = temp.right;
        }

        // If heights are equal, it's a perfect binary tree
        // Total nodes = 2^h - 1
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1; // 2^h - 1 using bit shift
        }

        // Otherwise, recurse on both subtrees
        return 1 + countNodes(root.left) + countNodes(root.right);
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
        System.out.println("--- Count Nodes in Complete Binary Tree ---");

        // Complete binary tree: [1, 2, 3, 4, 5, 6]
        Integer[] arr = { 1, 2, 3, 4, 5, 6 };
        Node root = buildTree(arr);
        System.out.println("Tree: [1, 2, 3, 4, 5, 6]");
        System.out.println("Node Count: " + countNodes(root));

        // Perfect binary tree: [1, 2, 3, 4, 5, 6, 7]
        Integer[] arr2 = { 1, 2, 3, 4, 5, 6, 7 };
        Node root2 = buildTree(arr2);
        System.out.println("\nTree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Node Count: " + countNodes(root2));
    }
}
