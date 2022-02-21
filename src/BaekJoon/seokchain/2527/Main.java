package week_2;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_2527.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		


		for (int tc = 0; tc < 4; tc++) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());
	
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());
			

			// 공통부분 없음
			if(p1<x2 || p2 < x1 || q1<y2 ||  q2<y1 ) {
				sb.append('d');
			}
			// 점
			else if(x1==p2 && y1==q2 || p1 == x2 && q1==y2 || x1==p2 && q1 == y2 || p1 ==x2 && y1==q2) {
				sb.append('c');
			}
			// 선분
			else if(y1==q2 || y2 == q1|| p2==x1 ||x2==p1) {
				sb.append('b');
			}
			// 직사각형
			else sb.append('a');
				
			System.out.println(sb.toString());
			
		}
		br.close();
	}
}
