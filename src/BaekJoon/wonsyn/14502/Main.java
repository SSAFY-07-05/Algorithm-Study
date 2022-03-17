package a0317_bj_14502_연구소;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, max = Integer.MIN_VALUE;
	static int[][] map, dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static ArrayList<int[]> virus;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M]; 
		virus = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
				if (map[i][j] == 2) virus.add(new int[] {i, j});
			}
		}
		
		setWall(0);
		
		System.out.println(max);
		br.close();
	}
	
	static void setWall(int cnt) {
		if(cnt == 3) {
			bfs();
			return;
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					setWall(cnt + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	static void bfs() {
		int[][] copy = new int[N][];
		for (int i = 0; i < N; i++)
			copy[i] = Arrays.copyOf(map[i], map[i].length);
		
		Queue<int[]> v = new LinkedList<>();
		for(int[] item : virus) v.offer(item);
		
		while(!v.isEmpty()) {
			int x = v.peek()[0];
			int y = v.poll()[1];
					
			for(int d = 0; d < 4; d++) {
				int nx = x + dir[d][0];
				int ny = y + dir[d][1];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < M && copy[nx][ny] == 0) {
					copy[nx][ny] = 2;
					v.offer(new int[] {nx, ny});
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(copy[i][j] == 0) cnt++;
			}
		}
		
		max = Math.max(max, cnt);
	}
}
