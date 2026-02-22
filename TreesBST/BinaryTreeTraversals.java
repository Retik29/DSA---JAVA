
// Binary Tree Traversals — Inorder, Preorder, Postorder, Level Order
// Time: O(n) | Space: O(h) recursive, O(n) iterative
import java.util.*;

public class BinaryTreeTraversals {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Recursive traversals
    static void inorder(Node r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.data + " ");
            inorder(r.right);
        }
    }

    static void preorder(Node r) {
        if (r != null) {
            System.out.print(r.data + " ");
            preorder(r.left);
            preorder(r.right);
        }
    }

    static void postorder(Node r) {
        if (r != null) {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data + " ");
        }
    }

    // Iterative inorder using stack
    static List<Integer> inorderIterative(Node root) {
        List<Integer> res = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.data);
            cur = cur.right;
        }
        return res;
    }

    // Level order (BFS)
    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < sz; i++) {
                Node n = q.poll();
                level.add(n.data);
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            result.add(level);
        }
        return result;
    }

    // Build tree interactively (level order, -1 = null)
    static Node buildTree(Scanner sc) {
        System.out.print("Enter root value (-1 for null): ");
        int val = sc.nextInt();
        if (val == -1)
            return null;
        Node root = new Node(val);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.print("Left child of " + cur.data + " (-1=null): ");
            int l = sc.nextInt();
            if (l != -1) {
                cur.left = new Node(l);
                q.add(cur.left);
            }
            System.out.print("Right child of " + cur.data + " (-1=null): ");
            int r = sc.nextInt();
            if (r != -1) {
                cur.right = new Node(r);
                q.add(cur.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node root = buildTree(sc);

        System.out.print("\nInorder:    ");
        inorder(root);
        System.out.println();
        System.out.print("Preorder:   ");
        preorder(root);
        System.out.println();
        System.out.print("Postorder:  ");
        postorder(root);
        System.out.println();
        System.out.println("Level order: " + levelOrder(root));

        sc.close();
    }
}
