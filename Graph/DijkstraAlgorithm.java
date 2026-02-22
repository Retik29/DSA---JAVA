
// Dijkstra's Shortest Path Algorithm
// Time Complexity: O((V+E)logV) | Space Complexity: O(V)
import java.util.*;

public class DijkstraAlgorithm {
    static class Node implements Comparable<Node> {
        int v, weight;

        Node(int v, int w) {
            this.v = v;
            this.weight = w;
        }

        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
    }

    static void dijkstra(int V, ArrayList<ArrayList<Node>> adj, int S) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v;

            for (Node neighbor : adj.get(u)) {
                if (dist[u] + neighbor.weight < dist[neighbor.v]) {
                    dist[neighbor.v] = dist[u] + neighbor.weight;
                    pq.add(new Node(neighbor.v, dist[neighbor.v]));
                }
            }
        }

        System.out.println("Shortest distances from source " + S + ":");
        for (int i = 0; i < V; i++)
            System.out.println("Node " + i + " : " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        ArrayList<ArrayList<Node>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++)
            adj.add(new ArrayList<>());

        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        System.out.println("Enter edges (u v weight):");
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v_node = sc.nextInt();
            int w = sc.nextInt();
            adj.get(u).add(new Node(v_node, w));
            adj.get(v_node).add(new Node(u, w)); // Undirected
        }

        System.out.print("Enter source node: ");
        int source = sc.nextInt();
        dijkstra(v, adj, source);
        sc.close();
    }
}
