package week_2;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 온도를 측정한 날짜 수
		int K = Integer.parseInt(st.nextToken()); // 연속적인 날짜의 수

		int arr[] = new int[N]; // 온도를 담을 배열

		StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st1.nextToken());
		}

		int max = Integer.MIN_VALUE;

		if (N == K) {
			max = 0;
			for (int i : arr) {
				max += i;
			}
		}

		else {
			for (int i = 0; i < N - K + 1; i++) {
				int sum = 0;
				for (int j = i; j < i + K; j++) {
					sum += arr[j];
				}
				max = Math.max(sum, max);
			}
		}

		System.out.println(max);
	}
}
