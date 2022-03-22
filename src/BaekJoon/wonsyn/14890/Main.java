package a0322_bj_14890_경사로;

import java.io.*;
import java.util.*;

public class Main {
	static int N, L;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14890.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		// 행 확인
		Nextrow: for (int i = 0; i < N; i++) {
			boolean[] check = new boolean[N];
			for(int j = 0; j < N - 1; j++) {
				int val = map[i][j] - map[i][j + 1];
				if (Math.abs(val) > 1) continue Nextrow;
				if(val == 0) continue;
				
				if(val == 1) {
					if(L == 1) {
						if(check[j + 1]) continue Nextrow;
						check[j + 1] = true;
						continue;
					}
					
					for(int k = j + 1, r = 0; r < L - 1; r++, k++)
						if(k + 1 >= N || map[i][k] != map[i][k + 1] || check[k] || check[k + 1]) continue Nextrow;
					
					for(int k = j + 1, r = 0; r < L; r++, k++) {
						check[k] = true;
					}
				} else if(val == -1) {
					if(L == 1) {
						if(check[j]) continue Nextrow;
						check[j] = true;
						continue;
					}
					
					for(int k = j, r = 0; r < L - 1; r++, k--)
						if(k - 1 < 0 || map[i][k] != map[i][k - 1] || check[k] || check[k - 1]) continue Nextrow;
					
					for(int k = j, r = 0; r < L; r++, k--) {
						check[k] = true; 	// 경사면을 설치
					}
				}
			}
			cnt++;
		}
		
		// 열 확인
		Nextcol: for (int j = 0; j < N; j++) {
			boolean[] check = new boolean[N];
			for (int i = 0; i < N - 1; i++) {
				int val = map[i][j] - map[i + 1][j];
				if(Math.abs(val) > 1) continue Nextcol; 
				if(val == 0) continue;
				
				if(val == 1) {
					if (L == 1) {
						if(check[i + 1]) continue Nextcol;
						check[i + 1] = true;
						continue;
					}
					
					for(int k = i + 1, r = 0; r < L - 1; r++, k++)
						if(k + 1 >= N || map[k][j] != map[k + 1][j] || check[k] || check[k + 1]) continue Nextcol;
					
					for(int k = i + 1, r = 0; r < L; r++, k++) {
						check[k] = true;
					}
				} else if (val == -1) {
					if(L == 1) {
						if(check[i]) continue Nextcol;
						check[i] = true;
						continue;
					}
					
					for(int k = i, r = 0; r < L - 1; r++, k--)
						if(k - 1 < 0 || map[k][j] != map[k - 1][j] || check[k] || check[k - 1]) continue Nextcol;
					
					for(int k = i, r = 0; r < L; r++, k--) {
						check[k] = true;
					}
				}
			}
			cnt++;
		}
		
		System.out.println(cnt);
		
		br.close();
	}
}
