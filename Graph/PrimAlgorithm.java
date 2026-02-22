
// Prim's Algorithm for Minimum Spanning Tree (MST)
// Time Complexity: O(E log V) | Space Complexity: O(V + E)
import java.util.*;

public class PrimAlgorithm {
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

    static int primMST(int V, ArrayList<ArrayList<Node>> adj) {
        boolean[] inMST = new boolean[V];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        int mstWeight = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.v;

            if (inMST[u])
                continue;
            inMST[u] = true;
            mstWeight += node.weight;

            for (Node neighbor : adj.get(u)) {
                if (!inMST[neighbor.v]) {
                    pq.add(new Node(neighbor.v, neighbor.weight));
                }
            }
        }
        return mstWeight;
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
            adj.get(v_node).add(new Node(u, w));
        }

        System.out.println("Weight of MST: " + primMST(v, adj));
        sc.close();
    }
}
