package week_1;

import java.io.*;
import java.util.*;

public class Main{

	static int dice[][];
	static int N, sum;

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_2116.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 주사위 개수

		dice = new int[N][6]; // 주사위를 2차원 배열에 저장

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 6; j++) {
				dice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0;
		for (int i = 1; i <= 6; i++) { // 첫번째 주사위가 놓이는 경우의 수 6가지 반복
			sum = 0;
			minus(0, i);
			max = Math.max(max, sum);
		}
		System.out.println(max);
		br.close();
	}

	static int minus(int num, int top) {
		
		if (num == N) { return sum; } // 주사위 N개를 다 쌓으면 값 리턴
		
		int temp[] = new int[6];

		for (int i = 0; i < 6; i++) {
			temp[i] = dice[num][i];
		}
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] == top) { // 이전 주사위에 윗면에 해당하는 수를 찾아준다.
					if (i == 0) { 
						temp[i] = 0;
						top = temp[i + 5]; // 반대편 숫자를 top으로 만들어주고
						temp[i + 5] = 0; 
						Arrays.sort(temp); //정렬을 통해 옆면에서 가장 큰 수를 구해 더해준다.
						sum += temp[5];
					} else if (i == 1) {
						temp[i] = 0;
						top = temp[i + 2];
						temp[i + 2] = 0;
						Arrays.sort(temp);
						sum += temp[5];
					} else if (i == 2) {
						temp[i] = 0;
						top = temp[i + 2];
						temp[i + 2] = 0;
						Arrays.sort(temp);
						sum += temp[5];
					} else if (i == 3) {
						temp[i] = 0;
						top = temp[i - 2];
						temp[i - 2] = 0;
						Arrays.sort(temp);
						sum += temp[5];
					} else if (i == 4) {
						temp[i] = 0;
						top = temp[i - 2];
						temp[i - 2] = 0;
						Arrays.sort(temp);
						sum += temp[5];
					} else {
						temp[i] = 0;
						top = temp[i - 5];
						temp[i - 5] = 0;
						Arrays.sort(temp);
						sum += temp[5];
					}
				}
			}
		return minus(num + 1, top); // 주사위 한개를 쌓고 다음 주사위로 이동
	}
}
