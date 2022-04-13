import java.io.*;
import java.util.*;

public class Main {
    static int W, H, cnt;
    static int[][] map;
    static boolean[][] visit;

    static int[] dx = {1, -1, 0, 0, -1, 1, 1, -1};
    static int[] dy = {0, 0, 1, -1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W==0 && H==0) break;

            map = new int[H][W];
            visit = new boolean[H][W];

            for(int i = 0; i<H; i++){
                st = new StringTokenizer(br.readLine(), " ");
                for(int j = 0; j<W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            cnt = 0;

            for(int i= 0; i<H; i++){
                for(int j = 0; j<W; j++){
                    if(map[i][j]==1 && !visit[i][j]){
                        Search(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
        br.close();
    }

    static void Search(int y, int x){
        visit[y][x] = true;

        for(int d=0; d<8; d++){
            int ni = y + dy[d];
            int nj = x + dx[d];

            if(ni>=0 && nj>=0 && ni<H && nj<W){
                if(map[ni][nj] == 1 && !visit[ni][nj])
                    Search(ni, nj);
            }
        }
    }
}
