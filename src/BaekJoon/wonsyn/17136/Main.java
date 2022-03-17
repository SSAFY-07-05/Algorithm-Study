package a0316_bj_17136_색종이붙이기;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] wallPaper;
	static int[] remain = {5, 5, 5, 5, 5};
	static int ans = 26;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17136.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wallPaper = new int[10][10];
		
		for(int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 10; j++)
				wallPaper[i][j] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 0);
		
		System.out.println((ans == 26)? -1 : ans);
		br.close();
	}
	
	static void dfs(int x, int y, int cnt) {
		if (x >= 9 && y > 9) {
			ans = Math.min(ans,  cnt);
			return;
		}
		
		if(ans <= cnt) return;
		
		if(y > 9) {
			dfs(x + 1, 0, cnt);
			return;
		}
		
		if(wallPaper[x][y] == 1) {
			for(int i = 5; i >= 1; i--) {
				if(remain[i - 1] > 0 && isPossible(x, y, i)) {
					change(x, y, i, 0);
					remain[i - 1]--;
					dfs(x, y + 1, cnt + 1);
					change(x, y, i, 1);
					remain[i - 1]++;
				}
			}
		} else dfs(x, y + 1, cnt);
	}
	
	static void change(int x, int y, int size, int state) {
		for(int i = x; i < x + size; i++)
			for (int j = y; j < y + size; j++)
				wallPaper[i][j] = state;
	}
	
	static boolean isPossible(int x, int y, int size) {
		if(x + size > 10 || y + size > 10) return false;
		
		for (int i = x; i < x + size; i++)
			for (int j = y; j < y + size; j++)
				if(wallPaper[i][j] != 1) return false;
		
		return true;
	}
}
