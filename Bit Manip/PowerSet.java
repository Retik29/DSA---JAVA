
// Generate Power Set (all subsets) using Bit Manipulation
// Time: O(n × 2^n) | Space: O(n × 2^n)
import java.util.*;

public class PowerSet {

    // Each number 0 to 2^n-1 represents a subset; bit i → include arr[i]
    static List<List<Integer>> generatePowerSet(int[] arr) {
        int n = arr.length, total = 1 << n; // 2^n subsets
        List<List<Integer>> powerSet = new ArrayList<>();
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if ((mask & (1 << i)) != 0)
                    subset.add(arr[i]);
            powerSet.add(subset);
        }
        return powerSet;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        List<List<Integer>> ps = generatePowerSet(arr);
        System.out.println("Total subsets: " + ps.size());
        ps.forEach(System.out::println);

        sc.close();
    }
}
