
/**
 * Boundary Traversal of a Binary Tree
 * 
 * Problem: Print all the boundary nodes of a binary tree in anti-clockwise
 * direction starting from the root.
 * 
 * The boundary includes:
 * 1. Left Boundary (top to bottom, excluding leaf nodes)
 * 2. Leaf Nodes (left to right)
 * 3. Right Boundary (bottom to top, excluding leaf nodes)
 * 
 * Approach:
 * 1. Add the root node (if it's not a leaf).
 * 2. Traverse the left boundary: keep going left; if left child is absent, go right.
 *    Stop before you hit a leaf.
 * 3. Traverse all leaf nodes from left to right using any DFS (Inorder works well).
 * 4. Traverse the right boundary in reverse: collect nodes going down the right edge,
 *    then reverse them before adding.
 * 
 * Time Complexity : O(n) - Each node is visited at most twice (once for boundary, once for leaf check).
 * Space Complexity: O(n) - For storing the result and recursion stack.
 */
import java.util.*;

public class BoundaryTraversal {

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
     * Checks if a node is a leaf node (no children).
     */
    static boolean isLeaf(Node node) {
        // A leaf node has no left and no right child
        return node.left == null && node.right == null;
    }

    /**
     * Adds the left boundary nodes (excluding root and leaves).
     * Traverses from root's left child downward.
     */
    static void addLeftBoundary(Node root, List<Integer> result) {
        // Start from the left child of the root
        Node current = root.left;

        while (current != null) {
            // Only add non-leaf nodes (leaves will be added separately)
            if (!isLeaf(current)) {
                result.add(current.data);
            }

            // Move to the left child if it exists, otherwise go right
            if (current.left != null) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    /**
     * Adds all leaf nodes from left to right using DFS.
     */
    static void addLeaves(Node root, List<Integer> result) {
        // Base case: null node
        if (root == null)
            return;

        // If this is a leaf, add its value
        if (isLeaf(root)) {
            result.add(root.data);
            return;
        }

        // Recurse on left subtree first (to get left-to-right order)
        addLeaves(root.left, result);

        // Then recurse on right subtree
        addLeaves(root.right, result);
    }

    /**
     * Adds the right boundary nodes in reverse order (excluding root and leaves).
     * Traverses from root's right child downward, then reverses.
     */
    static void addRightBoundary(Node root, List<Integer> result) {
        // Temporary list to store the right boundary before reversing
        List<Integer> temp = new ArrayList<>();

        // Start from the right child of the root
        Node current = root.right;

        while (current != null) {
            // Only add non-leaf nodes
            if (!isLeaf(current)) {
                temp.add(current.data);
            }

            // Move to the right child if it exists, otherwise go left
            if (current.right != null) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        // Add in reverse order (bottom to top)
        for (int i = temp.size() - 1; i >= 0; i--) {
            result.add(temp.get(i));
        }
    }

    /**
     * Main function to perform boundary traversal.
     * 
     * @param root Root of the binary tree
     * @return List of boundary nodes in anti-clockwise order
     */
    static List<Integer> boundaryTraversal(Node root) {
        // Result list to store boundary nodes
        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // Step 1: Add the root (if it's not a leaf)
        if (!isLeaf(root)) {
            result.add(root.data);
        }

        // Step 2: Add left boundary (top to bottom, excluding leaves)
        addLeftBoundary(root, result);

        // Step 3: Add all leaf nodes (left to right)
        addLeaves(root, result);

        // Step 4: Add right boundary (bottom to top, excluding leaves)
        addRightBoundary(root, result);

        return result;
    }

    /**
     * Builds a binary tree from a level-order array.
     * null entries represent absent nodes.
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
        // Demo tree: 1
        // / \
        // 2 3
        // / \ / \
        // 4 5 6 7
        // Boundary: [1, 2, 4, 5, 6, 7, 3]
        System.out.println("--- Boundary Traversal ---");
        Integer[] treeData = { 1, 2, 3, 4, 5, 6, 7 };
        Node root = buildTree(treeData);

        List<Integer> result = boundaryTraversal(root);
        System.out.println("Tree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Boundary: " + result);

        // Test 2: Skewed tree
        Integer[] treeData2 = { 1, 2, null, 3, null, null, null };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, null, 3]");
        System.out.println("Boundary: " + boundaryTraversal(root2));
    }
}
