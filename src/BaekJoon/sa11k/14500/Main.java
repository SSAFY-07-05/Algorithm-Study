import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result;
    static int[][] grid;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

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

        visit = new boolean[N][M];
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                visit[i][j] = true;
                DFS(i, j, 1, grid[i][j]);
                visit[i][j] = false;
                fuck(i, j);
            }
        }

        System.out.println(result);

        br.close();
    }

    static void DFS(int y, int x, int cnt, int sum) {
        if(cnt >= 4){
            result = Math.max(result, sum);
            return;
        }

        for(int d = 0; d<4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny<0 || nx<0 || ny>=N || nx>=M || visit[ny][nx])
                continue;

            visit[ny][nx] = true;
            DFS(ny, nx, cnt+1, sum+grid[ny][nx]);
            visit[ny][nx] = false;
        }
    }

    static void fuck(int y, int x){
        if(y < N-2 && x < M-1)
            result = Math.max(result, grid[y][x] + grid[y+1][x] + grid[y+2][x] + grid[y+1][x+1]);

        if(y < N-2 && x >0)
            result = Math.max(result, grid[y][x] + grid[y+1][x] + grid[y+2][x] + grid[y+1][x-1]);

        if(y < N-1 && x <M-2)
            result = Math.max(result, grid[y][x] + grid[y][x+1] + grid[y][x+2] + grid[y+1][x+1]);

        if(y > 0 && x < M-2)
            result = Math.max(result, grid[y][x] + grid[y][x+1] + grid[y][x+2] + grid[y-1][x+1]);
    }
}