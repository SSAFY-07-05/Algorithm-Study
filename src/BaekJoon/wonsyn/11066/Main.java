package a0421_bj_11066_파일합치기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_11066.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int size = Integer.parseInt(br.readLine());
			int[] file = new int[size];
			
			st = new StringTokenizer(br.readLine(), " ");
			
			for (int i = 1; i <= size; i++) {
				file[i] = Integer.parseInt(st.nextToken());
			}
			
            int[] sum = new int[size];
            sum[0] = file[0];
            for (int i = 1; i < size; i++) {
                sum[i] = sum[i - 1] + file[i];
            }
            
            int ans = solution(file, sum);
            sb.append(ans).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	static int solution(int[] file, int[] sum) {
		  int[][] dp = new int[file.length][file.length];

	        for (int i = 0; i < dp.length - 1; i++) {
	            dp[i][i + 1] = file[i] + file[i + 1];
	        }

	        for (int j = 2; j < dp.length; j++) {
	            for (int i = 0; i + j < dp.length; i++) {
	                for (int k = i; k < i + j; k++) {
	                    if (dp[i][i + j] == 0) {
	                        dp[i][i + j] = dp[i][k] + dp[k + 1][i + j] + sumDist(sum, i, i + j);
	                    } else {
	                        dp[i][i + j] = Math.min(dp[i][i + j], dp[i][k] + dp[k + 1][i + j] + sumDist(sum, i, i + j));
	                    }
	                }
	            }
	        }

	        return dp[0][dp.length - 1];
	}
	
	static int sumDist(int[] sum, int start, int end) {
        if (start == 0) {
            return sum[end];
        }

        return sum[end] - sum[start - 1];
    }
}
