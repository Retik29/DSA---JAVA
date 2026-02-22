
// KMP Algorithm — pattern search using LPS (Longest Prefix Suffix) array
// Time: O(n + m) | Space: O(m)
import java.util.*;

public class KMPAlgorithm {

    // Build LPS array
    static int[] computeLPS(String pat) {
        int m = pat.length();
        int[] lps = new int[m];
        int len = 0, i = 1;
        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

    // Search pattern in text, return all positions
    static List<Integer> search(String text, String pat) {
        List<Integer> positions = new ArrayList<>();
        int[] lps = computeLPS(pat);
        int i = 0, j = 0; // text index, pattern index
        while (i < text.length()) {
            if (text.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            if (j == pat.length()) {
                positions.add(i - j);
                j = lps[j - 1];
            } else if (i < text.length() && text.charAt(i) != pat.charAt(j)) {
                if (j > 0)
                    j = lps[j - 1];
                else
                    i++;
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

        System.out.println("LPS array: " + Arrays.toString(computeLPS(pat)));
        List<Integer> pos = search(text, pat);
        System.out.println("Pattern found at positions: " + (pos.isEmpty() ? "Not found" : pos));

        sc.close();
    }
}
