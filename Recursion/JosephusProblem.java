
// Josephus Problem — every kth person eliminated from circle
// Time: O(n) | Space: O(n) recursive, O(1) iterative
import java.util.*;

public class JosephusProblem {

    // Recursive (0-indexed): survivor position
    static int josephus(int n, int k) {
        return (n == 1) ? 0 : (josephus(n - 1, k) + k) % n;
    }

    // Iterative (0-indexed)
    static int josephusIterative(int n, int k) {
        int pos = 0;
        for (int i = 2; i <= n; i++)
            pos = (pos + k) % i;
        return pos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n (people) and k (step): ");
        int n = sc.nextInt(), k = sc.nextInt();

        int pos = josephusIterative(n, k);
        System.out.println("Survivor position (0-indexed): " + pos);
        System.out.println("Survivor position (1-indexed): " + (pos + 1));

        sc.close();
    }
}
