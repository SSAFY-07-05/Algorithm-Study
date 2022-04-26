import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new boolean[N+1][40001];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dp(0, 0);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++){
            int t = Integer.parseInt(st.nextToken());
            if(dp[N][t])
                sb.append("Y ");
            else
                sb.append("N ");
        }

        System.out.println(sb);

        br.close();
    }

    static void dp(int cnt, int num){
        if(dp[cnt][num]) return;
        dp[cnt][num] = true;

        if(cnt == N) return;

        dp(cnt+1, num+arr[cnt]);
        dp(cnt+1, num);
        dp(cnt+1, Math.abs(num-arr[cnt]));
    }
}