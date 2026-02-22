
// N-Queens — place N queens on NxN board with no conflicts
// Time: O(N!) | Space: O(N²)
import java.util.*;

public class NQueens {

    static List<List<String>> solutions = new ArrayList<>();

    // Check if placing queen at (row, col) is safe
    static boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q')
                return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q')
                return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 'Q')
                return false;
        return true;
    }

    static void solve(char[][] board, int row, int n) {
        if (row == n) {
            List<String> sol = new ArrayList<>();
            for (char[] r : board)
                sol.add(new String(r));
            solutions.add(sol);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                solve(board, row + 1, n);
                board[row][col] = '.'; // backtrack
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        char[][] board = new char[n][n];
        for (char[] row : board)
            Arrays.fill(row, '.');
        solve(board, 0, n);

        System.out.println("Total solutions: " + solutions.size());
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nSolution " + (i + 1) + ":");
            solutions.get(i).forEach(System.out::println);
        }

        sc.close();
    }
}
