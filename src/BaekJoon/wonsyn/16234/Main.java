package a0307_bj_16234_인구이동;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] A;
	static boolean[][] v;
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 상 우 하 좌
	static int N, L, R, sum;
	static boolean isPossible;
	static ArrayList<int[]> list;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		while(true) {
			v = new boolean[N][N];
			isPossible = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!v[i][j]) {
						list = new ArrayList<>();
						sum = 0;
	
						dfs(i, j);
						int val = sum / list.size();
						for (int[] a : list) {
							A[a[0]][a[1]] = val;
						}
					}
				}
			}
			if(!isPossible)
				break;
			else count++;
		}
		
		System.out.println(count);

		br.close();
	}

	static void dfs(int x, int y) {
		list.add(new int[] { x, y });
		sum += A[x][y];
		v[x][y] = true;

		for (int d = 0; d < 4; d++) {
			int nx = x + dir[d][0];
			int ny = y + dir[d][1];
			if (0 <= nx && nx < N && 0 <= ny && ny < N && !v[nx][ny] 
					&& Math.abs(A[x][y] - A[nx][ny]) <= R
					&& Math.abs(A[x][y] - A[nx][ny]) >= L) {
				isPossible = true;
				dfs(nx, ny);
			}
		}
	}
}
