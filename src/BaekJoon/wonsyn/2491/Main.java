package a0214_bj_2491_수열;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2491.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] arr = new int[N];
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 1;
		
		for (int i = 0; i < N - 1; i++) {
			if (arr[i] <= arr[i + 1]) {
				cnt++;
				max = Math.max(max, cnt);
			}
			else cnt = 1;
		}
		
		cnt = 1;
		for (int i = 0; i < N - 1; i++) {
			if(arr[i] >= arr[i + 1]) {
				cnt++;
				max = Math.max(max, cnt);
			}
			else cnt = 1;
		}
		max = Math.max(max, cnt);
		
		System.out.println(max);
		
		br.close();
	}
}
