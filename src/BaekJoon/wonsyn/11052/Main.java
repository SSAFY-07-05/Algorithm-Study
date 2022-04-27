package a0427_bj_11052_카드구매하기;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] packs;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_11052.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		packs = new int[N + 1];
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			packs[i] = Integer.parseInt(st.nextToken());
			sum += packs[i];
		}
		
		dp = new int[N + 1][N + 1];
		for(int i = 0; i < N + 1; i++) {
			dp[i][0] = 0; dp[0][i] = 0;
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(j - i < 0) dp[i][j] = dp[i - 1][j];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - i] + packs[i]);
			}
		}
		
		System.out.print(dp[N][N]);
		br.close();
	}
}
