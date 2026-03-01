
/**
 * Lowest Common Ancestor (LCA) in a Binary Tree
 * 
 * Problem: For two nodes 'n1' and 'n2' in a binary tree, find the lowest 
 * node that has both nodes as descendants (where a node can be a descendant of itself).
 * 
 * Logic (Recursive Approach):
 * 1. Base Case: If the current node is null, or if it matches either 'n1' or 'n2', 
 *    return the current node.
 * 2. Recursive Step: Search for 'n1' and 'n2' in the left and right subtrees.
 * 3. Consolidation:
 *    - If left subtree returns a non-null node and right subtree also returns 
 *      a non-null node, it means 'n1' and 'n2' are on different sides of the 
 *      current node. Thus, the current node is their Lowest Common Ancestor.
 *    - If only one subtree returns a non-null node, then both 'n1' and 'n2' 
 *      (or at least the one found) are located in that subtree. 
 *      Return the found node.
 * 
 * Note: This works for any binary tree, not just Binary Search Trees.
 * 
 * Time Complexity : O(n) - Visits every node at most once.
 * Space Complexity: O(h) - Recursive call stack, where h is height.
 */
import java.util.*;

/**
 * Standard Node structure for a Binary Tree.
 */
class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
    }
}

public class LowestCommonAncestor {

    /**
     * Recursively finds the LCA of nodes n1 and n2.
     * 
     * @param root The root of the binary tree
     * @param n1   Data of first node
     * @param n2   Data of second node
     * @return LCA node if found, otherwise null
     */
    static Node findLCA(Node root, int n1, int n2) {
        // Base case: null or found one of the keys
        if (root == null || root.data == n1 || root.data == n2) {
            return root;
        }

        // Search in subtrees
        Node leftResult = findLCA(root.left, n1, n2);
        Node rightResult = findLCA(root.right, n1, n2);

        // If both subtrees returned non-null, n1 and n2 are on different sides
        if (leftResult != null && rightResult != null) {
            return root;
        }

        // If one is null, return the other (could be null or the actual node)
        return (leftResult != null) ? leftResult : rightResult;
    }

    /**
     * Utility to build a tree from a Level Order array.
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

            // Handle left child
            if (i < arr.length && arr[i] != null) {
                current.left = new Node(arr[i]);
                queue.add(current.left);
            }
            i++;

            // Handle right child
            if (i < arr.length && arr[i] != null) {
                current.right = new Node(arr[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample tree structure
        System.out.println("Processing Demo Tree...");
        Integer[] treeData = { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
        Node root = buildTree(treeData);

        System.out.println("Tree built successfully.");
        System.out.print("Enter first node value: ");
        int n1 = sc.nextInt();
        System.out.print("Enter second node value: ");
        int n2 = sc.nextInt();

        Node lca = findLCA(root, n1, n2);

        // Output results
        System.out.println("\n--- LCA Result ---");
        if (lca != null) {
            System.out.println("Lowest Common Ancestor: " + lca.data);
        } else {
            System.out.println("LCA not found (nodes might not exist in the tree).");
        }

        sc.close();
    }
}
