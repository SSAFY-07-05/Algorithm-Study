import java.io.*;
import java.util.*;

public class Solution{

    static int D, W, K, totalCnt,  res;
    static int map[][];

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/input_s_2112.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T ; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[D][W];
            res = 14;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            check();
            if(totalCnt == W || K ==1) res = 0;
            else dfs(0, 0);

            sb.append("#").append(tc).append(" ").append(res).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    // 성능 검사
    static void check(){
        totalCnt = 0;
        for (int j = 0; j < W; j++) {
            int cnt = 1;
            for (int i = 1; i < D; i++) {
                if(cnt == K) break;
                if(map[i-1][j]==map[i][j]) cnt++;
                else cnt = 1;
            }
            if(cnt == 1) return;
            if(cnt == K) totalCnt++;
        }
    }

    static void dfs(int i, int cnt){
        if(cnt>res) return;

        if(i>=D){
            check();
            if(totalCnt == W) res = Math.min(res,cnt);
            return;
        }

        int [] tmp = new int[W];
        for (int j = 0; j < W; j++) { tmp[j] = map[i][j]; }

        dfs(i+1,cnt); // 주입X
        for (int j = 0; j < W; j++) { map[i][j] = 0; } //A 주입
        dfs(i+1 , cnt+1);
        for (int j = 0; j < W; j++) { map[i][j] = 1; } //B 주입
        dfs(i+1 , cnt+1);

        for (int j = 0; j < W; j++) { map[i][j] = tmp[j]; }

    }
}
