package a0428_bj_14500_테트로미노;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, max;
	static int[][] paper, del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14500.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		max = Integer.MIN_VALUE;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] v = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0, v);
				check(i, j);
			}
		}
		
		System.out.print(max);
		br.close();
	}
	
	static void dfs(int r, int c, int sum, int cnt, boolean[][] v) {
		v[r][c] = true;
		sum += paper[r][c];
		
		if(cnt == 3) {
			max = Math.max(max, sum);
			v[r][c] = false;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nr = r + del[d][0];
			int nc = c + del[d][1];
			if(0 <= nr && nr < N && 0 <= nc && nc < M && !v[nr][nc]) {
				dfs(nr, nc, sum, cnt + 1, v);
			}
		}
		v[r][c] = false;
	}
	
	static void check(int r, int c) {
		next: for(int d = 0; d < 4; d++) {
			int sum = paper[r][c];
			
			for(int i = 1; i < 3; i++) {
				int nr = r + del[d][0] * i;
				int nc = c + del[d][1] * i;
				if (nr < 0 || N <= nr || nc < 0 || M <= nc) continue next;
				sum += paper[nr][nc];
			}
			
			int newD = (d + 1) % 4;
			int nr = r + del[d][0] + del[newD][0];
			int nc = c + del[d][1] + del[newD][1];
			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				int nSum = sum + paper[nr][nc];
				max = Math.max(max, nSum);
			}
			newD = (newD + 2) % 4;
			nr = r + del[d][0] + del[newD][0];
			nc = c + del[d][1] + del[newD][1];
			if (0 <= nr && nr < N && 0 <= nc && nc < M) {
				int nSum = sum + paper[nr][nc];
				max = Math.max(max, nSum);
			}
		}
	}
}
