
// Palindrome String Check using Recursion
// Time: O(n) | Space: O(n) recursion stack
import java.util.*;

public class PalindromeString {

    // Recursive: compare chars from both ends
    static boolean isPalindrome(String s, int l, int r) {
        if (l >= r)
            return true;
        return (s.charAt(l) == s.charAt(r)) && isPalindrome(s, l + 1, r - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine().toLowerCase().replaceAll("[^a-z0-9]", "");

        boolean result = isPalindrome(s, 0, s.length() - 1);
        System.out.println("\"" + s + "\" is palindrome? " + result);

        sc.close();
    }
}
