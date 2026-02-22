
// Distinct Elements using HashSet
// Time: O(n) | Space: O(n)
import java.util.*;

public class DistinctElements {

    // Count distinct elements
    static int countDistinct(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr)
            set.add(num);
        return set.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Distinct count: " + countDistinct(arr));

        // Print distinct elements
        Set<Integer> set = new LinkedHashSet<>();
        for (int x : arr)
            set.add(x);
        System.out.println("Distinct elements: " + set);

        sc.close();
    }
}
