package a0302_bj_13458_시험감독;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_13458.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] room = new int[N];
		long cnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			room[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			room[i] -= B;
			cnt++;
			if(room[i] > 0) {
				if(room[i] % C == 0) cnt = cnt + (room[i] / C);
				else cnt = cnt + (room[i] / C) + 1;
			}
		}
		
		System.out.println(cnt);
		br.close();
	}
}
