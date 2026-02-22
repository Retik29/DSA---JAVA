
// Graph Basics: Adjacency List, BFS, and DFS
// Time Complexity: O(V + E) | Space Complexity: O(V + E)
import java.util.*;

public class GraphBasics {
    private int V;
    private LinkedList<Integer> adj[];

    @SuppressWarnings("unchecked")
    public GraphBasics(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v); // Undirected Graph
    }

    void BFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        System.out.print("BFS traversal starting from node " + s + ": ");
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            for (int n : adj[s]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean visited[] = new boolean[V];
        System.out.print("DFS traversal starting from node " + v + ": ");
        DFSUtil(v, visited);
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        GraphBasics g = new GraphBasics(v);

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v_edge = sc.nextInt();
            g.addEdge(u, v_edge);
        }

        System.out.print("Enter starting node for traversals: ");
        int startNode = sc.nextInt();

        g.BFS(startNode);
        g.DFS(startNode);

        sc.close();
    }
}
