
/**
 * Root to Leaf Path Problems in a Binary Tree
 * 
 * Problem Set:
 * 1. Print all root-to-leaf paths.
 * 2. Check if a root-to-leaf path with a given sum exists.
 * 3. Find all root-to-leaf paths with a given target sum.
 * 
 * Approach:
 * - Use DFS (backtracking): maintain a current path list.
 * - Add the current node to the path.
 * - If it's a leaf, process the path (print it, check sum, etc.).
 * - Recurse on left and right children.
 * - Backtrack: remove the current node from the path.
 * 
 * Time Complexity : O(n) for each traversal.
 * Space Complexity: O(h) for the recursion stack and path list, where h is height.
 */
import java.util.*;

public class RootToLeafPaths {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // ==================== 1. Print All Root-to-Leaf Paths ====================

    /**
     * Prints all root-to-leaf paths in the binary tree.
     * 
     * @param root Root of the binary tree
     */
    static void printAllPaths(Node root) {
        // List to store the current path
        List<Integer> path = new ArrayList<>();
        // Start DFS from the root
        printPathsDFS(root, path);
    }

    /**
     * DFS traversal to build and print paths.
     */
    static void printPathsDFS(Node root, List<Integer> path) {
        // Base case: null node
        if (root == null)
            return;

        // Add current node to the path
        path.add(root.data);

        // If it's a leaf node, print the entire path
        if (root.left == null && root.right == null) {
            System.out.println("  Path: " + path);
        } else {
            // Not a leaf, continue DFS
            printPathsDFS(root.left, path);
            printPathsDFS(root.right, path);
        }

        // Backtrack: remove the current node before returning to the parent
        path.remove(path.size() - 1);
    }

    // ==================== 2. Path Sum Check (Boolean) ====================

    /**
     * Checks if any root-to-leaf path has the given target sum.
     * 
     * @param root   Root of the binary tree
     * @param target The target sum to check for
     * @return true if such a path exists, false otherwise
     */
    static boolean hasPathSum(Node root, int target) {
        // Base case: null node contributes nothing
        if (root == null)
            return false;

        // Subtract the current node's value from the target
        int remaining = target - root.data;

        // If this is a leaf and the remaining sum is 0, we found a valid path
        if (root.left == null && root.right == null) {
            return remaining == 0;
        }

        // Recurse on left and right subtrees with the reduced target
        // If either subtree has a valid path, return true
        return hasPathSum(root.left, remaining)
                || hasPathSum(root.right, remaining);
    }

    // ==================== 3. Find All Paths with Target Sum ====================

    /**
     * Finds all root-to-leaf paths that sum to the target.
     * 
     * @param root   Root of the binary tree
     * @param target The target sum
     * @return List of all valid paths
     */
    static List<List<Integer>> pathSum(Node root, int target) {
        // Result list to store all valid paths
        List<List<Integer>> result = new ArrayList<>();

        // Current path being explored
        List<Integer> currentPath = new ArrayList<>();

        // Start DFS
        findPaths(root, target, currentPath, result);

        return result;
    }

    /**
     * DFS to find all paths summing to the target.
     */
    static void findPaths(Node root, int remaining, List<Integer> path, List<List<Integer>> result) {
        // Base case: null node
        if (root == null)
            return;

        // Add current node to the path
        path.add(root.data);

        // Update the remaining sum
        remaining -= root.data;

        // If this is a leaf and remaining sum is 0, add this path to results
        if (root.left == null && root.right == null && remaining == 0) {
            // Create a copy of the path (important! don't add the same reference)
            result.add(new ArrayList<>(path));
        } else {
            // Continue DFS on both subtrees
            findPaths(root.left, remaining, path, result);
            findPaths(root.right, remaining, path, result);
        }

        // Backtrack: remove the current node from the path
        path.remove(path.size() - 1);
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
        // Demo tree: 5
        // / \
        // 4 8
        // / / \
        // 11 13 4
        // / \ / \
        // 7 2 5 1
        System.out.println("--- Root to Leaf Paths ---");
        Integer[] treeData = { 5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1 };
        Node root = buildTree(treeData);

        // 1. Print all root-to-leaf paths
        System.out.println("\n1. All Root-to-Leaf Paths:");
        printAllPaths(root);

        // 2. Check if a path with sum 22 exists
        int targetSum = 22;
        System.out.println("\n2. Has Path Sum " + targetSum + "? " + hasPathSum(root, targetSum));

        // 3. Find all paths with sum 22
        System.out.println("\n3. All Paths with Sum " + targetSum + ": " + pathSum(root, targetSum));
    }
}
