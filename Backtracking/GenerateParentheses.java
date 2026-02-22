
// Generate Valid Parentheses Combinations
// Time: O(4^n / √n) — Catalan number | Space: O(n)
import java.util.*;

public class GenerateParentheses {

    // Backtrack: add '(' if open < n, add ')' if close < open
    static void generate(List<String> res, String cur, int open, int close, int n) {
        if (cur.length() == 2 * n) {
            res.add(cur);
            return;
        }
        if (open < n)
            generate(res, cur + "(", open + 1, close, n);
        if (close < open)
            generate(res, cur + ")", open, close + 1, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n (pairs of parentheses): ");
        int n = sc.nextInt();

        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);

        System.out.println("Total combinations: " + result.size());
        result.forEach(System.out::println);

        sc.close();
    }
}
