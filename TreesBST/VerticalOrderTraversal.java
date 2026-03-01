
/**
 * Vertical Order Traversal of a Binary Tree
 * 
 * Problem: Given a binary tree, return the vertical order traversal of its
 * nodes' values. Two nodes are in the same column if they have the same
 * horizontal distance from the root. If two nodes are in the same row and
 * column, the node with the smaller value should come first.
 * 
 * Approach:
 * 1. Assign coordinates to each node: (horizontal distance, level/depth).
 * 2. Use BFS to traverse the tree, tracking (node, hd, level) tuples.
 * 3. Use a TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>
 *    to sort by HD first, then by level, then by value.
 * 4. Extract results from the sorted map.
 * 
 * Time Complexity : O(n log n) - Due to sorting in TreeMap and PriorityQueue.
 * Space Complexity: O(n) - For the map and queue.
 */
import java.util.*;

public class VerticalOrderTraversal {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // Tuple class to hold node information during BFS
    static class Tuple {
        Node node; // The tree node
        int hd; // Horizontal distance from root
        int level; // Depth level in the tree

        Tuple(Node node, int hd, int level) {
            this.node = node;
            this.hd = hd;
            this.level = level;
        }
    }

    /**
     * Performs vertical order traversal of the binary tree.
     * 
     * @param root Root of the binary tree
     * @return List of lists, each representing a vertical column
     */
    static List<List<Integer>> verticalTraversal(Node root) {
        // Result list
        List<List<Integer>> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // TreeMap: HD -> (Level -> sorted values at that position)
        // Outer TreeMap sorts by horizontal distance
        // Inner TreeMap sorts by level
        // PriorityQueue sorts values at the same (HD, level) in ascending order
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        // BFS queue
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(root, 0, 0));

        while (!queue.isEmpty()) {
            // Dequeue the front tuple
            Tuple t = queue.poll();
            Node node = t.node;
            int hd = t.hd;
            int level = t.level;

            // Insert into the nested map structure
            // computeIfAbsent creates the inner map or priority queue if absent
            map.computeIfAbsent(hd, k -> new TreeMap<>())
                    .computeIfAbsent(level, k -> new PriorityQueue<>())
                    .add(node.data);

            // Enqueue left child with HD - 1, level + 1
            if (node.left != null) {
                queue.add(new Tuple(node.left, hd - 1, level + 1));
            }

            // Enqueue right child with HD + 1, level + 1
            if (node.right != null) {
                queue.add(new Tuple(node.right, hd + 1, level + 1));
            }
        }

        // Extract results from the sorted map
        for (TreeMap<Integer, PriorityQueue<Integer>> levelMap : map.values()) {
            // Create a list for this vertical column
            List<Integer> column = new ArrayList<>();

            // Iterate through each level within this column
            for (PriorityQueue<Integer> pq : levelMap.values()) {
                // Drain the priority queue (elements come out sorted)
                while (!pq.isEmpty()) {
                    column.add(pq.poll());
                }
            }

            // Add this column to the result
            result.add(column);
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
        // Demo tree: 3
        // / \
        // 9 20
        // / \
        // 15 7
        System.out.println("--- Vertical Order Traversal ---");
        Integer[] treeData = { 3, 9, 20, null, null, 15, 7 };
        Node root = buildTree(treeData);

        List<List<Integer>> result = verticalTraversal(root);
        System.out.println("Tree: [3, 9, 20, null, null, 15, 7]");
        System.out.println("Vertical Order: " + result);

        // Test 2
        Integer[] treeData2 = { 1, 2, 3, 4, 5, 6, 7 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Vertical Order: " + verticalTraversal(root2));
    }
}
