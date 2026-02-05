import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDictionary {
    private static List<Integer> topoSort(int V, List<List<Integer>> adj){
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++){
            for (int nei : adj.get(i)){
                inDegree[nei]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0){
                q.offer(i);
            }
        }
        List<Integer> topo = new ArrayList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            topo.add(node);
            for(int n : adj.get(node)){
                inDegree[n]--;
                if (inDegree[n] == 0){
                    q.offer(n);
                }
            }
        }
        return topo;
    }

    public static String findOrder(String[] dict, int N, int K){
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++){
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i+1];
            int len = Math.min(s1.length(), s2.length());
            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)){
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break;
                }
            }
        }
        List<Integer> topo = topoSort(K, adj);
        StringBuilder ans = new StringBuilder();
        for(int node : topo){
            ans.append((char)(node + 'a'));
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        int N = 5, K = 4;
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        String ans = findOrder(dict, N, K);

        for (char ch : ans.toCharArray()) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }
}
