import java.util.ArrayList;

public class CycleUndirectedDfs {
    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[V];

        for(int i = 0; i < V; i++){
            if(!visited[i]){
                if (dfs(i, -1, adj, visited)) return true;
            }
        }
        return false;
    }

    private static boolean dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
        visited[node] = true;
        for (int n : adj.get(node)){
            if(!visited[n]){
                if (dfs(n, node, adj, visited)) return true;
            } else if (n != parent) {
                return true;
            }
        }


        return false;
    }

    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        // Add edges
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(4);
        adj.get(4).add(3);
        adj.get(4).add(1);

        if(isCycle(V, adj)){
            System.out.println("Cycle Detected!!");
        }else {
            System.out.println("No Cycle Found!!");
        }
    }
}
