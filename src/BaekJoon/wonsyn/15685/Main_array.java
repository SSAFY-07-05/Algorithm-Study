package a0401_bj_15685_드래곤커브;

import java.io.*;
import java.util.*;

public class Main_array {
	static int[][] dir = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};  // 우 상 좌 하
	static boolean[][] map = new boolean[101][101];
	static int N;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_15685.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int[] list = getDir(direction, g);
			
			map[x][y] = true;
			for(int d = 0; d < list.length; d++) {
				x = x + dir[list[d]][0];
				y = y + dir[list[d]][1];
				map[x][y] = true;
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1])
					cnt++;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}
	
	static int[] getDir(int d, int gen) {
		int size = (int)Math.pow(2, gen);
		int[] list = new int[size];
		list[0] = d;
		int idx = 1;
		for(int g = 0; g < gen; g++) {
			for(int i = idx - 1; i >= 0; i--) {
				list[idx] = (list[i] + 1) % 4;
				idx++;
			}
		}
		return list;
	}
}
