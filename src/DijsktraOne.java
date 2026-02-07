import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class DijsktraOne {

    static class Pair{
        int dist;
        int node;
        Pair(int d, int n){
            dist = d;
             node = n;
        }
    }

    public static int[] dijsktra(int V, ArrayList<int[]>[] adj, int S){
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        TreeSet<Pair> set = new TreeSet<>(
                (a,b) -> {
                    if (a.dist != b.dist){
                        return a.dist - b.dist;
                    }
                    return a.node - b.node;
                }
        );

        set.add(new Pair(0, S));
        while(!set.isEmpty()){
            Pair cur = set.pollFirst();
            int u = cur.node;
            for(int[] edge : adj[u]){
                int v = edge[0];
                int wt = edge[1];

                if (dist[u] + wt < dist[v]){
                    if (dist[v] != Integer.MAX_VALUE){
                        set.remove(new Pair(dist[v], v));
                    }
                    dist[v] = dist[u] + wt;
                    set.add(new Pair(dist[v], v));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        int V = 5; // number of vertices

        // Adjacency list
        ArrayList<int[]>[] adj = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Adding edges (u -> v, weight)
        addEdge(adj, 0, 1, 2);
        addEdge(adj, 0, 4, 1);
        addEdge(adj, 1, 2, 3);
        addEdge(adj, 1, 3, 8);
        addEdge(adj, 2, 3, 2);
        addEdge(adj, 4, 3, 9);

        int source = 0;

        int[] dist = dijsktra(V, adj, source);

        System.out.println("Shortest distances from source " + source + ":");

        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " -> " + dist[i]);
        }
    }

    static void addEdge(ArrayList<int[]>[] adj, int u, int v, int w) {
        adj[u].add(new int[]{v, w});
        adj[v].add(new int[]{u, w}); // remove if graph is directed
    }

}
