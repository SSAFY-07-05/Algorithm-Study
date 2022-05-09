package a0502_bj_17142_연구소3;

import java.io.*;
import java.util.*;

public class Main {
	static class Virus {
		int r, c;
//		boolean avail;
		Virus(int r, int c){
			this.r = r; this.c = c; 
//			this.avail = avail;
		}
	}
	
	static int N, M, blank, min;
	static int[][] lab, del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static ArrayList<Virus> viruses;
	static Virus[] selected;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_17142.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		viruses = new ArrayList<>();
		blank = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		
		lab = new int[N][N];
		selected = new Virus[M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 2) viruses.add(new Virus(i, j));
				else if(lab[i][j] == 0) blank++;
			}
		}
		
		if (blank == 0) {
			System.out.print(0);
			return;
		}
		
		comb(0, 0);
		System.out.print(min==Integer.MAX_VALUE? -1:min);
		br.close();
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			bfs();
			return;
		}
		
		for(int i = idx; i < viruses.size(); i++) {
			selected[cnt] = viruses.get(i);
			comb(i + 1, cnt + 1);
		}
	}
	
	static void bfs() {
		boolean[][] v = new boolean[N][N];
		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copy[i][j] =- lab[i][j]; 
			}
		}
			
		Queue<Virus> q = new ArrayDeque<>();
		for(int i = 0; i < M; i++) {
			q.offer(selected[i]);
			copy[selected[i].r][selected[i].c] = 0;
			v[selected[i].r][selected[i].c] = true;
		}
		
		int t = 1;
		int cnt = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Virus virus = q.poll();
				
				for(int d = 0; d < 4; d++) {
					int nr = virus.r + del[d][0];
					int nc = virus.c + del[d][1];
					if(0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc]) {
						if(copy[nr][nc] == 0) {
							v[nr][nc] = true;
							copy[nr][nc] = t;
							q.offer(new Virus(nr, nc));
							cnt++;
							if(cnt == blank) {
								min = Math.min(min, t);
								return;
							}
						} else if(copy[nr][nc] == -2) {
							v[nr][nc] = true;
							copy[nr][nc] = t;
							q.offer(new Virus(nr, nc));
						}
					}
				}
			}
			t++;
		}
	}
}
