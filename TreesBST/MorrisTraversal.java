
/**
 * Morris Inorder Traversal (Threaded Binary Tree Traversal)
 * 
 * Problem: Perform inorder traversal of a binary tree WITHOUT using
 * recursion or an explicit stack. Achieve O(1) extra space.
 * 
 * Key Idea (Threading):
 * - Temporarily create links (threads) from inorder predecessor to root.
 * - After processing, remove the threads to restore the original tree.
 * 
 * Algorithm:
 * 1. Start with current = root.
 * 2. While current is not null:
 *    a. If current has no left child:
 *       - Visit current (print / add to result).
 *       - Move to current.right.
 *    b. If current has a left child:
 *       - Find the inorder predecessor (rightmost node in left subtree).
 *       - Case A: predecessor.right == null (first visit to this subtree)
 *           - Create a thread: predecessor.right = current.
 *           - Move to current.left.
 *       - Case B: predecessor.right == current (returning via thread)
 *           - Remove the thread: predecessor.right = null.
 *           - Visit current (print / add to result).
 *           - Move to current.right.
 * 
 * Time Complexity : O(n) - Each edge is traversed at most 3 times.
 * Space Complexity: O(1) - No recursion stack or explicit stack needed.
 */
import java.util.*;

public class MorrisTraversal {

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
     * Performs Morris Inorder Traversal (O(1) space, no recursion).
     * 
     * @param root Root of the binary tree
     * @return List of node values in inorder sequence
     */
    static List<Integer> morrisInorder(Node root) {
        // Result list to store inorder sequence
        List<Integer> result = new ArrayList<>();

        // Current node pointer
        Node current = root;

        while (current != null) {
            // Case 1: No left child exists
            if (current.left == null) {
                // Visit this node (it's the next in inorder)
                result.add(current.data);

                // Move to the right child (or follow the thread)
                current = current.right;
            } else {
                // Case 2: Left child exists — find the inorder predecessor
                Node predecessor = current.left;

                // Go to the rightmost node in the left subtree
                // But stop if we encounter 'current' (thread already exists)
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Sub-case A: First time — create thread
                    // Link the predecessor to the current node
                    predecessor.right = current;

                    // Move to the left subtree (explore it before visiting current)
                    current = current.left;
                } else {
                    // Sub-case B: Thread exists — we're returning
                    // Remove the thread (restore original tree)
                    predecessor.right = null;

                    // Visit the current node now
                    result.add(current.data);

                    // Move to the right subtree
                    current = current.right;
                }
            }
        }

        return result;
    }

    /**
     * Performs Morris Preorder Traversal (O(1) space, no recursion).
     * 
     * @param root Root of the binary tree
     * @return List of node values in preorder sequence
     */
    static List<Integer> morrisPreorder(Node root) {
        // Result list to store preorder sequence
        List<Integer> result = new ArrayList<>();

        // Current node pointer
        Node current = root;

        while (current != null) {
            // Case 1: No left child
            if (current.left == null) {
                // Visit the node (root before subtrees in preorder)
                result.add(current.data);

                // Move right
                current = current.right;
            } else {
                // Case 2: Left child exists — find predecessor
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // First visit — visit current NOW (preorder: root first)
                    result.add(current.data);

                    // Create thread
                    predecessor.right = current;

                    // Move left
                    current = current.left;
                } else {
                    // Returning via thread — just remove thread
                    predecessor.right = null;

                    // Move right (don't visit again, already visited)
                    current = current.right;
                }
            }
        }

        return result;
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
        // Demo tree: 1
        // / \
        // 2 3
        // / \
        // 4 5
        System.out.println("--- Morris Traversal (O(1) Space) ---");
        Integer[] treeData = { 1, 2, 3, 4, 5 };
        Node root = buildTree(treeData);

        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Morris Inorder:  " + morrisInorder(root));
        System.out.println("Morris Preorder: " + morrisPreorder(root));
    }
}
