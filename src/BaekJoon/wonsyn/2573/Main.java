package a0408_bj_2573_빙산;

import java.io.*;
import java.util.*;

public class Main {
	static class Melt {
		int r, c, h;
		
		Melt(int r, int c) {
			this.r = r; this.c = c;
		}
		
		void melt() {
			map[r][c] -= h;
			if(map[r][c] < 0) map[r][c] = 0;
		}
	}
	
	static int[][] map, del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
	static boolean[][] v;
	static int year, N, M;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2573.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		year = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Melt> list = new ArrayList<>();
		while (true) {
			list.clear();
			v = new boolean[N][M];
			
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && !v[i][j]) {
						dfs(i, j, list);
						cnt++;
					}
				}
			}
			
			if(cnt == 0) {
				year = 0;
				break;
			}
			if(cnt > 1) break;
			
			later(list);
			
			year++;
		}
		
		
		System.out.print(year);
		br.close();
	}
	
	static void dfs(int r, int c, ArrayList<Melt> list) {
		v[r][c] = true;
		list.add(new Melt(r, c));
		
		for(int d = 0; d < 4; d++) {
			int nr = r + del[d][0];
			int nc = c + del[d][1];
			if(0 <= nr && nr < N && 0 <= nc && nc < M && !v[nr][nc] && map[nr][nc] != 0) {
				dfs(nr, nc, list);
			}
		}
	}
	
	static void later(ArrayList<Melt> list) {
		for(Melt m : list) {
			int cnt = 0;
			for(int d = 0; d < 4; d++) {
				int r = m.r + del[d][0];
				int c = m.c + del[d][1];
				if(0 <= r && r < N && 0 <= c && c < M && map[r][c] == 0) {
					cnt++;
				}
			}
			m.h = cnt;
		}
		
		for(Melt m : list) {
			m.melt();
		}
	}
}
