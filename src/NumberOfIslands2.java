import java.util.ArrayList;
import java.util.List;

public class NumberOfIslands2 {
    private static boolean isValid(int adjr, int adjc, int n, int m){
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < m;
    }

    public static int[] numOfIslands(int n, int m, int[][] operators){
        DisjointSet disjointSet = new DisjointSet(n*m);
        int[][] vis = new int[n][m];
        int count = 0;
        List<Integer> answer = new ArrayList<>();

        for (int[] it : operators){
            int row = it[0];
            int col = it[1];
            if (vis[row][col] == 1){
                answer.add(count);
                continue;
            }
            vis[row][col] = 1;
            count++;

            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};
            for (int ind = 0; ind < 4; ind++) {
                int adjr = row + dr[ind];
                int adjc = col + dc[ind];
                if (isValid(adjr, adjc, n, m)){
                    if (vis[adjr][adjc] == 1){
                        int nodeNo = row * m + col;
                        int adjNodeNo = adjr * m + adjc;
                        if(disjointSet.findUPar(nodeNo) !=  disjointSet.findUPar(adjNodeNo)){
                            count--;
                            disjointSet.unionBySize(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
            answer.add(count);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
    public static void main(String[] args) {
        int n = 4, m = 5;
        int[][] operators = {
                {0, 0}, {0, 0}, {1, 1}, {1, 0}, {0, 1},
                {0, 3}, {1, 3}, {0, 4}, {3, 2}, {2, 2}, {1, 2}, {0, 2}
        };
        int[] ans = numOfIslands(n, m, operators);
        for (int res : ans) {
            System.out.print(res + " ");
        }
        System.out.println();
    }
}
