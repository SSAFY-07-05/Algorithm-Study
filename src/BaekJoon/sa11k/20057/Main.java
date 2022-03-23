import java.io.*;
import java.util.*;

public class Main {
    static int N, out=0;
    static int[][] grid;
    static int[][][] sandpercent = {{{1,1,0,0,-2,0,0,-1,-1,-1},{-1,1,-2,2,0,-1,1,-1,1,0}},    // 서쪽
                                    {{-1,1,-2,2,0,-1,1,-1,1,0},{-1,-1,0,0,2,0,0,1,1,1}},    // 남쪽
                                    {{-1,-1,0,0,2,0,0,1,1,1},{1,-1,2,-2,0,-1,1,-1,1,0}},    // 동쪽
                                    {{-1,1,-2,2,0,-1,1,-1,1,0},{1,1,0,0,-2,0,0,-1,-1,-1}}};   // 북쪽
    static int[] percent = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        spreadSand(N/2, N/2);

        System.out.println(out);

        br.close();
    }

    static void spreadSand(int x, int y){
        int nowX = x;
        int nowY = y;
        int nowD = 0;
        int move = 1;
        int cnt = 0;

        while(true){
            if(cnt == 2){
                cnt = 0;
                if(nowY > 0)
                    move++;
            }

            for(int i = 0; i<move; i++){
                int nx = nowX + dx[nowD];
                int ny = nowY + dy[nowD];

                if(grid[ny][nx] > 0){
                    int totalsand = grid[ny][nx];

                    for(int j = 0; j<9; j++){
                        int sandX = nx + sandpercent[nowD][0][j];
                        int sandY = ny + sandpercent[nowD][1][j];

                        int sand = grid[ny][nx] * percent[j] / 100;
                        totalsand -= sand;

                        if((0<=sandX && sandX<N) && (0<=sandY && sandY<N)){
                            grid[sandY][sandX] += sand;
                        }
                        else
                            out += sand;
                    }

                    int sandX = nx + sandpercent[nowD][0][9];
                    int sandY = ny + sandpercent[nowD][1][9];

                    if((0<=sandX && sandX<N) && (0<=sandY && sandY<N)){
                        grid[sandY][sandX] += totalsand;
                        grid[ny][nx] = 0;
                    }
                    else
                        out += totalsand;

                }

                nowX = nx;
                nowY = ny;
            }

            cnt++;
            nowD = (nowD+1) % 4;

            if(nowX == 0 && nowY == 0)
                break;
        }
    }
}