import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] grid;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(isSeparate() < 2){
            if(allzero()){
                result = 0;
                break;
            }
            melt();
            result++;
        }

        System.out.println(result);

        br.close();
    }

    static boolean allzero(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(grid[i][j] != 0) return false;
            }
        }
        return true;
    }

    static void melt(){
        int[][] tmp = new int[N][M];

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                 tmp[i][j] = grid[i][j];
            }
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(grid[i][j] == 0) continue;
                int cnt = 0;
                for(int d = 0; d<4; d++){
                    int nr = i + dy[d];
                    int nc = j + dx[d];
                    if(nr>=0 && nr<N && nc>=0 && nc<M && grid[nr][nc] == 0)
                        cnt++;
                }
                if(grid[i][j] - cnt >= 0){
                    tmp[i][j] = grid[i][j] - cnt;
                }else{
                    tmp[i][j] = 0;
                }
            }
        }
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                grid[i][j] = tmp[i][j];
            }
        }
    }

    static int isSeparate(){
        boolean[][] v = new boolean[N][M];
        int cnt = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(grid[i][j] != 0 && !v[i][j]){
                    DFS(i, j, v);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static void DFS(int y, int x, boolean[][] v){
        v[y][x] = true;

        for(int d = 0; d<4; d++){
            int dr = y + dy[d];
            int dc = x + dx[d];

            if(dr < 0 || dc < 0 || dr >= N || dc >= M)
                continue;

            if(grid[dr][dc] != 0 && !v[dr][dc]){
                DFS(dr, dc, v);
            }
        }
    }
}
