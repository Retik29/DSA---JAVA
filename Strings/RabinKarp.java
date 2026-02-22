
// Rabin-Karp Algorithm — rolling hash pattern search
// Time: O(n + m) avg, O(nm) worst | Space: O(1)
import java.util.*;

public class RabinKarp {

    static final int BASE = 256, PRIME = 101;

    static List<Integer> search(String text, String pat) {
        List<Integer> positions = new ArrayList<>();
        int n = text.length(), m = pat.length();
        if (m > n)
            return positions;

        // Compute h = BASE^(m-1) % PRIME
        long h = 1;
        for (int i = 0; i < m - 1; i++)
            h = (h * BASE) % PRIME;

        // Initial hashes
        long patHash = 0, txtHash = 0;
        for (int i = 0; i < m; i++) {
            patHash = (BASE * patHash + pat.charAt(i)) % PRIME;
            txtHash = (BASE * txtHash + text.charAt(i)) % PRIME;
        }

        // Slide window
        for (int i = 0; i <= n - m; i++) {
            if (patHash == txtHash && text.substring(i, i + m).equals(pat))
                positions.add(i);
            if (i < n - m) {
                txtHash = (BASE * (txtHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;
                if (txtHash < 0)
                    txtHash += PRIME;
            }
        }
        return positions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter pattern: ");
        String pat = sc.nextLine();

        List<Integer> pos = search(text, pat);
        System.out.println("Pattern found at positions: " + (pos.isEmpty() ? "Not found" : pos));

        sc.close();
    }
}
