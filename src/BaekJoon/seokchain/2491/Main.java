package week_2;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_2491.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 수열의 길이
		int num[] = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int upMax = 1;
		int downMax = 1;

		int upCnt = 1;
		int downCnt = 1;			
		
		for (int j = 0; j < N - 1; j++) {
			if (num[j] <= num[j + 1]) {
				upCnt++;
			} else
				upCnt = 1;
			if (upCnt > upMax)
				upMax = upCnt;
		}

		for (int j = 0; j < N - 1; j++) {
			if (num[j] >= num[j + 1]) {
				downCnt++;
			} else
				downCnt = 1;
			if (downCnt > downMax)
				downMax = downCnt;
		}

		int max = Math.max(upMax, downMax);

		System.out.println(max);
		br.close();
	}
}
