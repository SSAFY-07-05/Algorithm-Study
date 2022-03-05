package a0304_bj_14501_퇴사;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int max = Integer.MIN_VALUE;
	static int[][] schedule;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14501.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		schedule = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0);
		System.out.println(max);
		br.close();
	}
	
	static void dfs(int day, int val) {
		if(day >= N) {
			max = Math.max(max, val);
			return;
		}
		
		// 현재 day 포함
		if(day + schedule[day][0] <= N) dfs(day + schedule[day][0], val + schedule[day][1]);
		else dfs(day + schedule[day][0], val);
		
		// 현재 day 미포함
		dfs(day + 1, val);
	}
}
