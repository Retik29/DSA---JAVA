
// Reverse Words in a String
// Time: O(n) | Space: O(n)
import java.util.*;

public class ReverseWords {

    // Using split & reverse iterate
    static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--)
            sb.append(words[i]).append(i > 0 ? " " : "");
        return sb.toString();
    }

    // In-place: reverse entire string, then reverse each word
    static String reverseWordsInPlace(String s) {
        char[] arr = s.toCharArray();
        reverse(arr, 0, arr.length - 1);
        int start = 0;
        for (int end = 0; end <= arr.length; end++)
            if (end == arr.length || arr[end] == ' ') {
                reverse(arr, start, end - 1);
                start = end + 1;
            }
        return new String(arr);
    }

    private static void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String s = sc.nextLine();

        System.out.println("Reversed words:  \"" + reverseWords(s) + "\"");
        System.out.println("In-place method: \"" + reverseWordsInPlace(s.trim()) + "\"");

        sc.close();
    }
}
