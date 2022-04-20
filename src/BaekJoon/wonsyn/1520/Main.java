package a0420_bj_1520_내리막길;

import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[][] map, dp, del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1520.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		dp = new int[M][N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		int result = dfs(0, 0);
		System.out.print(result);
		
		br.close();
	}
	
	static int dfs(int x, int y) {
		if(x == M - 1 && y == N - 1) return 1;
		
		if(dp[x][y] != -1) return dp[x][y];
		else {
			dp[x][y] = 0;
			for(int d = 0; d < 4; d++) {
				int nx = x + del[d][0];
				int ny = y + del[d][1];
				
				if(0 <= nx && nx < M && 0 <= ny && ny < N && map[x][y] > map[nx][ny])
					dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
}
