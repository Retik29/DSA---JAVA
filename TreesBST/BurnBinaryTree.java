
import java.util.*;

/**
 * Time to Burn a Binary Tree (Minimum Time to Burn Entire Tree)
 * 
 * Problem: Given a binary tree and a node from where fire starts,
 * find the minimum time to burn the entire tree. Fire spreads to
 * adjacent nodes (left, right, parent) in 1 second.
 * 
 * Approach:
 * 1. Build a parent pointer map (to traverse upward).
 * 2. BFS from the fire source in all 3 directions.
 * 3. The number of BFS levels = time to burn the entire tree.
 * 
 * Time: O(n) | Space: O(n)
 */
public class BurnBinaryTree {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Find minimum time to burn the entire tree from the source node
    static int timeToBurn(Node root, int source) {
        if (root == null)
            return 0;

        // Build parent pointer map and find source node
        Map<Node, Node> parentMap = new HashMap<>();
        Node sourceNode = null;
        Queue<Node> buildQ = new LinkedList<>();
        buildQ.add(root);

        while (!buildQ.isEmpty()) {
            Node node = buildQ.poll();
            if (node.data == source)
                sourceNode = node;
            if (node.left != null) {
                parentMap.put(node.left, node);
                buildQ.add(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                buildQ.add(node.right);
            }
        }

        if (sourceNode == null)
            return -1; // Source not found

        // BFS from source, counting levels
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(sourceNode);
        visited.add(sourceNode);
        int time = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burned = false; // Track if new nodes burned this second

            for (int i = 0; i < size; i++) {
                Node node = queue.poll();

                // Spread to left child
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.add(node.left);
                    burned = true;
                }
                // Spread to right child
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.add(node.right);
                    burned = true;
                }
                // Spread to parent
                Node parent = parentMap.get(node);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.add(parent);
                    burned = true;
                }
            }

            // Only increment time if fire spread to new nodes
            if (burned)
                time++;
        }

        return time;
    }

    static Node buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null)
            return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node cur = q.poll();
            if (i < arr.length && arr[i] != null) {
                cur.left = new Node(arr[i]);
                q.add(cur.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                cur.right = new Node(arr[i]);
                q.add(cur.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        System.out.println("--- Burn Binary Tree ---");
        // 1
        // / \
        // 2 3
        // / \
        // 4 5
        Integer[] arr = { 1, 2, 3, 4, 5 };
        Node root = buildTree(arr);

        System.out.println("Tree: [1, 2, 3, 4, 5]");
        System.out.println("Fire starts at node 2");
        System.out.println("Time to burn entire tree: " + timeToBurn(root, 2));
    }
}
