
/**
 * Graph Basics: Adjacency List, BFS, and DFS
 * 
 * This class demonstrates the fundamental way to represent and traverse a graph.
 * 
 * Representation: Adjacency List
 * - An array of lists where each list at index 'i' contains all vertices 
 *   adjacent to vertex 'i'.
 * - Pros: Efficient for sparse graphs, O(V+E) space.
 * 
 * Traversals:
 * 1. Breadth First Search (BFS):
 *    - Explores the graph level by level.
 *    - Uses a Queue data structure.
 *    - Application: Shortest path in unweighted graphs.
 * 
 * 2. Depth First Search (DFS):
 *    - Explores as far as possible along each branch before backtracking.
 *    - Uses Recursion (Call Stack).
 *    - Application: Path finding, cycle detection, topological sort.
 * 
 * Time Complexity : O(V + E) for both traversals.
 * Space Complexity: O(V + E) to store the adjacency list + O(V) for visited array.
 */
import java.util.*;

public class GraphBasics {
    private int V; // Number of vertices
    private LinkedHashSet<Integer> adj[]; // List of adjacent nodes for each vertex

    /**
     * Constructor for Graph.
     */
    @SuppressWarnings("unchecked")
    public GraphBasics(int v) {
        V = v;
        adj = new LinkedHashSet[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedHashSet<>();
        }
    }

    /**
     * Adds an undirected edge between vertices v and w.
     */
    void addEdge(int v, int w) {
        if (v < V && w < V) {
            adj[v].add(w);
            adj[w].add(v); // Symmetric for undirected graph
        }
    }

    /**
     * Breadth First Search (BFS).
     * Logic: Use a queue to keep track of nodes to visit next.
     */
    void BFS(int startNode) {
        boolean visited[] = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        System.out.print("BFS (Level Order): ");
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");

            // Visit neighbors
            for (int neighbor : adj[curr]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    /**
     * Depth First Search (DFS).
     * Logic: Use recursion to visit the deepest possible nodes first.
     */
    void DFS(int startNode) {
        boolean visited[] = new boolean[V];
        System.out.print("DFS (Depth First): ");
        dfsRecursive(startNode, visited);
        System.out.println();
    }

    private void dfsRecursive(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        // Recursively visit all neighbors
        for (int neighbor : adj[v]) {
            if (!visited[neighbor]) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Graph Basics (BFS/DFS) ---");
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        GraphBasics g = new GraphBasics(v);

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter " + e + " edges (format: u v):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v_edge = sc.nextInt();
            g.addEdge(u, v_edge);
        }

        System.out.print("\nEnter starting vertex: ");
        int startNode = sc.nextInt();

        g.BFS(startNode);
        g.DFS(startNode);

        sc.close();
    }
}
