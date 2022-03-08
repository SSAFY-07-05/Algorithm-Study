import java.io.*;
import java.util.*;

public class Main {

	static int map[][];
	static int v[][];
	static int N, L, R;
	static int unionCnt ;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());

		L = Integer.parseInt(st.nextToken()); // 최소 인구차이
		R = Integer.parseInt(st.nextToken()); // 최대 인구차이

		map = new int[N][N];
		v = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int dayCnt = 0;

		while (true) {
			boolean flag = true;
			int zeroCnt = 0;
			unionCnt = 1;
			v = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (v[i][j] == 0) {
						dfs(i, j);
						unionCnt++;
						flag = false;
						zeroCnt++;
					}
				}
			}
			
			if (flag || zeroCnt == N*N) break;
			dayCnt++;
			
			int[] unionsSum = new int[unionCnt];
            int[] unionsNum = new int[unionCnt];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j] != 0) {
                    unionsSum[v[i][j]] += map[i][j];
                    unionsNum[v[i][j]]++;
                    }
                }
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (v[i][j] != 0) map[i][j] = unionsSum[v[i][j]] / unionsNum[v[i][j]];
                }
            }

            
        }
		System.out.println(dayCnt);
	
}

	static int di[] = { -1, 0, +1, 0 }; // 상, 우, 하 좌
	static int dj[] = { 0, +1, 0, -1 };

	static void dfs(int i, int j) {

		if (i < 0 || j < 0 || i >= N || j >= N)
			return;

		for (int d = 0; d < 4; d++) {
			int y = i + di[d];
			int x = j + dj[d];

			if (y < N && 0 <= y && x < N && 0 <= x) {

				int personA = map[i][j]; // 현재 나라 인구수
				int personB = map[y][x]; // 근접 나라 인구수
				int res = Math.abs(personA - personB); // 근접 나라와 인구수 차이

				if (L <= res && res <= R) {
					v[i][j] = unionCnt;
					if (v[y][x] == 0)
						dfs(y, x);
				}
			}
		}
	}
}
