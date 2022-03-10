import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R, C, D;
    static int result = 1;
    static int [][] grid;
    static int[] dx = {0, 1, 0, -1};    // 북 동 남 서
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<M; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(R, C, D);
        System.out.println(result);

        br.close();
    }

    static void DFS(int y, int x, int dir){
        grid[y][x] = 2;

        for(int d = 0; d<4; d++){
            dir -= 1;
            if(dir == -1) dir = 3;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx >= 0 && ny >= 0 && nx<M && ny < N){
                if(grid[ny][nx] == 0){
                    result++;
                    DFS(ny, nx, dir);
                    return;
                }
            }
        }

        // 후진하기
        int dir2 = (dir + 2) % 4;
        int bx = x + dx[dir2];
        int by = y + dy[dir2];
        if(bx >= 0 &&by >= 0 && bx < M && by < N && grid[by][bx] != 1){
            DFS(by, bx, dir);
        }
    }
}