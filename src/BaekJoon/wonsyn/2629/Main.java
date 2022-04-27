package a0425_bj_2629_양팔저울;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, question, max=15000, arr[];
	static boolean dp[][];
	
	public static void main(String [] args) throws IOException {
		System.setIn(new FileInputStream("res/input_bj_2629.txt"));
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N= Integer.parseInt(br.readLine());
		arr= new int[N];
		dp= new boolean[31][max+1];
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		find_dp(0,0);
		
		StringBuilder sb= new StringBuilder();
		M= Integer.parseInt(br.readLine());
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			question= Integer.parseInt(st.nextToken());
			if(question>15000)  sb.append("N ");
			else sb.append(dp[N][question]?"Y ":"N ");
		}
		System.out.print(sb);
		br.close();
	}
	
	public static void find_dp(int idx, int weight) {
		if(dp[idx][weight]) return;
		dp[idx][weight]=true;
		if(idx==N) return;
		
		find_dp(idx+1, weight+arr[idx]);
		find_dp(idx+1, weight);
		find_dp(idx+1, Math.abs(weight-arr[idx]));
	}
}
