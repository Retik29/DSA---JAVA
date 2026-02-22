
// String Permutations using Backtracking
// Time: O(n! × n) | Space: O(n)
import java.util.*;

public class StringPermutations {

    static void permute(char[] arr, int idx, List<String> result) {
        if (idx == arr.length) {
            result.add(new String(arr));
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = idx; i < arr.length; i++) {
            if (used.add(arr[i])) { // skip duplicates
                char t = arr[idx];
                arr[idx] = arr[i];
                arr[i] = t; // swap
                permute(arr, idx + 1, result);
                t = arr[idx];
                arr[idx] = arr[i];
                arr[i] = t; // swap back
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        List<String> result = new ArrayList<>();
        permute(s.toCharArray(), 0, result);

        System.out.println("Total permutations: " + result.size());
        result.forEach(System.out::println);

        sc.close();
    }
}
