package a0404_d9_2112_보호필름;

import java.io.*;
import java.util.*;

public class Solution {
	static int D, W, K, min;
	static int[][] film;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d9_2112.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			min = Integer.MAX_VALUE;
			film = new int[D][W];
			
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			change(0, 0);
			sb.append(min).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	static boolean check() {
		for(int j = 0; j < W; j++) {
			int cnt = 1;
			for (int i = 1; i < D; i++) {
				if(film[i][j] == film[i-1][j]) cnt++;
				else cnt = 1;
				
				if(cnt == K) break;
			}
			if(cnt != K) return false;
		}
		return true;
	}
	
	static void change(int r, int cnt) {
		if(r == D) {
			if(check()) {
				min = Math.min(min, cnt);
			}
			return;
		}
		
		int[] cp = Arrays.copyOf(film[r], W);
		set(r, 0);  // A세팅
		change(r + 1, cnt + 1);
		set(r, 1);  // B세팅
		change(r + 1, cnt + 1);
		film[r] = cp;
		
		change(r + 1, cnt);
	}
	
	static void set(int r, int s) {
		for(int i = 0; i < W; i++) {
			film[r][i] = s;
		}
	}
}