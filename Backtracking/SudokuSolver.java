
// Sudoku Solver - Backtracking
// Time Complexity: O(9^(n*n)) | Space Complexity: O(n*n)
import java.util.*;

public class SudokuSolver {
    static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int d = 0; d < 9; d++) {
            if (board[row][d] == num || board[d][col] == num)
                return false;
        }
        int sqrt = 3;
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num)
                    return false;
            }
        }
        return true;
    }

    static boolean solveSudoku(int[][] board, int n) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty)
                break;
        }

        if (isEmpty)
            return true;

        for (int num = 1; num <= n; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board, n))
                    return true;
                else
                    board[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[9][9];
        System.out.println("Enter Sudoku board (9x9), use 0 for empty cells:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        if (solveSudoku(board, 9)) {
            System.out.println("Solved Sudoku:");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists");
        }
        sc.close();
    }
}
