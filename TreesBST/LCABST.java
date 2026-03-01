
/**
 * Lowest Common Ancestor (LCA) of a Binary Search Tree
 * 
 * Problem: Given a BST and two nodes p and q, find the lowest
 * common ancestor. In a BST, we can exploit the ordering property.
 * 
 * Key Insight (BST Property):
 * - If both p and q are less than root, LCA lies in the LEFT subtree.
 * - If both p and q are greater than root, LCA lies in the RIGHT subtree.
 * - If one is less and the other is greater (or one equals root),
 *   then the current root IS the LCA.
 * 
 * Iterative Approach (preferred for BST):
 * Simply walk down the tree following the BST property.
 * No recursion needed — O(1) extra space.
 * 
 * Time Complexity : O(h) - Where h is the height of the BST.
 * Space Complexity: O(1) - Iterative, no extra space.
 */
import java.util.*;

public class LCABST {

    // Standard BST node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    /**
     * Finds the LCA of two nodes in a BST (Iterative).
     * 
     * @param root Root of the BST
     * @param p    Value of the first node
     * @param q    Value of the second node
     * @return The LCA node
     */
    static Node lcaBST(Node root, int p, int q) {
        // Walk down the tree
        while (root != null) {
            // If both values are smaller, LCA must be in the left subtree
            if (p < root.data && q < root.data) {
                root = root.left;
            }
            // If both values are larger, LCA must be in the right subtree
            else if (p > root.data && q > root.data) {
                root = root.right;
            }
            // Split point found — current root is the LCA
            else {
                return root;
            }
        }

        // Should not reach here if both nodes exist in the BST
        return null;
    }

    /**
     * Inserts a value into the BST.
     */
    static Node insert(Node root, int data) {
        // Base case: found the correct spot
        if (root == null)
            return new Node(data);

        // Go left if data is smaller, right if larger
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }

        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- LCA in a BST (Iterative) ---");

        // Build BST: 6
        // / \
        // 2 8
        // / \ / \
        // 0 4 7 9
        // / \
        // 3 5
        Node root = null;
        int[] values = { 6, 2, 8, 0, 4, 7, 9, 3, 5 };
        for (int v : values) {
            root = insert(root, v);
        }

        // Test 1: LCA of 2 and 8
        Node lca1 = lcaBST(root, 2, 8);
        System.out.println("LCA of 2 and 8: " + (lca1 != null ? lca1.data : "null"));

        // Test 2: LCA of 2 and 4
        Node lca2 = lcaBST(root, 2, 4);
        System.out.println("LCA of 2 and 4: " + (lca2 != null ? lca2.data : "null"));

        // Test 3: LCA of 3 and 5
        Node lca3 = lcaBST(root, 3, 5);
        System.out.println("LCA of 3 and 5: " + (lca3 != null ? lca3.data : "null"));

        // Test 4: LCA of 0 and 5
        Node lca4 = lcaBST(root, 0, 5);
        System.out.println("LCA of 0 and 5: " + (lca4 != null ? lca4.data : "null"));
    }
}
