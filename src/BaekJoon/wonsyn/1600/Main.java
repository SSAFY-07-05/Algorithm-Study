package a0331_bj_1600_말이되고픈원숭이;

import java.io.*;
import java.util.*;

public class Main {
	static int K, W, H, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] v;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상 우 하 좌
	static int[][] hdir = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, 
							{1, 2}, {2, 1}, {2, -1}, {1, -2}}; 
	
	static class Monkey {
		int x, y, k, cnt;
		
		Monkey(int x, int y, int k, int cnt) {
			this.x=x; this.y=y; this.k=k; this.cnt=cnt;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1600.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W]; v = new boolean[H][W][K + 1];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
		
		System.out.println(min == Integer.MAX_VALUE? -1:min);
		br.close();
	}
	
	static void bfs() {
		Queue<Monkey> q = new ArrayDeque<>();
		v[0][0][0] = true;
		q.offer(new Monkey(0, 0, 0, 0));
		
		while(!q.isEmpty()) {
			Monkey m = q.poll();
			if(m.x == H - 1 && m.y == W - 1) {
				min = Math.min(m.cnt, min);
				return;
			}
			
			// 원숭이처럼 움직이는 경우 -> 4방
			for(int d = 0; d < 4; d++) {
				int nx = m.x + dir[d][0];
				int ny = m.y + dir[d][1];
				if(0 <= nx && nx < H && 0 <= ny && ny < W && map[nx][ny] != 1 && !v[nx][ny][m.k]) {
					v[nx][ny][m.k] = true;
					q.offer(new Monkey(nx, ny, m.k, m.cnt + 1));
				}
			}
			
			// 이미 말처럼 움직일 횟수를 모두 쓴 원숭이라면, 말처럼 움직이지 못함
			if(m.k == K) continue;
			
			// 말처럼 움직이는 경우 -> 8방
			for(int d = 0; d < 8; d++) {
				int nx = m.x + hdir[d][0];
				int ny = m.y + hdir[d][1];
				if(0 <= nx && nx < H && 0 <= ny && ny < W && map[nx][ny] != 1 && !v[nx][ny][m.k + 1]) {
					v[nx][ny][m.k + 1] = true;
					q.offer(new Monkey(nx, ny, m.k + 1, m.cnt + 1));
				}
			}
		}
	}
}
