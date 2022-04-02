package a0401_bj_15685_드래곤커브;

import java.io.*;
import java.util.*;

public class Main {
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
			List<Integer> list = getDir(direction, g);
			
			map[x][y] = true;
			for(int d : list) {
				x = x + dir[d][0];
				y = y + dir[d][1];
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
	
	static List<Integer> getDir(int d, int gen) {
		List<Integer> list = new ArrayList<>();
		list.add(d);
		for(int g = 0; g < gen; g++) {
			for(int i = list.size() - 1; i >= 0; i--) {
				list.add((list.get(i) + 1) % 4);
			}
		}
		return list;
	}
}
