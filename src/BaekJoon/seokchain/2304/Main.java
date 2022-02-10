package week_1;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_2304.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		ArrayDeque<int[]> location = new ArrayDeque<>(); // 기둥에 좌표를 담을 배열
		ArrayDeque<int[]> temp = new ArrayDeque<>(); // 기둥에 좌표를 정렬 및 y의 최댓값을 찾기 위한 임시 배열

		next: for (int i = 0; i < N; i++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int[] tempArray = { x, y };

			if (i == 0) { // 최초로 들어오는 기둥 좌표 입력
				location.offerLast(tempArray);
				continue next;
			}

			if (location.peekLast()[0] > x) { // 다음에 들어온 기둥에 x 좌표가 더 작은 경우
				while (!location.isEmpty() && location.peekLast()[0] > x) { // 자신 보다 작은 값을 찾을때 까지 반복
					temp.offerFirst(location.pollLast()); // 자신보다 큰 값은 temp에 저장
				}
				location.offerLast(tempArray); // 자신보다 작은 값을 발견하면 현재 값을 넣어준다.
				while (!temp.isEmpty()) { // temp에 저장한 값을 다시 location에 뒤에서부터 저장해준다.
					location.offerLast(temp.pollFirst());
				}
				continue next;
			}

			if (location.peekLast()[0] < x) { // 다음에 들어온 기둥에 x 좌표가 더 큰 경우
				location.offerLast(tempArray); // 별도의 처리 없이 뒤쪽으로 넣어준다.
				continue next;
			}
		}
		// 정렬된 좌표 (2 4) (4 6) (5 3) (8 10) (11 4) (13 6) (15 8)

		int maxidx = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			if (location.peekFirst()[1] >= max) { // 최대값 Index 찾기
				max = location.peekFirst()[1];
				maxidx = i;
			}
			temp.offerLast(location.pollFirst());
		}

		while (!temp.isEmpty()) { 
			location.offerLast(temp.pollFirst());
		}

		int sum = 0; // 창고의 넓이를 저장할 변수

		int start_x = 0;
		int start_y = 0;

		if (maxidx != 0) { // 가장 큰 y를 가진 Index가 0이 아닌 경우

			start_x = location.peekFirst()[0];
			start_y = location.peekFirst()[1];

			location.pollFirst();
			// 좌표의 처음부터 넓이 계산
			for (int i = 1; i <= maxidx; i++) {
				if (start_y <= location.peekFirst()[1]) {
					sum += start_y * (location.peekFirst()[0] - start_x);
					start_x = location.peekFirst()[0];
					start_y = location.peekFirst()[1];
					if (i == maxidx) {
						sum += start_y;
						break;
					}
				}
				location.pollFirst();
			}
		} else { // 가장 큰 y를 가진 Index가 0인 경우
			sum += location.peekFirst()[1];
		}

		// 반대부터 다시 넓이를 계산하기 위해 출발점을 맨 끝에 좌표로 변경
		start_x = location.peekLast()[0];
		start_y = location.peekLast()[1];
		location.pollLast();

		// 좌표의 끝부터 역순으로 넓이 계산
		for (int i = 0; i < N - maxidx - 1; i++) {
			if (start_y <= location.peekLast()[1]) {
				sum += start_y * (start_x - location.peekLast()[0]);
				start_x = location.peekLast()[0];
				start_y = location.peekLast()[1];
			}
			location.pollLast();
		}
		System.out.println(sum);
		br.close();
	}
}
