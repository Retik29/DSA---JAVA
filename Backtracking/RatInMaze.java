
// Rat in a Maze — find all paths from (0,0) to (n-1,n-1)
// Time: O(4^(n²)) | Space: O(n²)
import java.util.*;

public class RatInMaze {

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static char[] dir = { 'D', 'U', 'R', 'L' };

    static void findPaths(int[][] maze, int r, int c, int n, boolean[][] vis, String path, List<String> res) {
        if (r == n - 1 && c == n - 1) {
            res.add(path);
            return;
        }
        vis[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dx[d], nc = c + dy[d];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && !vis[nr][nc] && maze[nr][nc] == 1)
                findPaths(maze, nr, nc, n, vis, path + dir[d], res);
        }
        vis[r][c] = false; // backtrack
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter maze size n: ");
        int n = sc.nextInt();
        int[][] maze = new int[n][n];
        System.out.println("Enter maze (1=open, 0=blocked):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                maze[i][j] = sc.nextInt();

        List<String> paths = new ArrayList<>();
        if (maze[0][0] == 1 && maze[n - 1][n - 1] == 1)
            findPaths(maze, 0, 0, n, new boolean[n][n], "", paths);

        System.out.println(paths.isEmpty() ? "No path exists" : "Paths: " + paths);

        sc.close();
    }
}
