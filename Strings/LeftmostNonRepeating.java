
// Leftmost Non-Repeating Character
// Time: O(n) | Space: O(1) — constant 256 chars
import java.util.*;

public class LeftmostNonRepeating {

    static int leftmostNonRepeating(String s) {
        int[] count = new int[256];
        for (char c : s.toCharArray())
            count[c]++; // count frequency
        for (int i = 0; i < s.length(); i++) // find first with count 1
            if (count[s.charAt(i)] == 1)
                return i;
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        int idx = leftmostNonRepeating(s);
        if (idx == -1)
            System.out.println("No non-repeating character found");
        else
            System.out.println("Leftmost non-repeating: '" + s.charAt(idx) + "' at index " + idx);

        sc.close();
    }
}
