import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ConnectComponentInMatrix {

    public static int countComponents(int V, int[][] edges){
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges){
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        boolean[] visited = new boolean[V];
        int components = 0;
        for(int i = 0; i < V; i++){
            if (!visited[i]){
                components++;

                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                visited[i] = true;

                while (!q.isEmpty()){
                    int node = q.poll();
                    for (int nbr : adj.get(node)){
                        if(!visited[nbr]){
                            visited[nbr] = true;
                            q.offer(nbr);
                        }
                    }
                }
            }
        }
        return components;
    }

    public static void main(String[] args) {
        int V = 5;

        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};

        System.out.println("Number of Connected Components : " + countComponents(V, edges));
    }
}