
/**
 * Left View and Right View of a Binary Tree
 * 
 * Left View:  The set of nodes visible when the tree is viewed from the left side.
 *             It is the FIRST node at each level.
 * 
 * Right View: The set of nodes visible when the tree is viewed from the right side.
 *             It is the LAST node at each level.
 * 
 * Approach (BFS):
 * 1. Perform level-order traversal.
 * 2. For Left View:  pick the FIRST node in each level.
 * 3. For Right View: pick the LAST node in each level.
 * 
 * Time Complexity : O(n) - Every node is visited exactly once.
 * Space Complexity: O(n) - Queue can hold at most n/2 nodes.
 */
import java.util.*;

public class LeftRightViewTree {

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
     * Returns the left view of the binary tree.
     * The left view contains the first node of each level.
     */
    static List<Integer> leftView(Node root) {
        // Result list for left view
        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // BFS queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Number of nodes at this level
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                // Remove the front node from the queue
                Node node = queue.poll();

                // The first node in this level (i == 0) is the left view node
                if (i == 0) {
                    result.add(node.data);
                }

                // Enqueue children for the next level
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return result;
    }

    /**
     * Returns the right view of the binary tree.
     * The right view contains the last node of each level.
     */
    static List<Integer> rightView(Node root) {
        // Result list for right view
        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // BFS queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            // Number of nodes at this level
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                // Remove the front node from the queue
                Node node = queue.poll();

                // The last node in this level (i == levelSize - 1) is the right view node
                if (i == levelSize - 1) {
                    result.add(node.data);
                }

                // Enqueue children for the next level
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
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
        // / \ \
        // 4 5 6
        // Left View: [1, 2, 4]
        // Right View: [1, 3, 6]
        System.out.println("--- Left View & Right View ---");
        Integer[] treeData = { 1, 2, 3, 4, 5, null, 6 };
        Node root = buildTree(treeData);

        System.out.println("Tree: [1, 2, 3, 4, 5, null, 6]");
        System.out.println("Left View:  " + leftView(root));
        System.out.println("Right View: " + rightView(root));

        // Test 2: Single node
        Integer[] treeData2 = { 1 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1]");
        System.out.println("Left View:  " + leftView(root2));
        System.out.println("Right View: " + rightView(root2));
    }
}
