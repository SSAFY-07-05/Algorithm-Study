package week_2;

import java.io.*;
import java.util.*;

public class Main_bj_2564_경비원_대전_5반_윤석찬 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_2564.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int x = Integer.parseInt(st.nextToken()); // 가로의 길이
		int y = Integer.parseInt(st.nextToken()); // 세로의 길이

		int N = Integer.parseInt(br.readLine()); // 상점의 개수
		int map[] = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			int location = Integer.parseInt(st1.nextToken());
			int num = Integer.parseInt(st1.nextToken());
			
			if (location == 1) {
				map[i] = num;
			} else if (location == 2) {
				map[i] = (x*2+y)-num;
			} else if (location == 3) {
				map[i] = (x*2+2*y)-num;
			} else if (location == 4) {
				map[i] = x+num;
			}
		}
		
		int sum = x*2 + y*2;
		int res = 0;
		
		for (int i = 0; i < N; i++) {
			int temp1 = Math.abs(map[N]-map[i]);
			int temp2 = 0;
			if(map[N]>map[i]) temp2 = Math.abs(sum-map[N]+map[i]);
			else temp2 = Math.abs(sum-map[i]+map[N]);
			res += Math.min(temp1, temp2);
		}

		System.out.println(res);
		
		
	}
}
