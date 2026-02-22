
// Lowest Common Ancestor (LCA) in a Binary Tree
// Time Complexity: O(n) | Space Complexity: O(h) where h is height
import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int d) {
        data = d;
    }
}

public class LowestCommonAncestor {

    static Node findLCA(Node root, int n1, int n2) {
        if (root == null)
            return null;

        // If either n1 or n2 matches with root's data, report the presence
        if (root.data == n1 || root.data == n2)
            return root;

        // Look for keys in left and right subtrees
        Node leftLCA = findLCA(root.left, n1, n2);
        Node rightLCA = findLCA(root.right, n1, n2);

        // If both nodes are found in different subtrees, root is LCA
        if (leftLCA != null && rightLCA != null)
            return root;

        // Otherwise return non-null node
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    // Helper to build tree from level order array
    static Node buildTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null)
            return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < arr.length) {
            Node curr = q.poll();
            if (i < arr.length && arr[i] != null) {
                curr.left = new Node(arr[i]);
                q.add(curr.left);
            }
            i++;
            if (i < arr.length && arr[i] != null) {
                curr.right = new Node(arr[i]);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample tree: [3,5,1,6,2,0,8,null,null,7,4]
        System.out.println("Building a sample tree for Demonstration...");
        Integer[] treeData = { 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4 };
        Node root = buildTree(treeData);

        System.out.print("Enter first node data: ");
        int n1 = sc.nextInt();
        System.out.print("Enter second node data: ");
        int n2 = sc.nextInt();

        Node lca = findLCA(root, n1, n2);
        if (lca != null)
            System.out.println("LCA of " + n1 + " and " + n2 + " is: " + lca.data);
        else
            System.out.println("LCA not found.");

        sc.close();
    }
}
