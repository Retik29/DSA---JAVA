
// Tower of Hanoi — move n disks from source to destination via auxiliary
// Time: O(2^n) | Space: O(n)
import java.util.*;

public class TowerOfHanoi {

    static int moveCount = 0;

    static void solve(int n, char src, char aux, char dst) {
        if (n == 0)
            return;
        solve(n - 1, src, dst, aux); // move n-1 disks to auxiliary
        System.out.println("Move disk " + n + ": " + src + " → " + dst);
        moveCount++;
        solve(n - 1, aux, src, dst); // move n-1 disks to destination
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of disks: ");
        int n = sc.nextInt();

        solve(n, 'A', 'B', 'C');
        System.out.println("Total moves: " + moveCount + " (2^" + n + " - 1 = " + ((1 << n) - 1) + ")");

        sc.close();
    }
}
