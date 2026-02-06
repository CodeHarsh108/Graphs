import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathInDAG {
    private static void topoSort(int node, List<List<int[]>> adj, boolean[] visited, Stack<Integer> st){
        visited[node] = true;
        for (int[] neighbor : adj.get(node)){
            if (!visited[neighbor[0]]){
                topoSort(neighbor[0], adj, visited, st);
            }
        }
        st.push(node);
    }
    public static int[] shortestPath(int n , int m,  int[][] edges){
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++){
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new int[]{v, wt});
        }
        boolean[] visited = new boolean[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++){
            if (!visited[i]){
                topoSort(i, adj, visited, st);
            }
        }

        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);

        dist[0] = 0;

        while(!st.isEmpty()){
            int node = st.pop();

            if (dist[node] != (int) 1e9){
                for (int[] nei : adj.get(node)){
                    int v = nei[0];
                    int wt = nei[1];

                    if (dist[node] + wt <  dist[v]){
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i] == (int) 1e9){
                dist[i] = -1;
            }
        }

        return dist;

    }

    public static void main(String[] args) {
        int N = 6, M = 7;

        // Edge list with weights
        int[][] edges = {
                {0,1,2}, {0,4,1}, {4,5,4},
                {4,2,2}, {1,2,3}, {2,3,6}, {5,3,1}
        };


        int[] result = shortestPath(N, M, edges);

        // Print the shortest distances
        for (int val : result) {
            System.out.print(val + " ");
        }
    }
    }

