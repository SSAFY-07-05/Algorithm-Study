package a0412_bj_4963_섬의개수;

import java.io.*;
import java.util.*;

public class Main {
	static int W, H;
	static int[][] map, del = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	static boolean[][] v;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_4963.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			if(W == 0 && H == 0) break;
			
			map = new int[H][W];
			v = new boolean[H][W];
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(map[i][j] == 1 && !v[i][j]) {
						cnt++;
						dfs(i, j);
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
	
	static void dfs(int r, int c) {
		v[r][c] = true;
		
		for(int d = 0; d < 8; d++) {
			int nr = r + del[d][0];
			int nc = c + del[d][1];
			
			if(0 <= nr && nr < H && 0 <= nc && nc < W && map[nr][nc] == 1 && !v[nr][nc])
				dfs(nr, nc);
		}
	}
}
