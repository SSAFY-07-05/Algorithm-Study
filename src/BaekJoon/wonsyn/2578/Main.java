package a0221_bj_2578_빙고;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] bingo;
	static int cnt = 0;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2578.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		bingo = new int[5][5];  // 빙고판 생성 5 x 5
		
		// 빙고판 입력
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 5; j++)
				bingo[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 4번 부를 동안은 빙고 성립 x, 5번째 한번 체크
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int k = 0; k < 5; k++) {
			int n = Integer.parseInt(st.nextToken());
			setNext: for (int i = 0; i < 5; i++) {  // setNext: 다음 수로 넘어감
				for (int j = 0; j < 5; j++) {
					if (bingo[i][j] == n) {
						bingo[i][j] = 0;
						if (k == 4) {
							bingoCheck(i, j);
						}
						break setNext;
					}
				}
			}
		}
		
		int callCnt = 5; // 사회자가 부른 횟수
		
		// 한번 부를 때마다 빙고가 성립하는지 확인
		finish: for (int k = 0; k < 4; k++) {  // finish: 빙고 성립
			st = new StringTokenizer(br.readLine(), " ");
			for (int l = 0; l < 5; l++) {
				int n = Integer.parseInt(st.nextToken());
				next : for (int i = 0; i < 5; i++) {  // next: 다음 수 확인
					for (int j = 0; j < 5; j++) {
						if(bingo[i][j] == n) {
							callCnt++;
							bingo[i][j] = 0;
							bingoCheck(i, j);
							if(cnt >= 3) break finish;
							break next;
						}
					}
				}
			}
		}
		
		System.out.println(callCnt);
		br.close();
	}
	
	static void bingoCheck(int x, int y) {
		// 가로 체크
		int zero = 0;
		for (int i = 0; i < 5; i++) {
			if (bingo[x][i] != 0) break;
			zero++;
		}
		if(zero == 5) cnt++;
		
		// 세로 체크
		zero = 0;
		for(int i = 0; i < 5; i++) {
			if(bingo[i][y] != 0) break;
			zero++;
		}
		if(zero == 5) cnt++;
		
		// 대각선 체크
		zero = 0;
		if(x == y) {
			for(int i = 0, j = 0; i < 5; i++, j++) {
				if(bingo[i][j] != 0) break;
				zero++;
			}
		}
		if(zero == 5) cnt++;
		
		zero = 0;
		if (4 - x == y) {
			for(int i = 4, j = 0; j < 5; i--, j++) {
				if(bingo[i][j] != 0) break;
				zero++;
			}
		}
		if(zero == 5) cnt++;
	}
}
