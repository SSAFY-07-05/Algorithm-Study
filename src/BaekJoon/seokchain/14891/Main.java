package week_5;


import java.io.*;
import java.util.*;

public class Main{

	static int gear[][];

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		gear = new int[4][8];
		for (int i = 0; i < 4; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = s.charAt(j) - '0';
			}
		}

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken()); // 1: 시계, -1: 반시계
			
			boolean gearCheck[] = new boolean[3];
			int check[] = new int[5];

			check[num] = direction;

			gearCheck[0] = (gear[0][2] == gear[1][6]); // 1번 2번 톱니바퀴 비교
			gearCheck[1] = (gear[1][2] == gear[2][6]); // 2번 3번 톱니바퀴 비교
			gearCheck[2] = (gear[2][2] == gear[3][6]); // 3번 4번 톱니바퀴 비교

			if (num == 1) {  						/* 1번 톱니바퀴 */
				for (int k = 0; k < 3; k++) {
					if(!gearCheck[k]) {
						direction = direction*-1;
						num ++;
						check[num] = direction;
					} else break;
				}
				
			}else if (num == 2) { 					/* 2번 톱니바퀴 */
				
				if(!gearCheck[0]) check[num-1] = direction*-1; // 왼쪽
				
				for (int k = 1; k <= 2; k++) { 		// 오른쪽
					if(!gearCheck[k]) {
						direction = direction *-1;
						num++;
						check[num] = direction;
					} else break;
				}
				
			}else if (num == 3) { 					/* 3번 톱니바퀴 */

				if(!gearCheck[2]) check[num+1] = direction*-1; // 오른쪽
				
				for (int k = 1; k >= 0; k--) { // 왼쪽
					if(!gearCheck[k]) {
						direction = direction *-1;
						num--;
						check[num] = direction;
					} else break;
				}
				
				
			}else { 								/* 4번 톱니바퀴 */
				for (int k = 2; k >= 0; k--) {
					if(!gearCheck[k]) {
						direction = direction*-1;
						num--;
						check[num] = direction;
					} else break;
				}
				
			}
			
			// 톱니 회전
			for (int j = 1; j <= 4; j++) {
				if(check[j]==1) {
					right(gear, j-1);
				}else if(check[j]==-1) {
					left(gear, j-1);
				}
			}
		}
		
		int sum = 0;
		
		for (int i = 0 ; i < 4; i++ ) {
			if(gear[i][0] == 1) {
				sum += Math.pow(2, i);
			}
		}
		System.out.println(sum);

	}

	static void left(int arr[][], int N) {

		int temp = gear[N][0];
		for (int i = 0; i < 7; i++) {
			gear[N][i] = gear[N][i + 1];
		}
		gear[N][7] = temp;
	}

	static void right(int arr[][], int N) {

		int temp = gear[N][7];
		for (int i = 6; i >= 0; i--) {
			gear[N][i + 1] = gear[N][i];
		}
		gear[N][0] = temp;
	}
}

