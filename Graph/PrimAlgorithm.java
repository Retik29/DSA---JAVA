
/**
 * Prim's Algorithm for Minimum Spanning Tree (MST)
 * 
 * Problem: Given a connected and undirected weighted graph, find a subset 
 * of edges that connects all vertices together, without any cycles and with 
 * the minimum possible total edge weight.
 * 
 * Algorithm: Greedy Strategy
 * 1. Maintain a set 'inMST' that keeps track of vertices already included in MST.
 * 2. Use a PriorityQueue to pick the minimum weight edge that connects 
 *    a vertex in MST to a vertex not in MST.
 * 3. Start from an arbitrary vertex (e.g., node 0).
 * 4. While the PQ is not empty:
 *    - Extract node 'u' with the minimum edge weight.
 *    - If 'u' is already in MST, skip it.
 *    - Mark 'u' as inMST and add its weight to the total weight.
 *    - Push all neighbors of 'u' that are not in MST into the PQ.
 * 
 * Time Complexity : O(E log V) - where E is edges and V is vertices.
 * Space Complexity: O(V + E) - for the adjacency list and the priority queue.
 */
import java.util.*;

public class PrimAlgorithm {

    /**
     * Helper class to represent an edge leading to a vertex 'v'.
     */
    static class Node implements Comparable<Node> {
        int v, weight;

        Node(int v, int w) {
            this.v = v;
            this.weight = w;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    /**
     * Calculates the total weight of the Minimum Spanning Tree.
     */
    static int primMST(int V, ArrayList<ArrayList<Node>> adj) {
        boolean[] inMST = new boolean[V];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // Start from node 0 with weight 0
        pq.add(new Node(0, 0));
        int totalWeight = 0;
        int edgesCount = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v;

            // If vertex is already part of MST, don't process it again
            if (inMST[u])
                continue;

            // Include vertex in MST
            inMST[u] = true;
            totalWeight += node.weight;
            edgesCount++;

            // If we have included all vertices, we can stop early
            if (edgesCount == V)
                break;

            // Add all adjacent edges to the PriorityQueue
            for (Node neighbor : adj.get(u)) {
                if (!inMST[neighbor.v]) {
                    pq.add(new Node(neighbor.v, neighbor.weight));
                }
            }
        }
        return totalWeight;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Prim's Minimum Spanning Tree ---");
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
            // Symmetric for undirected graph
            adj.get(u).add(new Node(v_node, w));
            adj.get(v_node).add(new Node(u, w));
        }

        // Output results
        int mstWeight = primMST(v, adj);
        System.out.println("\n--- MST Result ---");
        System.out.println("Total weight of the Minimum Spanning Tree: " + mstWeight);

        sc.close();
    }
}
