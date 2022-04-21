import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T; tc++){
            int K;
            int[] novel, sum;
            int[][] dp;

            K = Integer.parseInt(br.readLine());
            novel = new int[K+1];
            dp = new int[K+1][K+1];
            sum = new int[K+1];

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 1; i<=K; i++){
                novel[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + novel[i];
            }

            for(int n = 1; n<=K; n++){
                for(int from = 1; from+n<=K; from++){
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for(int divide = from; divide<to; divide++){
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide+1][to] + sum[to] - sum[from-1]);
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}