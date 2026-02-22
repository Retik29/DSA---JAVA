
// Palindrome Number Check
// Time: O(d) where d = digits | Space: O(1)
import java.util.*;

public class Palindrome {

    static boolean isPalindrome(int num) {
        int temp = num, rev = 0;
        while (temp != 0) {
            rev = rev * 10 + temp % 10;
            temp /= 10;
        }
        return num == rev;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        System.out.println(num + (isPalindrome(num) ? " is a palindrome" : " is NOT a palindrome"));

        sc.close();
    }
}
