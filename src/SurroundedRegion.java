import java.util.Arrays;

public class SurroundedRegion {

    public static void main(String[] args) {
        char[][] mat = {
                {'X','X','X','X'},
                {'X','O','X','X'},
                {'X','O','O','X'},
                {'X','O','X','X'},
                {'X','X','O','O'}
        };
        char[][] ans = fill(mat);

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }

    }


    public static char[][] fill(char[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        if(n == 0 || m == 0) return mat;

        int[][] visited = new int[n][m];
        int[] dr = {-1, 0 , 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int j = 0; j < m; j++){
            if(visited[0][j] == 0 && mat[0][j] == 'O') dfs(0, j, visited, mat, dr, dc);
            if(visited[n-1][j] == 0 && mat[n-1][j] == 'O') dfs(n-1, j, visited, mat, dr, dc);
        }

        for(int i = 0; i < n; i++){
            if(visited[i][0] == 0 && mat[i][0] == 'O') dfs(i, 0, visited, mat, dr ,dc);
            if(visited[i][m-1] == 0 && mat[i][m-1] == 'O') dfs(i, m-1, visited, mat, dr, dc);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j] == 0 && mat[i][j] == 'O') mat[i][j] = 'X';
            }
        }
        
        return mat;
    }

    private static void dfs(int r, int c, int[][] visited, char[][] mat, int[] dr, int[] dc) {
        int n  = mat.length;
        int m = mat[0].length;
        visited[r][c] = 1;
        for(int k = 0; k < 4; k++){
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(nr >= 0 && nr < n && nc >= 0 && nc < m && visited[nr][nc] == 0 && mat[nr][nc] == 'O'){
                dfs(nr, nc, visited, mat, dr, dc);
            }
        }
    }
}
