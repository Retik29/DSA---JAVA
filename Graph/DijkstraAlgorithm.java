
/**
 * Dijkstra's Shortest Path Algorithm
 * 
 * Problem: Given a weighted graph and a source vertex, find the shortest 
 * distance from the source to all other vertices.
 * 
 * Algorithm: Greedy Strategy
 * 1. Initialize 'dist' array with Infinity, and dist[source] = 0.
 * 2. Use a PriorityQueue (Min-Heap) to store pairs of (node, distance).
 * 3. While the PQ is not empty:
 *    - Extract the node 'u' with the smallest distance.
 *    - For each neighbor 'v' of 'u':
 *      If (dist[u] + weight(u, v) < dist[v]):
 *         Update dist[v] = dist[u] + weight(u, v)
 *         Add (v, dist[v]) to the PriorityQueue.
 * 
 * Constraint: Works only for graphs with Non-Negative Edge Weights.
 * 
 * Time Complexity : O((V + E) log V) - where V is vertices and E is edges.
 * Space Complexity: O(V) - to store distances and the priority queue.
 */
import java.util.*;

public class DijkstraAlgorithm {

    /**
     * Helper class to represent a node and its distance from the source.
     */
    static class Node implements Comparable<Node> {
        int v, weight;

        Node(int v, int w) {
            this.v = v;
            this.weight = w;
        }

        // Sorting based on weight (Min-Priority queue)
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * Finds shortest paths from source S to all other nodes.
     */
    static void dijkstra(int V, ArrayList<ArrayList<Node>> adj, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances as Infinity
        dist[S] = 0; // Distance to source is 0

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v;

            // Relaxation step
            for (Node neighbor : adj.get(u)) {
                if (dist[u] != Integer.MAX_VALUE && dist[u] + neighbor.weight < dist[neighbor.v]) {
                    dist[neighbor.v] = dist[u] + neighbor.weight;
                    pq.add(new Node(neighbor.v, dist[neighbor.v]));
                }
            }
        }

        // Print calculated shortest distances
        System.out.println("\nShortest distances from source node " + S + ":");
        System.out.println("Node \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Dijkstra's Shortest Path ---");
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();

        ArrayList<ArrayList<Node>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter " + e + " edges (u v weight):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v_node = sc.nextInt();
            int w = sc.nextInt();
            // Assuming undirected graph for this demo
            adj.get(u).add(new Node(v_node, w));
            adj.get(v_node).add(new Node(u, w));
        }

        System.out.print("\nEnter source node index: ");
        int source = sc.nextInt();

        dijkstra(v, adj, source);

        sc.close();
    }
}
