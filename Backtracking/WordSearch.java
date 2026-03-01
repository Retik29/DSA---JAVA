
/**
 * Word Search in 2D Grid (Backtracking)
 * 
 * Problem: Given an m x n grid of characters and a string 'word', return true 
 * if 'word' exists in the grid. The word can be constructed from letters of 
 * sequentially adjacent cells, where adjacent cells are horizontally or 
 * vertically neighboring. The same letter cell may not be used more than once.
 * 
 * Logic (Recursive Backtracking):
 * 1. Entry Point: Start a search from every cell (i, j) in the grid.
 * 2. Pruning: If board[i][j] doesn't match the first character of the word, skip.
 * 3. DFS Traversal:
 *    - Base Case: If idx == word.length, we found the complete word.
 *    - Boundary Check: Ensure (r, c) is within grid limits.
 *    - Character Match: Ensure board[r][c] == word.charAt(idx).
 * 4. Backtracking (Cell Tracking):
 *    - To prevent using the same cell twice, temp-mark the current cell (e.g., '#').
 *    - Explore all 4 neighbors (Up, Down, Left, Right).
 *    - If any branch returns true, word exists.
 *    - Backtrack: Restore the original character to the cell so it can be 
 *      used by other starting points or paths.
 * 
 * Complexity:
 * - Time Complexity : O(N * M * 4^L) - Where N*M is grid size and L is word length.
 * - Space Complexity: O(L) - Maximum recursion depth is the length of the word.
 */
import java.util.*;

public class WordSearch {

    // Vectors for 4-directional movement: Down, Up, Right, Left
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    /**
     * Recursive backtracking function to search for the word.
     */
    static boolean search(char[][] board, String word, int r, int c, int idx) {
        // Base Case: Entire word matched
        if (idx == word.length()) {
            return true;
        }

        // Boundary Check and Character Match Check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(idx)) {
            return false;
        }

        // Step 1: Temporarily mask current cell (Mark as visited)
        char tempChar = board[r][c];
        board[r][c] = '#';

        // Step 2: Explore all 4 adjacent directions
        for (int d = 0; d < 4; d++) {
            if (search(board, word, r + dx[d], c + dy[d], idx + 1)) {
                return true; // Match found in this branch!
            }
        }

        // Step 3: Backtrack - Restore original character for other search attempts
        board[r][c] = tempChar;

        return false;
    }

    /**
     * Checks if the word exists starting from any cell in the grid.
     */
    static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the first character matches, start DFS from here
                if (board[i][j] == word.charAt(0)) {
                    if (search(board, word, i, j, 0))
                        return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- 2D Grid Word Search ---");
        System.out.print("Enter grid dimensions (rows cols): ");
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        char[][] board = new char[rows][cols];
        System.out.println("Enter the " + rows + "x" + cols + " character grid:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = sc.next().charAt(0);
            }
        }

        System.out.print("Enter the word to search for: ");
        String word = sc.next();

        // Execution
        boolean found = exist(board, word);

        // Result Output
        System.out.println("\n--- Search Result ---");
        if (found) {
            System.out.println("The word \"" + word + "\" was FOUND in the grid.");
        } else {
            System.out.println("The word \"" + word + "\" was NOT found.");
        }

        sc.close();
    }
}
