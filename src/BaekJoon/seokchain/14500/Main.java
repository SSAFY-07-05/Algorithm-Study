import java.io.*;
import java.util.*;

public class Main{

    static int  N, M, max, map[][];
    static boolean v[][];
    static int ni[] = {1, -1, 0, 0};
    static int nj[] = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {

        //System.setIn(new FileInputStream("res/input_14500.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                v[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                v[i][j] = false;
                check(i,j);
            }
        }
        System.out.println(max);
    }

    static void dfs(int i, int j , int cnt, int sum){

        if(cnt == 4){
            max = Math.max(sum, max);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int di = i+ni[d];
            int dj = j+nj[d];
            if(di < N && di>=0 && dj < M && dj >= 0 && !v[di][dj]){
                v[di][dj] = true;
                dfs(di, dj, cnt+1, sum+map[di][dj]);
                v[di][dj] = false;
            }
        }
    }

    static void check(int i, int j){

        if(i+2< N && i+2>=0 && j+1 <M && j+1 >=0){
            max = Math.max(max, map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j+1]);
        }

        if(i+2< N && i+2>=0 && j-1 <M && j-1 >=0){
            max = Math.max(max, map[i][j] + map[i+1][j] + map[i+2][j] + map[i+1][j-1]);
        }

        if(j+2< M && j+2>=0 && i+1 < N && i+1 >=0){
            max = Math.max(max, map[i][j] + map[i][j+1] + map[i][j+2] + map[i+1][j+1]);
        }

        if(j+2< M && j+2>=0 && i-1 < N && i-1 >=0){
            max = Math.max(max, map[i][j] + map[i][j+1] + map[i][j+2] + map[i-1][j+1]);
        }
    }

}










