package a0324_bj_2583_영역구하기;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Main {
	static int M, N, K, cnt, area;
	static boolean[][] paper;
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};	// 상 우 하 좌
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2583.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		paper = new boolean[M][N];
		
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int i = y1; i < y2; i++) {
				for(int j = x1; j < x2; j++) {
					paper[i][j] = true;
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!paper[i][j]) {
					area = 0;
					dfs(i, j);
					cnt++;
					list.add(area);
				}
			}
		}
		Collections.sort(list);
		sb.append(cnt).append("\n");
		for(int n : list) {
			sb.append(n).append(" ");
		}
		System.out.println(sb);
		br.close();
	}
	
	static void dfs(int x, int y) {
		paper[x][y] = true;
		area++;
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if(0 <= nx && nx < M && 0 <= ny && ny < N && !paper[nx][ny]) {
				
				dfs(nx, ny);
			}
		}
	}
}
