
/**
 * Intersection and Union of Two Arrays using HashSet
 * Time: O(n + m) | Space: O(n + m)
 */
import java.util.*;

public class IntersectionUnion {

    // Union: all distinct elements from both arrays
    static Set<Integer> union(int[] a, int[] b) {
        Set<Integer> set = new HashSet<>();
        for (int x : a)
            set.add(x);
        for (int x : b)
            set.add(x);
        return set;
    }

    // Intersection: elements present in both arrays (distinct)
    static Set<Integer> intersection(int[] a, int[] b) {
        Set<Integer> setA = new HashSet<>(), result = new HashSet<>();
        for (int x : a)
            setA.add(x);
        for (int x : b)
            if (setA.contains(x))
                result.add(x);
        return result;
    }

    // Read an array: first reads size, then elements
    private static int[] readArray(Scanner sc, String label) {
        System.out.print("Enter size of " + label + ": ");
        int n = sc.nextInt();
        System.out.print("Enter " + n + " elements: ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr1 = readArray(sc, "array 1");
        int[] arr2 = readArray(sc, "array 2");

        System.out.println("\nArray 1: " + Arrays.toString(arr1));
        System.out.println("Array 2: " + Arrays.toString(arr2));

        Set<Integer> u = union(arr1, arr2);
        Set<Integer> inter = intersection(arr1, arr2);

        System.out.println("Union:        " + u + " (count: " + u.size() + ")");
        System.out.println("Intersection: " + inter + " (count: " + inter.size() + ")");

        sc.close();
    }
}
