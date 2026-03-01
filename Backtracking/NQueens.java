
/**
 * N-Queens Problem (Backtracking)
 * 
 * Problem: The N-Queens puzzle is the problem of placing 'N' chess queens on an 
 * N×N chessboard such that no two queens attack each other. 
 * Attack means sharing the same row, column, or diagonal.
 * 
 * Logic (Backtracking Strategy):
 * 1. Placement: Since we know each row must contain exactly one queen, we can 
 *    recurse row by row (starting from row 0).
 * 2. Safe Check: Before placing a queen at board[row][col], we check:
 *    - Is there a queen already in the same column?
 *    - Is there a queen on the upper-left diagonal?
 *    - Is there a queen on the upper-right diagonal?
 *    (We don't need to check the current row or below rows because we move 
 *     row by row from top to bottom).
 * 3. Recursion: If a safe spot is found, place the queen and move to the next row.
 * 4. Backtracking: If placing a queen in the current column doesn't lead to a 
 *    valid solution for subsequent rows, remove it ('Q' -> '.') and try the next column.
 * 
 * Complexity:
 * - Time Complexity : O(N!) - Exponential growth.
 * - Space Complexity: O(N^2) - To store the board configuration.
 */
import java.util.*;

public class NQueens {

    static List<List<String>> solutions = new ArrayList<>();

    /**
     * Checks if it's safe to place a queen at board[row][col].
     */
    static boolean isSafe(char[][] board, int row, int col, int n) {
        // 1. Check same column upwards
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        // 2. Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // 3. Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    /**
     * Recursive solver for the N-Queens problem.
     */
    static void solve(char[][] board, int row, int n) {
        // Base Case: All queens are placed correctly
        if (row == n) {
            List<String> sol = new ArrayList<>();
            for (char[] r : board) {
                sol.add(new String(r));
            }
            solutions.add(sol);
            return;
        }

        // Try placing the queen in each column of the current 'row'
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                // Place Queen
                board[row][col] = 'Q';

                // Recurse to next row
                solve(board, row + 1, n);

                // Backtrack: remove the queen to explore other column possibilities
                board[row][col] = '.';
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- N-Queens Solver (Backtracking) ---");
        System.out.print("Enter board size (N): ");
        int n = sc.nextInt();

        // Initialize board with empty slots '.'
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // Start recursion from row 0
        solve(board, 0, n);

        // Print results
        System.out.println("\nTotal distinct solutions found: " + solutions.size());
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("\nSolution #" + (i + 1) + ":");
            for (String rowStr : solutions.get(i)) {
                System.out.println(rowStr.replaceAll(".", "$0 ")); // Add spaces for readability
            }
        }

        sc.close();
    }
}
