
// Anagram Check — frequency count approach
// Time: O(n) | Space: O(1) — constant 26 chars
import java.util.*;

public class AnagramCheck {

    // Count-based: increment for s1, decrement for s2
    static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count)
            if (c != 0)
                return false;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String s1 = sc.nextLine().toLowerCase().replaceAll("\\s", "");
        System.out.print("Enter second string: ");
        String s2 = sc.nextLine().toLowerCase().replaceAll("\\s", "");

        System.out.println("\"" + s1 + "\" and \"" + s2 + "\" are anagrams? " + isAnagram(s1, s2));

        sc.close();
    }
}
