
import java.util.*;

/**
 * Inorder Successor and Predecessor in a BST
 * 
 * Inorder Successor: Smallest node GREATER than the given key.
 * Inorder Predecessor: Largest node SMALLER than the given key.
 * 
 * Approach: Walk down the BST. If key < root, root could be successor (go
 * left).
 * If key > root, root could be predecessor (go right).
 * 
 * Time: O(h) | Space: O(1)
 */
public class BSTSuccessorPredecessor {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Find inorder successor: smallest value > key
    static Node inorderSuccessor(Node root, int key) {
        Node successor = null; // Track the best candidate
        while (root != null) {
            if (key < root.data) {
                // Current node is greater than key, could be successor
                successor = root;
                root = root.left; // Try to find a closer (smaller) successor
            } else {
                // Key >= root, successor must be in the right subtree
                root = root.right;
            }
        }
        return successor;
    }

    // Find inorder predecessor: largest value < key
    static Node inorderPredecessor(Node root, int key) {
        Node predecessor = null; // Track the best candidate
        while (root != null) {
            if (key > root.data) {
                // Current node is less than key, could be predecessor
                predecessor = root;
                root = root.right; // Try to find a closer (larger) predecessor
            } else {
                // Key <= root, predecessor must be in the left subtree
                root = root.left;
            }
        }
        return predecessor;
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
        System.out.println("--- BST Successor & Predecessor ---");
        // BST: 20, 8, 22, 4, 12, 10, 14
        Node root = null;
        int[] vals = { 20, 8, 22, 4, 12, 10, 14 };
        for (int v : vals)
            root = insert(root, v);

        int key = 12;
        Node succ = inorderSuccessor(root, key);
        Node pred = inorderPredecessor(root, key);
        System.out.println("Key: " + key);
        System.out.println("Successor:   " + (succ != null ? succ.data : "null"));
        System.out.println("Predecessor: " + (pred != null ? pred.data : "null"));

        key = 22;
        succ = inorderSuccessor(root, key);
        pred = inorderPredecessor(root, key);
        System.out.println("\nKey: " + key);
        System.out.println("Successor:   " + (succ != null ? succ.data : "null"));
        System.out.println("Predecessor: " + (pred != null ? pred.data : "null"));
    }
}
