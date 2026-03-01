
/**
 * Serialize and Deserialize a Binary Tree
 * 
 * Problem: Design an algorithm to serialize a binary tree into a string,
 * and then deserialize the string back into the original tree.
 * 
 * Serialization: Convert a tree to a string.
 * Deserialization: Convert a string back to a tree.
 * 
 * Approach (BFS / Level Order):
 * 1. Serialize: Use BFS. For each node, add its value to the string.
 *    For null children, add "null". Separate values with commas.
 * 2. Deserialize: Split the string by commas. Use BFS to reconstruct.
 *    Read values one by one, assigning left and right children.
 * 
 * Time Complexity : O(n) for both serialize and deserialize.
 * Space Complexity: O(n) for the queue and the string/array.
 */
import java.util.*;

public class SerializeDeserializeTree {

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
     * Serializes a binary tree into a comma-separated string.
     * Uses BFS (level order) approach.
     * 
     * @param root Root of the binary tree
     * @return Serialized string representation
     */
    static String serialize(Node root) {
        // Edge case: empty tree
        if (root == null)
            return "null";

        // StringBuilder for efficient string concatenation
        StringBuilder sb = new StringBuilder();

        // BFS queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Dequeue the front node
            Node node = queue.poll();

            // If null, append "null"
            if (node == null) {
                sb.append("null,");
                continue; // Don't try to enqueue children of a null node
            }

            // Append the node's value
            sb.append(node.data).append(",");

            // Enqueue both children (even if null)
            // This ensures we capture the structure properly
            queue.add(node.left);
            queue.add(node.right);
        }

        // Remove the trailing comma
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    /**
     * Deserializes a string back into a binary tree.
     * Uses BFS approach, mirroring the serialization.
     * 
     * @param data The serialized string
     * @return Root of the reconstructed binary tree
     */
    static Node deserialize(String data) {
        // Edge case: null tree
        if (data.equals("null"))
            return null;

        // Split the string by commas to get individual values
        String[] values = data.split(",");

        // Create the root node from the first value
        Node root = new Node(Integer.parseInt(values[0]));

        // BFS queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Index to track position in the values array
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            // Get the current parent node
            Node parent = queue.poll();

            // Process left child
            if (!values[i].equals("null")) {
                // Create left child and add to queue
                parent.left = new Node(Integer.parseInt(values[i]));
                queue.add(parent.left);
            }
            i++; // Move to next value

            // Process right child
            if (i < values.length && !values[i].equals("null")) {
                // Create right child and add to queue
                parent.right = new Node(Integer.parseInt(values[i]));
                queue.add(parent.right);
            }
            i++; // Move to next value
        }

        return root;
    }

    /**
     * Utility: Inorder traversal to verify the tree.
     */
    static void printInorder(Node root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        System.out.println("--- Serialize & Deserialize Binary Tree ---");

        // Build a sample tree:
        // 1
        // / \
        // 2 3
        // / \
        // 4 5
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        // Serialize the tree
        String serialized = serialize(root);
        System.out.println("Serialized:   " + serialized);

        // Deserialize back to a tree
        Node deserialized = deserialize(serialized);

        // Verify by printing inorder traversal of both trees
        System.out.print("Original Inorder:     ");
        printInorder(root);

        System.out.print("\nDeserialized Inorder: ");
        printInorder(deserialized);

        // Re-serialize the deserialized tree to check equality
        String reSerialized = serialize(deserialized);
        System.out.println("\nRe-Serialized: " + reSerialized);
        System.out.println("Match? " + serialized.equals(reSerialized));
    }
}
