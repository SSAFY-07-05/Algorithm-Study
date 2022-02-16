package a0215_bj_2559_수열;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2559.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] numbers = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());
		
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < N - K + 1; i++) {
			int sum = 0;
			for (int j = i; j < i + K; j++) {
				sum += numbers[j];
			}
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
		
		br.close();
	}
}
