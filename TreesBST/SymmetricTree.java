
/**
 * Symmetric Tree (Mirror of Itself)
 * 
 * Problem: Given the root of a binary tree, check whether it is a mirror
 * of itself (i.e., symmetric around its center).
 * 
 * Approach:
 * 1. A tree is symmetric if the left subtree is a mirror reflection of the right subtree.
 * 2. Two trees are mirror reflections if:
 *    a. Their root values are the same.
 *    b. The right subtree of one is a mirror of the left subtree of the other.
 * 3. Use a recursive function that compares two nodes at a time.
 * 
 * Time Complexity : O(n) - Each node is visited once.
 * Space Complexity: O(h) - Recursion stack, where h is the height of the tree.
 */
import java.util.*;

public class SymmetricTree {

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
     * Checks if the binary tree is symmetric (mirror of itself).
     * 
     * @param root Root of the binary tree
     * @return true if symmetric, false otherwise
     */
    static boolean isSymmetric(Node root) {
        // An empty tree is symmetric
        if (root == null)
            return true;

        // Check if left and right subtrees are mirror images
        return isMirror(root.left, root.right);
    }

    /**
     * Recursively checks if two subtrees are mirror images of each other.
     * 
     * @param left  Root of the left subtree
     * @param right Root of the right subtree
     * @return true if the subtrees are mirrors, false otherwise
     */
    static boolean isMirror(Node left, Node right) {
        // Both null: symmetric (both sides are empty)
        if (left == null && right == null)
            return true;

        // One is null, the other is not: not symmetric
        if (left == null || right == null)
            return false;

        // Values must be equal, AND:
        // - left's left must mirror right's right
        // - left's right must mirror right's left
        return (left.data == right.data)
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
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
        // Test 1: Symmetric tree
        // 1
        // / \
        // 2 2
        // / \ / \
        // 3 4 4 3
        System.out.println("--- Symmetric Tree Check ---");
        Integer[] treeData1 = { 1, 2, 2, 3, 4, 4, 3 };
        Node root1 = buildTree(treeData1);
        System.out.println("Tree: [1, 2, 2, 3, 4, 4, 3]");
        System.out.println("Is Symmetric? " + isSymmetric(root1));

        // Test 2: Non-symmetric tree
        // 1
        // / \
        // 2 2
        // \ \
        // 3 3
        Integer[] treeData2 = { 1, 2, 2, null, 3, null, 3 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, 2, null, 3, null, 3]");
        System.out.println("Is Symmetric? " + isSymmetric(root2));

        // Test 3: Single node
        Integer[] treeData3 = { 1 };
        Node root3 = buildTree(treeData3);
        System.out.println("\nTree: [1]");
        System.out.println("Is Symmetric? " + isSymmetric(root3));
    }
}
