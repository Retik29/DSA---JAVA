
// Generate All Subsets of a String using Recursion
// Time: O(2^n) | Space: O(n)
import java.util.*;

public class StringSubsets {

    static void generateSubsets(String s, int idx, String cur, List<String> result) {
        if (idx == s.length()) {
            result.add(cur.isEmpty() ? "\"\"" : cur);
            return;
        }
        generateSubsets(s, idx + 1, cur, result); // exclude
        generateSubsets(s, idx + 1, cur + s.charAt(idx), result); // include
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        List<String> result = new ArrayList<>();
        generateSubsets(s, 0, "", result);

        System.out.println("Total subsets: " + result.size());
        result.forEach(System.out::println);

        sc.close();
    }
}
