
/**
 * Sudoku Solver (Backtracking)
 * 
 * Problem: A 9x9 grid where some cells are filled with numbers 1-9. 
 * The goal is to fill all empty cells (represented by 0) such that:
 * 1. Each row contains digits 1-9 exactly once.
 * 2. Each column contains digits 1-9 exactly once.
 * 3. Each of the nine 3x3 subgrids contains digits 1-9 exactly once.
 * 
 * Logic (Backtracking Strategy):
 * 1. Find Empty: Find an empty cell (board[i][j] == 0).
 * 2. Try Digits: Try digits 1 to 9 in that cell.
 * 3. Safe Check (Pruning): Before placing a digit, check if it violates any 
 *    Sudoku rules in its row, column, or 3x3 box.
 * 4. Recurse: If safe, place the digit and recursively call solver for the next cell.
 * 5. Backtrack: If the recursion returns false, reset cell to 0 and try the next digit.
 * 
 * Complexity:
 * - Time Complexity : O(9^(n*n)) - In the absolute worst case, it's exponential.
 * - Space Complexity: O(n*n) - For the board and recursion stack.
 */
import java.util.*;

public class SudokuSolver {

    /**
     * Checks if placing 'num' at board[row][col] is valid according to Sudoku
     * rules.
     */
    static boolean isSafe(int[][] board, int row, int col, int num) {
        // 1. Check Row and Column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // 2. Check 3x3 Box
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Main recursive function to solve the Sudoku.
     */
    static boolean solveSudoku(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isFull = true;

        // Step 1: Find the first empty cell
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isFull = false;
                    break;
                }
            }
            if (!isFull)
                break;
        }

        // Base Case: If no empty cells are left, Sudoku is solved
        if (isFull)
            return true;

        // Step 2: Try digits 1 to 9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                // Place digit
                board[row][col] = num;

                // Recursive call to solve for remaining cells
                if (solveSudoku(board)) {
                    return true;
                }

                // Backtrack: if placing 'num' leads to no solution, reset it
                board[row][col] = 0;
            }
        }
        return false; // Triggers backtracking in the caller
    }

    /**
     * Utility to print the 9x9 board.
     */
    static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0 && i != 0)
                System.out.println("---------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && j != 0)
                    System.out.print("| ");
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[9][9];

        System.out.println("--- Sudoku Solver (Backtracking) ---");
        System.out.println("Enter the 9x9 board (use 0 for empty cells):");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        System.out.println("\nSolving...");
        if (solveSudoku(board)) {
            System.out.println("\n--- Solved Sudoku ---");
            printBoard(board);
        } else {
            System.out.println("\nNo solution exists for the given Sudoku board.");
        }

        sc.close();
    }
}
