package a0318_bj_14888_연산자끼워넣기;

import java.io.*;
import java.util.*;

public class Main {
	static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] numbers, operator;
	static char[] form;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14888.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N];
		operator = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++)
			operator[i] = Integer.parseInt(st.nextToken());
		
		calc(numbers[0], 0);
		
		System.out.println(max + "\n" + min);
		br.close();
	}
	
	static void calc(int num, int cnt) {
		if(cnt == N - 1) {
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		
		for(int i = 0; i < 4; i++) {
			if(operator[i] != 0) {
				operator[i]--;
				
				if(i == 0) calc(num + numbers[cnt + 1], cnt + 1);
				else if(i == 1) calc(num - numbers[cnt + 1], cnt + 1);
				else if(i == 2) calc(num * numbers[cnt + 1], cnt + 1);
				else calc(num / numbers[cnt + 1], cnt + 1);
				
				operator[i]++;
			}
		}
	}
}
