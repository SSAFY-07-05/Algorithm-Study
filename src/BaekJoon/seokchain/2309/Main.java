package week_1;

// 조합 문제
// 9명의 난쟁이중 7마리 선택(중복X)
// 난쟁이 키의 합은 100
import java.io.*;
import java.util.*;

public class Main {

	static int N = 9; // 난쟁이 9명
	static int R = 7; // 난쟁이 7명
	static int[] input, numbers; // input : 모든 난쟁이 배열 , numbers : 난쟁이 선택 배열

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_2309.txt"));
		Scanner sc = new Scanner(System.in);

		input = new int[N];
		numbers = new int[R];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		combination(0, 0);
	}

	static void combination(int cnt, int start) {

		if (cnt == R) {
			// System.out.println(Arrays.toString(numbers));
			int sum = 0;
			for (int i = 0; i < R; i++) {
				sum += numbers[i];
			}
			if (sum == 100) {
				Arrays.sort(numbers);
				for (int i : numbers) {
					System.out.println(i);
				}
			}
			return;
		}

		for (int i = start; i < N; i++) {
			numbers[cnt] = input[i];
			combination(cnt + 1, i + 1);
		}
	}
}
