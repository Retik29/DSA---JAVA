
import java.util.*;

/**
 * Nodes at Distance K in a Binary Tree
 * 
 * Problem: Given a binary tree, a target node, and an integer K,
 * return all nodes that are exactly K distance from the target node.
 * 
 * Approach:
 * 1. Build a parent pointer map using BFS (so we can traverse UP).
 * 2. From the target node, do BFS expanding in 3 directions:
 * left child, right child, and parent.
 * 3. Stop when we've expanded K levels. The nodes at that level are the answer.
 * 4. Use a visited set to avoid revisiting nodes.
 * 
 * Time: O(n) | Space: O(n)
 */
public class NodesAtDistanceK {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Find all nodes at distance K from the target node
    static List<Integer> distanceK(Node root, int target, int k) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        // Step 1: Build parent pointer map using BFS
        Map<Node, Node> parentMap = new HashMap<>();
        Node targetNode = buildParentMap(root, target, parentMap);

        if (targetNode == null)
            return result; // Target not found

        // Step 2: BFS from target node, expanding in 3 directions
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(targetNode);
        visited.add(targetNode);

        int currentDistance = 0;

        while (!queue.isEmpty()) {
            // If we've reached distance K, collect all nodes at this level
            if (currentDistance == k) {
                for (Node node : queue) {
                    result.add(node.data);
                }
                return result;
            }

            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();

                // Expand to left child
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.add(node.left);
                }

                // Expand to right child
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.add(node.right);
                }

                // Expand to parent
                Node parent = parentMap.get(node);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.add(parent);
                }
            }
            currentDistance++;
        }

        return result;
    }

    // Build parent map and find the target node
    static Node buildParentMap(Node root, int target, Map<Node, Node> parentMap) {
        Node targetNode = null;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.data == target)
                targetNode = node;

            if (node.left != null) {
                parentMap.put(node.left, node);
                queue.add(node.left);
            }
            if (node.right != null) {
                parentMap.put(node.right, node);
                queue.add(node.right);
            }
        }

        return targetNode;
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
        System.out.println("--- Nodes at Distance K ---");
        // 3
        // / \
        // 5 1
        // / \ / \
        // 6 2 0 8
        // / \
        // 7 4
        Integer[] arr = { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
        Node root = buildTree(arr);

        System.out.println("Target = 5, K = 2");
        System.out.println("Nodes at distance K: " + distanceK(root, 5, 2));
    }
}
