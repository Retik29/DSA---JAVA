
import java.util.*;

/**
 * Convert Sorted Array to Balanced BST
 * 
 * Pick the middle element as root, recurse on left and right halves.
 * 
 * Time: O(n) | Space: O(log n)
 */
public class SortedArrayToBST {

    static class Node {
        int data;
        Node left, right;

        Node(int d) {
            data = d;
        }
    }

    static Node sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    static Node buildBST(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = start + (end - start) / 2;
        Node root = new Node(nums[mid]);
        root.left = buildBST(nums, start, mid - 1);
        root.right = buildBST(nums, mid + 1, end);
        return root;
    }

    static void printInorder(Node root) {
        if (root == null)
            return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    static int height(Node root) {
        if (root == null)
            return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public static void main(String[] args) {
        System.out.println("--- Sorted Array to Balanced BST ---");
        int[] nums = { -10, -3, 0, 5, 9 };
        Node root = sortedArrayToBST(nums);
        System.out.println("Input: " + Arrays.toString(nums));
        System.out.print("Inorder: ");
        printInorder(root);
        System.out.println("\nHeight: " + height(root));
    }
}
