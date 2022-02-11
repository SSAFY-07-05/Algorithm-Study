package week_1;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		// 큰 사각형 넓이 - 작은 사각형 넓이 * 넓이당 참외 수확 개수

		System.setIn(new FileInputStream("res/input_2477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 넓이당 참외 수확 개수

		int direction[] = new int[8]; // 앞 뒤로 배열 크기를 하나씩 들려준다.
		int width[] = new int[8]; // 앞 뒤로 배열 크기를 하나씩 들려준다.
		int cnt[] = new int[5]; // 동,서,남,북 카운트 배열

		for (int i = 1; i <= 6; i++) { // 방향과 너비를 따로 배열에 저장
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			direction[i] = Integer.parseInt(st.nextToken());
			cnt[direction[i]]++; // 입력된 방향 카운트
			width[i] = Integer.parseInt(st.nextToken());
		}

		int x = 0; // 큰 사각형 방향 x
		int y = 0; // 큰 사각형 방향 y
		int flag = 0;
		int maxWidth = 1;

		for (int i = 0; i < cnt.length; i++) { // 큰 사각형 구하기
			if (cnt[i] == 1) {
				for (int j = 0; j < direction.length; j++) {
					if (direction[j] == i) {
						maxWidth *= width[j];
						x = direction[j];
						flag++;
						if (flag == 1) y = direction[j];
					}
				}
			}
		}
		// 값을 비교하기 위해 맨 앞과 뒤를 이어 준다.
		direction[0] = direction[6];
		direction[7] = direction[1];

		int minusWidth = 1;
		for (int i = 1; i <= 6; i++) { // 작은 사각형 넓이 구하기
			if (direction[i] != x && direction[i] != y) {
				if (direction[i + 1] != x && direction[i + 1] != y && direction[i - 1] != x && direction[i - 1] != y)
					minusWidth *= width[i];
			}
		}
		
		int field = maxWidth - minusWidth;
		
		System.out.println(field * N);
	}
}
