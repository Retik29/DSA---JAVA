
/**
 * Construct Binary Tree from Inorder and Postorder Traversals
 * 
 * Problem: Given two integer arrays 'inorder' and 'postorder', construct
 * and return the binary tree.
 * 
 * Key Insight:
 * - Postorder: Root is always the LAST element.
 * - Inorder:   Elements to the LEFT of root form the left subtree.
 *              Elements to the RIGHT of root form the right subtree.
 * 
 * Approach:
 * 1. The last element of 'postorder' is the root.
 * 2. Find this root in 'inorder' (at index 'mid').
 * 3. Build the RIGHT subtree first (postorder visits right before left in reverse).
 * 4. Then build the LEFT subtree.
 * 5. Use a HashMap for O(1) lookup.
 * 
 * Time Complexity : O(n) - Each node is created once.
 * Space Complexity: O(n) - For the HashMap and recursion stack.
 */
import java.util.*;

public class BuildTreeFromPostorder {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // Global index to track the current root position in the postorder array
    // We start from the end and move backward
    static int postIndex;

    /**
     * Builds the binary tree from inorder and postorder traversals.
     * 
     * @param inorder   Inorder traversal array
     * @param postorder Postorder traversal array
     * @return Root of the constructed binary tree
     */
    static Node buildTree(int[] inorder, int[] postorder) {
        // HashMap to store value -> index mapping for the inorder array
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        // Start from the last index of postorder (the root)
        postIndex = postorder.length - 1;

        // Build the tree recursively
        return build(postorder, 0, inorder.length - 1, inMap);
    }

    /**
     * Recursive function to construct the tree.
     * 
     * @param postorder The postorder traversal array
     * @param inStart   Start index of the current inorder segment
     * @param inEnd     End index of the current inorder segment
     * @param inMap     HashMap for O(1) inorder index lookup
     * @return Root node of the subtree
     */
    static Node build(int[] postorder, int inStart, int inEnd, HashMap<Integer, Integer> inMap) {
        // Base case: no elements to construct the subtree
        if (inStart > inEnd)
            return null;

        // The current root is at postorder[postIndex]
        int rootVal = postorder[postIndex];
        postIndex--; // Move backward for the next call

        // Create the root node
        Node root = new Node(rootVal);

        // Find the index of this root value in the inorder array
        int mid = inMap.get(rootVal);

        // IMPORTANT: Build RIGHT subtree FIRST
        // In postorder (Left, Right, Root), when reading from the end,
        // the right child comes before the left child
        root.right = build(postorder, mid + 1, inEnd, inMap);

        // Then build the LEFT subtree
        root.left = build(postorder, inStart, mid - 1, inMap);

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
     * Utility: Postorder traversal to verify the constructed tree.
     */
    static void printPostorder(Node root) {
        if (root == null)
            return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        System.out.println("--- Build Tree from Inorder & Postorder ---");

        // Example:
        // Inorder: [9, 3, 15, 20, 7]
        // Postorder: [9, 15, 7, 20, 3]
        // Tree: 3
        // / \
        // 9 20
        // / \
        // 15 7
        int[] inorder = { 9, 3, 15, 20, 7 };
        int[] postorder = { 9, 15, 7, 20, 3 };

        Node root = buildTree(inorder, postorder);

        System.out.println("Input Inorder:   " + Arrays.toString(inorder));
        System.out.println("Input Postorder: " + Arrays.toString(postorder));

        System.out.print("\nVerification Inorder:   ");
        printInorder(root);

        System.out.print("\nVerification Postorder: ");
        printPostorder(root);
        System.out.println();
    }
}
