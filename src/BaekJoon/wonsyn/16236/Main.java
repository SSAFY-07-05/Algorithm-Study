package a0422_bj_16236_아기상어;

import java.io.*;
import java.util.*;

public class Main {
	static class Shark {
		int r, c, size, cnt;
		
		Shark(int r, int c, int size, int cnt) {
			this.r = r; this.c = c; this.size = size; this.cnt = cnt;
		}
		
		void upCnt() {
			if(++cnt == size) {
				size++; cnt = 0;
			}
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int r, c, dist;

		Fish(int r, int c, int dist) {
			this.r = r; this.c = c; this.dist = dist;
		}
		
		@Override
		public int compareTo(Fish o) {
			int distDiff = this.dist - o.dist;
			if(distDiff == 0) {
				int row = this.r - o.r;
				if(row == 0) return this.c - o.c; 
				return row;
			}
			return distDiff;
		}
	}
	
	static int N, sec;
	static int[][] map, del = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static Shark shark;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_16236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		sec = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); 
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Shark(i, j, 2, 0);
					map[i][j] = 0;
				}
			}
		}
		
		while(bfs());
		System.out.print(sec);
		br.close();
	}
	
	static boolean bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		PriorityQueue<Fish> fq = new PriorityQueue<>();
		int[][] dist = new int[N][N];
		q.offer(new int[] {shark.r, shark.c});
		dist[shark.r][shark.c] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d = 0; d < 4; d++) {
				int nr = cur[0] + del[d][0];
				int nc = cur[1] + del[d][1];
				if(0 <= nr && nr < N && 0 <= nc && nc < N && dist[nr][nc] == 0 && map[nr][nc] <= shark.size) {
					dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
					q.offer(new int[] {nr, nc});
					if(map[nr][nc] != 0 && map[nr][nc] != shark.size)
						fq.offer(new Fish(nr, nc, dist[nr][nc]));
				}
			}
		}
		
		if(fq.isEmpty())
			return false;
		
		Fish fish = fq.poll();
		sec += fish.dist;
		shark.upCnt();
		map[fish.r][fish.c] = 0;
		shark.r = fish.r; shark.c = fish.c;
		return true;
	}
	
	static int getDistance(int r, int c) {
		int distance = 0;
		distance += Math.abs(shark.r - r);
		distance += Math.abs(shark.c - c);
		return distance;
	}
}
