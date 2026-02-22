
// Word Search in 2D Grid — backtracking
// Time: O(N × M × 4^L) where L = word length | Space: O(L)
import java.util.*;

public class WordSearch {

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static boolean search(char[][] board, String word, int r, int c, int idx) {
        if (idx == word.length())
            return true;
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length)
            return false;
        if (board[r][c] != word.charAt(idx))
            return false;

        char temp = board[r][c];
        board[r][c] = '#'; // mark visited
        for (int d = 0; d < 4; d++)
            if (search(board, word, r + dx[d], c + dy[d], idx + 1))
                return true;
        board[r][c] = temp; // backtrack
        return false;
    }

    static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (search(board, word, i, j, 0))
                    return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter grid rows and cols: ");
        int rows = sc.nextInt(), cols = sc.nextInt();
        char[][] board = new char[rows][cols];
        System.out.println("Enter grid characters:");
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                board[i][j] = sc.next().charAt(0);

        System.out.print("Enter word to search: ");
        String word = sc.next();

        System.out.println("Word \"" + word + "\" exists? " + exist(board, word));

        sc.close();
    }
}
