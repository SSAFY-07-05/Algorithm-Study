package week_4;

import java.io.*;
import java.util.*;

public class Main_bj_13458_시험감독_대전_5반_윤석찬 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_13458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int arrA[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long cnt = 0;
		for (int i = 0; i < arrA.length; i++) {
			long num = arrA[i];
			
			num = num-B;
			cnt++;
			
			while(num>0) {
				if(num>=C) {
					cnt+= num/C;
					num = num%C;
				}
				else {
					num = 0;
					cnt++;
				}
				
			}
		}

		System.out.println(cnt);
		
	}
}
