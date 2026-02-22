
/**
 * Binary Search Tree — Insert, Delete, Search, Kth Smallest/Largest,
 * Validate BST, LCA, Floor & Ceiling, Inorder Traversal
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

    // Recursive insert into BST
    static Node insert(Node root, int data) {
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        return root;
    }

    // Search for a key in BST
    static boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.data == key)
            return true;
        return key < root.data ? search(root.left, key) : search(root.right, key);
    }

    // Delete a key from BST
    static Node delete(Node root, int key) {
        if (root == null)
            return null;
        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            // Leaf or single-child cases
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            // Two children: replace with inorder successor
            Node succ = root.right;
            while (succ.left != null)
                succ = succ.left;
            root.data = succ.data;
            root.right = delete(root.right, succ.data);
        }
        return root;
    }

    // Kth smallest via inorder traversal (left → root → right)
    static int kthSmallest(Node root, int k) {
        int[] state = { 0, -1 }; // state[0]=count, state[1]=result
        inorderKth(root, k, state, true);
        return state[1];
    }

    // Kth largest via reverse-inorder traversal (right → root → left)
    static int kthLargest(Node root, int k) {
        int[] state = { 0, -1 };
        inorderKth(root, k, state, false);
        return state[1];
    }

    // Unified helper: ascending if 'asc' is true, descending otherwise
    private static void inorderKth(Node root, int k, int[] state, boolean asc) {
        if (root == null || state[0] >= k)
            return;
        inorderKth(asc ? root.left : root.right, k, state, asc);
        if (++state[0] == k) {
            state[1] = root.data;
            return;
        }
        inorderKth(asc ? root.right : root.left, k, state, asc);
    }

    // Validate BST using min-max range
    static boolean isValidBST(Node root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node root, long min, long max) {
        if (root == null)
            return true;
        if (root.data <= min || root.data >= max)
            return false;
        return validate(root.left, min, root.data) && validate(root.right, root.data, max);
    }

    // Lowest Common Ancestor in BST
    static Node lca(Node root, int p, int q) {
        if (root == null)
            return null;
        if (p < root.data && q < root.data)
            return lca(root.left, p, q);
        if (p > root.data && q > root.data)
            return lca(root.right, p, q);
        return root; // split point
    }

    // Floor: largest value <= key
    static int floor(Node root, int key) {
        int res = -1;
        while (root != null) {
            if (root.data == key)
                return key;
            if (root.data < key) {
                res = root.data;
                root = root.right;
            } else
                root = root.left;
        }
        return res;
    }

    // Ceiling: smallest value >= key
    static int ceiling(Node root, int key) {
        int res = -1;
        while (root != null) {
            if (root.data == key)
                return key;
            if (root.data > key) {
                res = root.data;
                root = root.left;
            } else
                root = root.right;
        }
        return res;
    }

    // Inorder traversal (sorted order)
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

        // Build BST from user input
        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            root = insert(root, sc.nextInt());

        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();

        // Search
        System.out.print("Enter key to search: ");
        int key = sc.nextInt();
        System.out.println("Found: " + search(root, key));

        // Validation
        System.out.println("Is valid BST: " + isValidBST(root));

        // Kth smallest & largest
        System.out.print("Enter k for kth smallest/largest: ");
        int k = sc.nextInt();
        System.out.println("Kth smallest: " + kthSmallest(root, k));
        System.out.println("Kth largest:  " + kthLargest(root, k));

        // LCA
        System.out.print("Enter two nodes for LCA: ");
        int p = sc.nextInt(), q = sc.nextInt();
        Node lcaNode = lca(root, p, q);
        System.out.println("LCA: " + (lcaNode != null ? lcaNode.data : "N/A"));

        // Floor & Ceiling
        System.out.print("Enter key for floor/ceiling: ");
        int fc = sc.nextInt();
        System.out.println("Floor: " + floor(root, fc));
        System.out.println("Ceiling: " + ceiling(root, fc));

        // Delete
        System.out.print("Enter key to delete: ");
        int del = sc.nextInt();
        root = delete(root, del);
        System.out.print("Inorder after deletion: ");
        inorder(root);
        System.out.println();

        sc.close();
    }
}
