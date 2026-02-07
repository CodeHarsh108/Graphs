import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijsktraTwo {
    public static int[] dijsktra(int V, ArrayList<int[]>[] adj, int S){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;
        pq.offer(new int[]{0, S});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int dis = curr[0];
            int node = curr[1];

            for(int[] edge : adj[node]){
                int adjNode = edge[0];
                int weight = edge[1];

                if(dis + weight < dist[adjNode]){
                    dist[adjNode] = dis + weight;
                    pq.offer(new int[]{dist[adjNode], adjNode});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
            // Number of vertices and edges
            int V = 3, E = 3, S = 2;

            // Create adjacency list to represent the graph
            ArrayList<int[]>[] adj = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new ArrayList<>();
            }

            // Add edges to the graph
            adj[0].add(new int[]{1, 1});
            adj[0].add(new int[]{2, 6});
            adj[1].add(new int[]{2, 3});
            adj[1].add(new int[]{0, 1});
            adj[2].add(new int[]{1, 3});
            adj[2].add(new int[]{0, 6});

            int[] res = dijsktra(V, adj, S);

            // Output the shortest distance from source to all nodes
            for (int i = 0; i < V; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
        }
    }

