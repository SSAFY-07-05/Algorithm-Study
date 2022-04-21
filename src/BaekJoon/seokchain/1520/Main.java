import java.io.*;
import java.util.*;

public class Main {
    static int map[][], dp[][], N, M;
    static int ni[] = {1,-1,0,0};
    static int nj[] = {0,0,-1,1};
    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_1520.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }
        dfs(0,0);
        System.out.println(dp[0][0]);
    }

    static int dfs(int i, int j){

        if(i == N-1 && j == M-1){
            return 1;
        }
        
        if(dp[i][j] == -1){
            dp[i][j] = 0;
            for (int d = 0; d < 4; d++) {
                int di = i + ni[d];
                int dj = j + nj[d];
                if(di < N && di >=0 && dj < M && dj >=0 && map[di][dj] < map[i][j]){
                    dp[i][j] += dfs(di, dj);
                }
            }
        }

        return dp[i][j];
    }
}
