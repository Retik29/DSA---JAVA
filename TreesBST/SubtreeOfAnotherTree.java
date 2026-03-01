
/**
 * Subtree of Another Tree
 * 
 * Problem: Given two binary trees 'root' and 'subRoot', return true if
 * there is a subtree of 'root' with the same structure and node values
 * as 'subRoot'.
 * 
 * Approach:
 * 1. For each node in the main tree, check if the subtree rooted at
 *    that node is identical to 'subRoot'.
 * 2. Two trees are identical if they have the same structure and same values.
 * 3. Traverse the main tree recursively and at each node, check identity.
 * 
 * Time Complexity : O(m * n) - For each of the m nodes in root, we might
 *                   compare up to n nodes of subRoot.
 * Space Complexity: O(h) - Recursion stack, where h is the height of root.
 */
import java.util.*;

public class SubtreeOfAnotherTree {

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
     * Checks if subRoot is a subtree of root.
     * 
     * @param root    Root of the main tree
     * @param subRoot Root of the potential subtree
     * @return true if subRoot is a subtree of root
     */
    static boolean isSubtree(Node root, Node subRoot) {
        // If main tree is null, subRoot can't be a subtree (unless subRoot is also
        // null)
        if (root == null)
            return subRoot == null;

        // Check if the tree rooted at current node is identical to subRoot
        if (isIdentical(root, subRoot))
            return true;

        // Recursively check in left and right subtrees
        return isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot);
    }

    /**
     * Checks if two trees are identical (same structure and values).
     * 
     * @param t1 Root of the first tree
     * @param t2 Root of the second tree
     * @return true if both trees are identical
     */
    static boolean isIdentical(Node t1, Node t2) {
        // Both null: identical
        if (t1 == null && t2 == null)
            return true;

        // One null, the other not: not identical
        if (t1 == null || t2 == null)
            return false;

        // Values must match, and both subtrees must be identical
        return (t1.data == t2.data)
                && isIdentical(t1.left, t2.left)
                && isIdentical(t1.right, t2.right);
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
        System.out.println("--- Subtree of Another Tree ---");

        // Main tree: 3
        // / \
        // 4 5
        // / \
        // 1 2
        Integer[] rootArr = { 3, 4, 5, 1, 2 };
        Node root = buildTree(rootArr);

        // SubRoot 1: yes, it IS a subtree
        // 4
        // / \
        // 1 2
        Integer[] subArr1 = { 4, 1, 2 };
        Node subRoot1 = buildTree(subArr1);

        System.out.println("Main Tree: [3, 4, 5, 1, 2]");
        System.out.println("Sub Tree:  [4, 1, 2]");
        System.out.println("Is Subtree? " + isSubtree(root, subRoot1));

        // SubRoot 2: no, it is NOT a subtree (2 has a child in subRoot2 but not in
        // root)
        // 4
        // / \
        // 1 2
        // /
        // 0
        Integer[] subArr2 = { 4, 1, 2, null, null, 0 };
        Node subRoot2 = buildTree(subArr2);

        System.out.println("\nSub Tree:  [4, 1, 2, null, null, 0]");
        System.out.println("Is Subtree? " + isSubtree(root, subRoot2));
    }
}
