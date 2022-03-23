package a0323_bj_20057_마법사상어와토네이도;

import java.io.*;
import java.util.*;

public class Main {
	static int N, curI, curJ;
	static int[][] map, dir = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};	// 좌 하 우 상
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_20057.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		curI = N / 2; curJ = N / 2;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int out = 0;
		int cnt = 0, d = 0, r = 1, rc = 0;
		while(!(curI == 0 && curJ == 0)) {
			int ni = curI + dir[d][0], nj = curJ + dir[d][1];
			int curval = map[ni][nj];
			int temp, ti, tj;
			
			/* 5 % */
			temp = curval * 5 / 100;
			ti = ni + 2*dir[d][0];
			tj = nj + 2*dir[d][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp; 
			else out += temp;
			map[ni][nj] -= temp;
			
			/* 10 % */
			temp = curval * 10 / 100;
			ti = ni + dir[d][0] + dir[(d+3)%4][0];
			tj = nj + dir[d][1] + dir[(d+3)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp;
			ti = ni + dir[d][0] + dir[(d+1)%4][0];
			tj = nj + dir[d][1] + dir[(d+1)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp;
			map[ni][nj] -= 2 * temp;
			
			/* 7 % */
			temp = curval * 7 / 100;
			ti = ni + dir[(d+3)%4][0];
			tj = nj + dir[(d+3)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp;
			ti = ni + dir[(d+1)%4][0];
			tj = nj + dir[(d+1)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N)map[ti][tj] += temp;
			else out += temp;
			map[ni][nj] -= 2 * temp;
			
			/* 2 % */
			temp = curval * 2 / 100;
			ti = ni + 2 * dir[(d+3)%4][0];
			tj = nj + 2 * dir[(d+3)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp; 
			ti = ni + 2 * dir[(d+1)%4][0];
			tj = nj + 2 * dir[(d+1)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp; 
			map[ni][nj] -= 2 * temp;
			
			
			/* 1 % */
			temp = curval * 1 / 100;
			ti = ni + dir[(d+2)%4][0] + dir[(d+3)%4][0];
			tj = nj + dir[(d+2)%4][1] + dir[(d+3)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp;
			ti = ni + dir[(d+2)%4][0] + dir[(d+1)%4][0];
			tj = nj + dir[(d+2)%4][1] + dir[(d+1)%4][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += temp;
			else out += temp;
			map[ni][nj] -= 2 * temp;
			
			/* a 지역 */
			ti = ni + dir[d][0]; tj = nj + dir[d][1];
			if(0 <= ti && ti < N && 0 <= tj && tj < N) map[ti][tj] += map[ni][nj];
			else out += map[ni][nj];
			
			map[ni][nj] = 0;
			curI = ni; curJ = nj;
			
			cnt++;
			rc++;
			if(rc == r) {
				d = (d + 1) % 4;
				rc = 0;
				if(cnt > r) {
					r++;
					cnt = 0;
				}
			}
		}
		
		System.out.println(out);
		br.close();
	}
}
