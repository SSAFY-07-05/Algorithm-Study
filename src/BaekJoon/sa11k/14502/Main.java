import java.io.*;
import java.util.*;

public class Main {
    static int N, M, result = 0;
    static int[][] grid, copyGrid;
    static int[] dx = {0, 1, 0, -1};    // 상, 우, 하, 좌
    static int[] dy = {-1, 0, 1, 0};
    static Deque<int[]> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        copyGrid = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(0);

        System.out.println(result);

        br.close();
    }

    static void makeWall(int cnt){
        if(cnt == 3){
            spreadVirus();
            checkSafe();
            return;
        }

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(grid[i][j] == 0){
                    grid[i][j] = 1;
                    makeWall(cnt+1);
                    grid[i][j] = 0;
                }
            }
        }
    }

    static void spreadVirus(){
        q = new ArrayDeque<>();

        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                copyGrid[i][j] = grid[i][j];
                if(copyGrid[i][j] == 2){
                    q.add(new int[] {j, i});
                }
            }
        }

        while(!q.isEmpty()) {
            int[] a = q.poll();

            int x = a[0];
            int y = a[1];

            for(int d=0; d<4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx>=0 && nx < M && ny>=0 && ny <N){
                    if(copyGrid[ny][nx] == 0){
                        q.add(new int[] {nx, ny});
                        copyGrid[ny][nx] = 2;
                    }
                }
            }
        }
    }

    static void checkSafe(){
        int cnt = 0;
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                if(copyGrid[i][j]==0)
                    cnt++;
            }
        }

        result = Math.max(result, cnt);
    }
}