
// Longest Common Prefix in an array of strings
// Time Complexity: O(n * L) where L is length of prefix | Space Complexity: O(1)
import java.util.*;

public class LongestCommonPrefix {

    static String getLongestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty())
                    return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of strings: ");
        int n = sc.nextInt();
        String[] strs = new String[n];
        System.out.println("Enter " + n + " strings:");
        for (int i = 0; i < n; i++)
            strs[i] = sc.next();

        String lcp = getLongestCommonPrefix(strs);
        System.out.println("Longest Common Prefix: " + (lcp.isEmpty() ? "(None)" : lcp));

        sc.close();
    }
}
