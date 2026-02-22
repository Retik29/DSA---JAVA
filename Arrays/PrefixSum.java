
// Prefix Sum — range sum queries in O(1) after O(n) preprocessing
// Time: O(n) build, O(1) per query | Space: O(n)
import java.util.*;

public class PrefixSum {

    static int[] buildPrefixSum(int[] arr) {
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < arr.length; i++)
            prefix[i] = prefix[i - 1] + arr[i];
        return prefix;
    }

    // Sum of range [l, r] — O(1)
    static int rangeSum(int[] prefix, int l, int r) {
        return (l == 0) ? prefix[r] : prefix[r] - prefix[l - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int[] prefix = buildPrefixSum(arr);
        System.out.println("Array:  " + Arrays.toString(arr));
        System.out.println("Prefix: " + Arrays.toString(prefix));

        System.out.print("Enter range (l r): ");
        int l = sc.nextInt(), r = sc.nextInt();
        System.out.println("Sum[" + l + ".." + r + "] = " + rangeSum(prefix, l, r));

        sc.close();
    }
}
