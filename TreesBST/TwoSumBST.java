
import java.util.*;

/**
 * Two Sum in a BST (Pair with Given Sum)
 * 
 * Problem: Given a BST and a target sum, find if there exist two nodes
 * whose values add up to the target.
 * 
 * Approach (BST Iterator / Two Pointer):
 * 1. Use two BST iterators: one forward (inorder = ascending)
 * and one backward (reverse inorder = descending).
 * 2. Use two-pointer technique: if sum < target, advance forward;
 * if sum > target, advance backward.
 * 3. If sum == target, return true.
 * 
 * Time: O(n) | Space: O(h) for the two stacks
 */
public class TwoSumBST {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // BST Iterator that can iterate forward (inorder) or backward (reverse inorder)
    static class BSTIterator {
        Deque<Node> stack = new ArrayDeque<>();
        boolean reverse; // false = forward (ascending), true = backward (descending)

        BSTIterator(Node root, boolean reverse) {
            this.reverse = reverse;
            push(root); // Initialize by pushing the leftmost/rightmost path
        }

        // Push all nodes in one direction
        void push(Node node) {
            while (node != null) {
                stack.push(node);
                node = reverse ? node.right : node.left;
            }
        }

        // Get the next value
        int next() {
            Node node = stack.pop();
            // Push the opposite subtree's path
            push(reverse ? node.left : node.right);
            return node.data;
        }

        // Check if there are more elements
        boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    // Check if two nodes in the BST sum to the target
    static boolean findTarget(Node root, int target) {
        if (root == null)
            return false;

        // Forward iterator starts from the smallest value
        BSTIterator left = new BSTIterator(root, false);
        // Backward iterator starts from the largest value
        BSTIterator right = new BSTIterator(root, true);

        // Get the first values from both ends
        int l = left.next();
        int r = right.next();

        // Two-pointer approach
        while (l < r) {
            int sum = l + r;
            if (sum == target)
                return true;
            else if (sum < target)
                l = left.next(); // Need a larger value
            else
                r = right.next(); // Need a smaller value
        }

        return false;
    }

    static Node insert(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- Two Sum in BST ---");

        Node root = null;
        int[] vals = { 5, 3, 6, 2, 4, null, 7 };
        // Build BST properly by insertion
        int[] insertVals = { 5, 3, 6, 2, 4, 7 };
        for (int v : insertVals)
            root = insert(root, v);

        System.out.println("BST: [5, 3, 6, 2, 4, 7]");

        int target1 = 9;
        System.out.println("Target " + target1 + ": " + findTarget(root, target1));

        int target2 = 28;
        System.out.println("Target " + target2 + ": " + findTarget(root, target2));
    }
}
