
/**
 * Rat in a Maze Problem (Backtracking)
 * 
 * Problem: A rat starts from (0, 0) and wants to reach (n-1, n-1) in an n×n maze. 
 * The maze contains 1s (open) and 0s (blocked). The rat can move in four 
 * directions: Down (D), Up (U), Right (R), or Left (L).
 * 
 * Logic (Recursive Backtracking):
 * 1. Base Case: If current position is (n-1, n-1), we've found a valid path.
 * 2. Navigation: From current (r, c), try moving in all 4 directions.
 * 3. Validation: A move to (nr, nc) is valid if:
 *    - It's within the maze boundaries.
 *    - The cell is not blocked (maze[nr][nc] == 1).
 *    - The cell has not been visited in the current path (!vis[nr][nc]).
 * 4. Backtracking: 
 *    - Mark cell as visited (vis[r][c] = true) before recursing further.
 *    - CRITICAL: Mark cell as unvisited (vis[r][c] = false) after returning 
 *      from recursion. This allows other potential paths to use this cell.
 * 
 * Complexity:
 * - Time Complexity : O(4^(n^2)) - In the worst case, we explore 4 directions from every cell.
 * - Space Complexity: O(n^2) - To store the visited markers and recursion stack.
 */
import java.util.*;

public class RatInMaze {

    // Movement vectors: Down, Up, Right, Left
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static char[] dir = { 'D', 'U', 'R', 'L' };

    /**
     * Finds all possible paths from (0,0) to (n-1, n-1).
     */
    static void findPaths(int[][] maze, int r, int c, int n, boolean[][] vis, String path, List<String> res) {
        // Base Case: Destination reached
        if (r == n - 1 && c == n - 1) {
            res.add(path);
            return;
        }

        // Mark current cell as visited
        vis[r][c] = true;

        // Try all 4 possible movements
        for (int d = 0; d < 4; d++) {
            int nr = r + dx[d];
            int nc = c + dy[d];

            // Boundary and validity check
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc] && maze[nr][nc] == 1) {
                findPaths(maze, nr, nc, n, vis, path + dir[d], res);
            }
        }

        // Backtrack: Reset visited state for current cell for other path explorations
        vis[r][c] = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Rat in a Maze Solver ---");
        System.out.print("Enter maze dimensions (N): ");
        int n = sc.nextInt();

        int[][] maze = new int[n][n];
        System.out.println("Enter the " + n + "x" + n + " maze (1 for open path, 0 for wall):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        List<String> paths = new ArrayList<>();
        // Start search if both start and end points are open
        if (maze[0][0] == 1 && maze[n - 1][n - 1] == 1) {
            findPaths(maze, 0, 0, n, new boolean[n][n], "", paths);
        }

        // Output results
        System.out.println("\n--- Result Summary ---");
        if (paths.isEmpty()) {
            System.out.println("No valid paths found from (0,0) to (" + (n - 1) + "," + (n - 1) + ").");
        } else {
            System.out.println("Number of paths found: " + paths.size());
            System.out.println("Possible directions (D=Down, U=Up, R=Right, L=Left):");
            for (String p : paths) {
                System.out.println("Path: " + p);
            }
        }

        sc.close();
    }
}
