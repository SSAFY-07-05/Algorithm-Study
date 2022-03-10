package a0310_bj_14503_로봇청소기;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북, 동, 남, 서
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14503.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int[] pos = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		int direction = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int clean = 0;
		exit: while(true) {
			if (arr[pos[0]][pos[1]] == 0) {	// 청소하지 않은 곳인 경우 청소
				arr[pos[0]][pos[1]] = 2;
				clean++;
			}

			for (int d = 0; d < 4; d++) {
				direction = (direction + 3) % 4;	// 왼쪽 방향으로
				
				int nx = pos[0] + dir[direction][0];
				int ny = pos[1] + dir[direction][1];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] == 0) {
					pos[0] = nx; pos[1] = ny;
					break;
				}
				
				if (d == 3) {	// 네 방향을 모두 탐색한 후, 청소할 곳이 없다면
					// 현재 방향에서 뒤쪽 방향 탐색
					nx = pos[0] + dir[(direction + 2) % 4][0];
					ny = pos[1] + dir[(direction + 2) % 4][1];
					if (0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] != 1) {	// 후진이 가능하면
						pos[0] = nx; pos[1] = ny;
					} else break exit;	// 후진이 불가능하면 while 루프 탈출
				}
			}
		}
		
		System.out.println(clean);
		br.close();
	}
}
