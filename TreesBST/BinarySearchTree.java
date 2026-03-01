
/**
 * Binary Search Tree (BST)
 * 
 * A Binary Search Tree is a node-based binary tree data structure which has the 
 * following properties:
 * - The left subtree of a node contains only nodes with keys lesser than the node's key.
 * - The right subtree of a node contains only nodes with keys greater than the node's key.
 * - The left and right subtree each must also be a binary search tree.
 * 
 * Operations Included:
 * 1. Insert/Search/Delete: Standard CRUD operations.
 * 2. Kth Smallest/Largest: Finding nth element in sorted order.
 * 3. Validation: ensuring a tree satisfies BST properties.
 * 4. Floor/Ceiling: Closest values to the left and right of a key.
 * 5. LCA: Finding the shared ancestor most recently branched.
 * 
 * Time Complexity : O(h) for all operations, where h is height.
 * Space Complexity: O(h) due to recursion stack.
 */
import java.util.*;

public class BinarySearchTree {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Recursively inserts a new value into the BST.
     */
    static Node insert(Node root, int data) {
        // Base case: Find the correct spot for the new node
        if (root == null)
            return new Node(data);

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    /**
     * Searches for a key in the BST.
     */
    static boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.data == key)
            return true;

        // Exploit BST property to eliminate half of the tree
        return key < root.data ? search(root.left, key) : search(root.right, key);
    }

    /**
     * Deletes a node from the BST.
     * Cases:
     * 1. Node to be deleted is a leaf: Simply remove it.
     * 2. Node has one child: Replace node with its child.
     * 3. Node has two children: Replace node with its Inorder Successor
     * (smallest node in the right subtree) and delete the successor.
     */
    static Node delete(Node root, int key) {
        if (root == null)
            return null;

        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            // Found the node to delete

            // Case 1 & 2: Single child or Leaf
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            // Case 3: Two children
            // Find Inorder Successor (smallest in right subtree)
            Node successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            // Replace data
            root.data = successor.data;
            // Delete successor from right subtree
            root.right = delete(root.right, successor.data);
        }
        return root;
    }

    /**
     * Finds Kth smallest element using Inorder traversal (Left-Root-Right).
     */
    static int kthSmallest(Node root, int k) {
        int[] state = { 0, -1 }; // {count, result}
        inorderKth(root, k, state, true);
        return state[1];
    }

    /**
     * Finds Kth largest using Reverse-Inorder traversal (Right-Root-Left).
     */
    static int kthLargest(Node root, int k) {
        int[] state = { 0, -1 };
        inorderKth(root, k, state, false);
        return state[1];
    }

    /**
     * Unified Helper for Kth elements.
     */
    private static void inorderKth(Node root, int k, int[] state, boolean ascending) {
        if (root == null || state[0] >= k)
            return;

        inorderKth(ascending ? root.left : root.right, k, state, ascending);

        if (++state[0] == k) {
            state[1] = root.data;
            return;
        }

        inorderKth(ascending ? root.right : root.left, k, state, ascending);
    }

    /**
     * Validates if a tree is a valid BST using range-based checking.
     */
    static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node root, long min, long max) {
        if (root == null)
            return true;

        // Node data must be strictly between min and max
        if (root.data <= min || root.data >= max)
            return false;

        // Left child's max value is root's data
        // Right child's min value is root's data
        return validate(root.left, min, root.data) &&
                validate(root.right, root.data, max);
    }

    /**
     * Finds Lowest Common Ancestor (LCA) in a BST.
     * Logic: LCA is the node where p and q split (one goes left, one goes right).
     */
    static Node lca(Node root, int p, int q) {
        if (root == null)
            return null;

        // If both nodes are smaller than root, go left
        if (p < root.data && q < root.data) {
            return lca(root.left, p, q);
        }
        // If both nodes are larger than root, go right
        if (p > root.data && q > root.data) {
            return lca(root.right, p, q);
        }
        // If one is smaller and one is larger, current root is the LCA
        return root;
    }

    /**
     * Finds Floor: Largest value in BST <= key.
     */
    static int floor(Node root, int key) {
        int res = -1;
        while (root != null) {
            if (root.data == key)
                return key;
            if (root.data < key) {
                res = root.data; // potential floor
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return res;
    }

    /**
     * Finds Ceiling: Smallest value in BST >= key.
     */
    static int ceiling(Node root, int key) {
        int res = -1;
        while (root != null) {
            if (root.data == key)
                return key;
            if (root.data > key) {
                res = root.data; // potential ceiling
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return res;
    }

    /**
     * Standard Inorder traversal for displaying the BST.
     */
    static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root = null;

        // Interactive BST building
        System.out.println("--- BST Interactive Demo ---");
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        System.out.print("Enter " + n + " values to insert: ");
        for (int i = 0; i < n; i++)
            root = insert(root, sc.nextInt());

        System.out.print("\nBST Inorder traversal (sorted): ");
        inorder(root);
        System.out.println();

        // BST Analysis
        System.out.println("Valid BST Check: " + (isValidBST(root) ? "Success" : "Failed"));

        System.out.print("\nEnter k for Kth small/large: ");
        int k = sc.nextInt();
        System.out.println(k + "-th Smallest: " + kthSmallest(root, k));
        System.out.println(k + "-th Largest:  " + kthLargest(root, k));

        System.out.print("\nEnter key for Floor/Ceiling: ");
        int fcKey = sc.nextInt();
        System.out.println("Floor for " + fcKey + ": " + floor(root, fcKey));
        System.out.println("Ceiling for " + fcKey + ": " + ceiling(root, fcKey));

        sc.close();
    }
}
