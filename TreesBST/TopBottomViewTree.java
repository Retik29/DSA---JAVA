
/**
 * Top View and Bottom View of a Binary Tree
 * 
 * Top View: The set of nodes visible when the tree is viewed from the top.
 *           For each horizontal distance, the FIRST node encountered in
 *           level-order traversal is the top view node.
 * 
 * Bottom View: The set of nodes visible when the tree is viewed from the bottom.
 *              For each horizontal distance, the LAST node encountered in
 *              level-order traversal is the bottom view node.
 * 
 * Approach (Vertical Line / Horizontal Distance):
 * 1. Assign horizontal distance (HD) 0 to the root.
 * 2. Left child gets HD - 1, right child gets HD + 1.
 * 3. Use BFS (level-order) traversal:
 *    - Top View: For each HD, store the FIRST node only.
 *    - Bottom View: For each HD, keep OVERWRITING (last one wins).
 * 4. Use a TreeMap (sorted by HD) to get left-to-right order.
 * 
 * Time Complexity : O(n log n) - Due to TreeMap insertions.
 * Space Complexity: O(n) - For the queue and the map.
 */
import java.util.*;

public class TopBottomViewTree {

    // Standard binary tree node
    static class Node {
        int data; // Value stored in this node
        Node left; // Pointer to the left child
        Node right; // Pointer to the right child

        Node(int d) {
            data = d; // Initialize node with value d
        }
    }

    // Pair class to hold a node and its horizontal distance
    static class Pair {
        Node node; // The tree node
        int hd; // Horizontal distance from the root

        Pair(Node node, int hd) {
            this.node = node; // Store the node
            this.hd = hd; // Store the horizontal distance
        }
    }

    /**
     * Returns the top view of the binary tree (left to right).
     */
    static List<Integer> topView(Node root) {
        // Result list for top view
        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // TreeMap: key = horizontal distance, value = node data
        // TreeMap keeps keys sorted, so we get left-to-right order
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // BFS queue holding (node, horizontal distance) pairs
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            // Dequeue the front pair
            Pair pair = queue.poll();
            Node node = pair.node;
            int hd = pair.hd;

            // For Top View: only add if this HD hasn't been seen before
            // The first node at each HD (in level-order) is the topmost
            if (!map.containsKey(hd)) {
                map.put(hd, node.data);
            }

            // Enqueue left child with HD - 1
            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }

            // Enqueue right child with HD + 1
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Collect values from the sorted map
        result.addAll(map.values());
        return result;
    }

    /**
     * Returns the bottom view of the binary tree (left to right).
     */
    static List<Integer> bottomView(Node root) {
        // Result list for bottom view
        List<Integer> result = new ArrayList<>();

        // Edge case: empty tree
        if (root == null)
            return result;

        // TreeMap to store the last node at each horizontal distance
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // BFS queue holding (node, horizontal distance) pairs
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            // Dequeue the front pair
            Pair pair = queue.poll();
            Node node = pair.node;
            int hd = pair.hd;

            // For Bottom View: always overwrite (last node at each HD wins)
            map.put(hd, node.data);

            // Enqueue left child with HD - 1
            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }

            // Enqueue right child with HD + 1
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Collect values from the sorted map
        result.addAll(map.values());
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
        // \ /
        // 4 5
        // Top View: [2, 1, 3]
        // Bottom View: [2, 4, 5, 3]
        System.out.println("--- Top View & Bottom View ---");
        Integer[] treeData = { 1, 2, 3, null, 4, 5, null };
        Node root = buildTree(treeData);

        System.out.println("Tree: [1, 2, 3, null, 4, 5, null]");
        System.out.println("Top View:    " + topView(root));
        System.out.println("Bottom View: " + bottomView(root));

        // Test 2: Balanced tree
        Integer[] treeData2 = { 1, 2, 3, 4, 5, 6, 7 };
        Node root2 = buildTree(treeData2);
        System.out.println("\nTree: [1, 2, 3, 4, 5, 6, 7]");
        System.out.println("Top View:    " + topView(root2));
        System.out.println("Bottom View: " + bottomView(root2));
    }
}
