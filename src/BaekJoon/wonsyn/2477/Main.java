package a0210_bj_2477_참외밭;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2477.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());	// 단위
		
		int[] length = new int[6];	// 값을 담을 배열
		int bx = 0, by = 0, sx = 0, sy = 0;	// 계산에 필요한 변수
		
		for (int i = 0; i < 6; i++) {	// 값 저장
			st = new StringTokenizer(br.readLine(), " ");
			st.nextToken();	// 버림
			length[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < 6; i++) {	// 큰 사각형의 가로세로(bx, by), 작은 사각형의 가로세로(sx, sy)를 구함
			int l = length[i];
			if(l == length[(i + 2) % 6] + length[(i + 4) % 6]) {
				if (i % 2 == 1) {
					bx = l;
					sx = Math.abs(length[(i + 1) % 6] - length[(i + 5) % 6]);
				} else {
					by = l;
					sy = Math.abs(length[(i + 1) % 6] - length[(i + 5) % 6]);
				}
			}
		}
//		System.out.println("bx : " + bx + ", by : " + by + ", sx : " + sx + ", sy : " + sy);
		if(bx != 0 && by != 0 && sx != 0 && sy != 0)
			System.out.println(((bx * by) - (sx * sy)) * n);
		
		br.close();
	}
}
