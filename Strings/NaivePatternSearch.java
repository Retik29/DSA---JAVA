
// Naive Pattern Search — brute force
// Time: O((n-m+1) × m) worst | Space: O(1)
import java.util.*;

public class NaivePatternSearch {

    static List<Integer> naiveSearch(String text, String pat) {
        List<Integer> positions = new ArrayList<>();
        int n = text.length(), m = pat.length();
        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            while (j < m && text.charAt(i + j) == pat.charAt(j))
                j++;
            if (j == m)
                positions.add(i);
        }
        return positions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String pat = sc.nextLine();

        List<Integer> pos = naiveSearch(text, pat);
        System.out.println("Pattern found at positions: " + (pos.isEmpty() ? "Not found" : pos));
        System.out.println("Total occurrences: " + pos.size());

        sc.close();
    }
}
