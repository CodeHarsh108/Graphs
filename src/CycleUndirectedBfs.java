import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CycleUndirectedBfs {
    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[V];
        Arrays.fill(visited, false);
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        for(int i = 0; i < V; i++){
            if(!visited[i]){
                if(checkForCycle(adj, i, visited, parent)) return true;
            }
        }
        return false;
    }

    private static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int s, boolean[] visited, int[] parent) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(s, -1));
        visited[s] = true;
        while(!q.isEmpty()){
            int node = q.peek().first;
            int par = q.peek().second;
            q.remove();
            for (Integer it : adj.get(node)){
                if(!visited[it]){
                    q.add(new Node(it, node));
                    visited[it] = true;
                } else if (par != it) {
                    return true;
                }
            }
        }


        return false;
    }

    static class Node{
        int first;
        int second;
        public Node(int first, int second){
            this.first = first;
            this.second = second;
        }
    }


    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList < > ());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        boolean answer = isCycle(4, adj);
        if (answer)
            System.out.println("1");
        else
            System.out.println("0");
    }
}
