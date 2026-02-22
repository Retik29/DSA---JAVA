
// Binary Tree Properties — height, diameter, LCA, views, balance check
// Time: O(n) each | Space: O(h)
import java.util.*;

public class BinaryTreeProperties {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    // Height of tree
    static int height(Node root) {
        return (root == null) ? 0 : 1 + Math.max(height(root.left), height(root.right));
    }

    // Diameter (longest path between two nodes)
    static int diameter;

    static int diameterOfTree(Node root) {
        diameter = 0;
        heightForDia(root);
        return diameter;
    }

    private static int heightForDia(Node r) {
        if (r == null)
            return 0;
        int l = heightForDia(r.left), ri = heightForDia(r.right);
        diameter = Math.max(diameter, l + ri);
        return 1 + Math.max(l, ri);
    }

    // Check balanced (height difference <= 1 at every node)
    static boolean isBalanced(Node root) {
        return checkBal(root) != -1;
    }

    private static int checkBal(Node r) {
        if (r == null)
            return 0;
        int l = checkBal(r.left), ri = checkBal(r.right);
        if (l == -1 || ri == -1 || Math.abs(l - ri) > 1)
            return -1;
        return 1 + Math.max(l, ri);
    }

    // Count nodes
    static int countNodes(Node r) {
        return (r == null) ? 0 : 1 + countNodes(r.left) + countNodes(r.right);
    }

    // Mirror/Invert tree
    static Node invert(Node r) {
        if (r == null)
            return null;
        Node t = r.left;
        r.left = invert(r.right);
        r.right = invert(t);
        return r;
    }

    // Build tree from level-order input (-1 = null)
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
            System.out.print("Enter left child of " + cur.data + " (-1 for null): ");
            int l = sc.nextInt();
            if (l != -1) {
                cur.left = new Node(l);
                q.add(cur.left);
            }
            System.out.print("Enter right child of " + cur.data + " (-1 for null): ");
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

        System.out.println("\nHeight: " + height(root));
        System.out.println("Diameter: " + diameterOfTree(root));
        System.out.println("Node count: " + countNodes(root));
        System.out.println("Is balanced: " + isBalanced(root));

        sc.close();
    }
}
