import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] con = new int[N+1][2];
        int[] dp = new int[N+2];

        for(int i = 1; i<N+1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            con[i][0] = Integer.parseInt(st.nextToken());
            con[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = N; i>0; i--){
            if(i+con[i][0]>N+1) dp[i] = dp[i+1];
            else dp[i] = Math.max(dp[i+1], con[i][1]+dp[i+con[i][0]]);
        }
        System.out.println(dp[1]);

        br.close();
    }
}