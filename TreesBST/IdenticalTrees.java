
/**
 * Check if Two Binary Trees are Identical
 * 
 * Problem: Given two binary trees, check if they are structurally identical
 * and the nodes have the same values.
 * 
 * Approach:
 * 1. If both trees are null, they are identical.
 * 2. If one is null and the other is not, they are not identical.
 * 3. If the root values differ, they are not identical.
 * 4. Recursively check if left subtrees and right subtrees are identical.
 * 
 * Time Complexity : O(n) - Visit every node once (n = min of nodes in both trees).
 * Space Complexity: O(h) - Recursion stack, where h is the height of the shorter tree.
 */
import java.util.*;

public class IdenticalTrees {

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
     * Checks if two binary trees are identical.
     * 
     * @param root1 Root of the first tree
     * @param root2 Root of the second tree
     * @return true if both trees are identical, false otherwise
     */
    static boolean isIdentical(Node root1, Node root2) {
        // Both are null: identical (both empty)
        if (root1 == null && root2 == null)
            return true;

        // One is null, the other is not: not identical
        if (root1 == null || root2 == null)
            return false;

        // Check current node values and recurse on subtrees
        // All three conditions must be true:
        // 1. Current values match
        // 2. Left subtrees are identical
        // 3. Right subtrees are identical
        return (root1.data == root2.data)
                && isIdentical(root1.left, root2.left)
                && isIdentical(root1.right, root2.right);
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
        System.out.println("--- Identical Trees Check ---");

        // Test 1: Identical trees
        Integer[] arr1 = { 1, 2, 3 };
        Integer[] arr2 = { 1, 2, 3 };
        Node tree1 = buildTree(arr1);
        Node tree2 = buildTree(arr2);
        System.out.println("Tree1: [1, 2, 3], Tree2: [1, 2, 3]");
        System.out.println("Identical? " + isIdentical(tree1, tree2));

        // Test 2: Different values
        Integer[] arr3 = { 1, 2, 3 };
        Integer[] arr4 = { 1, 2, 4 };
        Node tree3 = buildTree(arr3);
        Node tree4 = buildTree(arr4);
        System.out.println("\nTree1: [1, 2, 3], Tree2: [1, 2, 4]");
        System.out.println("Identical? " + isIdentical(tree3, tree4));

        // Test 3: Different structures
        Integer[] arr5 = { 1, 2, null };
        Integer[] arr6 = { 1, null, 2 };
        Node tree5 = buildTree(arr5);
        Node tree6 = buildTree(arr6);
        System.out.println("\nTree1: [1, 2, null], Tree2: [1, null, 2]");
        System.out.println("Identical? " + isIdentical(tree5, tree6));
    }
}
