
// Diameter of a Binary Tree — longest path between any two nodes
// Time: O(n) | Space: O(h)
import java.util.*;

public class DiameterOfTree {

    // Node class to represent each node in the binary tree
    static class Node {
        int data; // value stored in this node
        Node left, right; // pointers to left and right children

        // Constructor to create a new node with the given value
        Node(int data) {
            this.data = data;
        }
    }

    // Static variable to store the maximum diameter found so far across all
    // recursive calls
    static int diameter;

    // Wrapper function to compute the diameter (longest path between any two nodes)
    static int diameterOfTree(Node root) {
        diameter = 0; // reset diameter before each computation
        heightForDia(root); // compute height while updating diameter
        return diameter; // return the maximum diameter found
    }

    // Helper function — computes height of subtree while updating diameter at each
    // node
    private static int heightForDia(Node root) {
        // Base case: null node has height 0
        if (root == null)
            return 0;
        // Recursively compute left subtree height
        int leftHeight = heightForDia(root.left);
        // Recursively compute right subtree height
        int rightHeight = heightForDia(root.right);
        // Diameter through this node = leftHeight + rightHeight; update if it's the max
        // so far
        diameter = Math.max(diameter, leftHeight + rightHeight);
        // Return height of this subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Build tree from level-order input (-1 = null)
    static Node buildTree(Scanner scanner) {
        // Read root value from user
        System.out.print("Enter root value (-1 for null): ");
        int value = scanner.nextInt();
        // If root itself is -1, tree is empty
        if (value == -1)
            return null;
        // Create root node
        Node root = new Node(value);
        // Queue for level-order construction
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        // Process each node in the queue and attach its children
        while (!queue.isEmpty()) {
            // Take next node whose children we need to set
            Node current = queue.poll();
            // Read and attach left child
            System.out.print("Enter left child of " + current.data + " (-1 for null): ");
            int leftValue = scanner.nextInt();
            if (leftValue != -1) {
                current.left = new Node(leftValue); // create left child
                queue.add(current.left); // enqueue for further processing
            }
            // Read and attach right child
            System.out.print("Enter right child of " + current.data + " (-1 for null): ");
            int rightValue = scanner.nextInt();
            if (rightValue != -1) {
                current.right = new Node(rightValue); // create right child
                queue.add(current.right); // enqueue for further processing
            }
        }
        return root; // return the fully constructed tree
    }

    public static void main(String[] args) {
        // Create scanner for reading input
        Scanner scanner = new Scanner(System.in);

        // Build the binary tree from user input
        Node root = buildTree(scanner);

        // Compute and print the diameter
        System.out.println("\nDiameter: " + diameterOfTree(root));

        // Close the scanner to release resources
        scanner.close();
    }
}
