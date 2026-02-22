
// Frequency Count using HashMap
// Time: O(n) | Space: O(n)
import java.util.*;

public class FrequencyCount {

    static Map<Integer, Integer> countFrequency(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr)
            freq.merge(num, 1, Integer::sum);
        return freq;
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
        Map<Integer, Integer> freq = countFrequency(arr);
        freq.forEach((k, v) -> System.out.println("  " + k + " → " + v));

        sc.close();
    }
}
