
/**
 * Binary Tree Traversals
 * 
 * Problem: Visit all the nodes of a binary tree in a specific order.
 * 
 * 1. Depth First Search (DFS):
 *    - Inorder (Left, Root, Right): Useful for BSTs as it visits nodes in sorted order.
 *    - Preorder (Root, Left, Right): Useful for creating a copy of the tree.
 *    - Postorder (Left, Right, Root): Useful for deleting nodes or evaluating expressions.
 * 
 * 2. Breadth First Search (BFS):
 *    - Level Order: Visits nodes level by level from top to bottom.
 * 
 * Time Complexity : O(n) - Every node is visited exactly once.
 * Space Complexity: 
 * - Recursive DFS: O(h) where h is height (for recursion stack).
 * - Iterative DFS: O(h) (for explicit stack).
 * - BFS: O(n) in the worst case (for queue storage).
 */
import java.util.*;

public class BinaryTreeTraversals {

    /**
     * Basic structure of a Tree Node.
     */
    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // --- RECURSIVE DFS TRAVERSALS ---

    /**
     * Inorder: Left -> Root -> Right
     */
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    /**
     * Preorder: Root -> Left -> Right
     */
    static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    /**
     * Postorder: Left -> Right -> Root
     */
    static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // --- ITERATIVE DFS ---

    /**
     * Iterative Inorder using an explicit Stack.
     */
    static List<Integer> inorderIterative(Node root) {
        List<Integer> result = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            // Keep going left as far as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Visit current node
            current = stack.pop();
            result.add(current.data);

            // Go right
            current = current.right;
        }
        return result;
    }

    // --- BREADTH FIRST SEARCH (BFS) ---

    /**
     * Level Order Traversal using a Queue.
     */
    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                currentLevel.add(node.data);

                // Add children to queue for next level
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    /**
     * Builds a tree based on Level Order Input.
     * Use -1 for Null nodes.
     */
    static Node buildTree(Scanner sc) {
        System.out.println("\n--- Build Binary Tree (Level Order) ---");
        System.out.print("Enter root value (-1 for none): ");
        int val = sc.nextInt();
        if (val == -1)
            return null;

        Node root = new Node(val);
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.poll();

            System.out.print("Left child of " + cur.data + " (-1 for null): ");
            int le = sc.nextInt();
            if (le != -1) {
                cur.left = new Node(le);
                q.add(cur.left);
            }

            System.out.print("Right child of " + cur.data + " (-1 for null): ");
            int ri = sc.nextInt();
            if (ri != -1) {
                cur.right = new Node(ri);
                q.add(cur.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Build the tree
        Node root = buildTree(sc);

        // Display traversals
        System.out.println("\n--- Traversal Results ---");

        System.out.print("Inorder (Recursive):   ");
        inorder(root);
        System.out.println();

        System.out.print("Preorder (Recursive):  ");
        preorder(root);
        System.out.println();

        System.out.print("Postorder (Recursive): ");
        postorder(root);
        System.out.println();

        System.out.println("Level Order (Lists):   " + levelOrder(root));

        sc.close();
    }
}
