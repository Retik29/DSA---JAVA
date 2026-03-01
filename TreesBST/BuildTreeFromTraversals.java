
/**
 * Construct Binary Tree from Inorder and Preorder Traversals
 * 
 * Problem: Given two integer arrays 'preorder' and 'inorder', where
 * preorder is the preorder traversal and inorder is the inorder traversal
 * of the same binary tree, construct and return the binary tree.
 * 
 * Key Insight:
 * - Preorder: Root is always the FIRST element.
 * - Inorder:  Elements to the LEFT of root form the left subtree.
 *             Elements to the RIGHT of root form the right subtree.
 * 
 * Approach:
 * 1. The first element of 'preorder' is the root of the tree.
 * 2. Find this root in 'inorder' (at index 'mid').
 * 3. Elements inorder[inStart..mid-1] form the left subtree.
 * 4. Elements inorder[mid+1..inEnd] form the right subtree.
 * 5. Use a HashMap for O(1) lookup of root's index in inorder.
 * 6. Recursively build left and right subtrees.
 * 
 * Time Complexity : O(n) - Each node is created once, HashMap gives O(1) lookup.
 * Space Complexity: O(n) - For the HashMap and recursion stack.
 */
import java.util.*;

public class BuildTreeFromTraversals {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // Global index to track the current root position in the preorder array
    static int preIndex;

    /**
     * Builds the binary tree from preorder and inorder traversals.
     * 
     * @param preorder Preorder traversal array
     * @param inorder  Inorder traversal array
     * @return Root of the constructed binary tree
     */
    static Node buildTree(int[] preorder, int[] inorder) {
        // HashMap to store value -> index mapping for the inorder array
        // This allows O(1) lookup of the root's position in inorder
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        // Initialize the preorder index to 0 (start from the first root)
        preIndex = 0;

        // Build the tree recursively
        return build(preorder, 0, inorder.length - 1, inMap);
    }

    /**
     * Recursive function to construct the tree.
     * 
     * @param preorder The preorder traversal array
     * @param inStart  Start index of the current inorder segment
     * @param inEnd    End index of the current inorder segment
     * @param inMap    HashMap for O(1) inorder index lookup
     * @return Root node of the subtree
     */
    static Node build(int[] preorder, int inStart, int inEnd, HashMap<Integer, Integer> inMap) {
        // Base case: no elements to construct the subtree
        if (inStart > inEnd)
            return null;

        // The current root is at preorder[preIndex]
        int rootVal = preorder[preIndex];
        preIndex++; // Move to the next element for subsequent calls

        // Create the root node
        Node root = new Node(rootVal);

        // Find the index of this root value in the inorder array
        int mid = inMap.get(rootVal);

        // Build left subtree from inorder[inStart..mid-1]
        // Must build LEFT subtree FIRST (preorder visits left before right)
        root.left = build(preorder, inStart, mid - 1, inMap);

        // Build right subtree from inorder[mid+1..inEnd]
        root.right = build(preorder, mid + 1, inEnd, inMap);

        return root;
    }

    /**
     * Utility: Inorder traversal to verify the constructed tree.
     */
    static void printInorder(Node root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    /**
     * Utility: Preorder traversal to verify the constructed tree.
     */
    static void printPreorder(Node root) {
        if (root == null)
            return;
        System.out.print(root.data + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        System.out.println("--- Build Tree from Inorder & Preorder ---");

        // Example:
        // Preorder: [3, 9, 20, 15, 7]
        // Inorder: [9, 3, 15, 20, 7]
        // Tree: 3
        // / \
        // 9 20
        // / \
        // 15 7
        int[] preorder = { 3, 9, 20, 15, 7 };
        int[] inorder = { 9, 3, 15, 20, 7 };

        Node root = buildTree(preorder, inorder);

        System.out.println("Input Preorder: " + Arrays.toString(preorder));
        System.out.println("Input Inorder:  " + Arrays.toString(inorder));

        System.out.print("\nVerification Inorder:  ");
        printInorder(root);

        System.out.print("\nVerification Preorder: ");
        printPreorder(root);
        System.out.println();
    }
}
