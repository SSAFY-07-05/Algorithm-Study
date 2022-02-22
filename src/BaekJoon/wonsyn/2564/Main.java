package a0217_bj_2564_경비원;

import java.io.*;
import java.util.*; 

public class Main {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2564.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		
		int[][] store = new int[N][2];
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 2; i++)
				store[n][i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int curD = Integer.parseInt(st.nextToken());
		int curL = Integer.parseInt(st.nextToken());
		int sum = 0;

		for(int n = 0; n < N; n++) {
			int dir = store[n][0];
			int length = store[n][1];
			
			if(dir == 1) {	// 상점이 북쪽
				if(curD == 1) {	// 동그니가 북쪽
					sum += Math.abs(curL - length);
				} else if (curD == 2) { // 동그니가 남쪽
					int way1 = curL + length;
					int way2 = 2 * W - curL - length; 
					sum += Math.min(way1, way2) + H;	
				} else if (curD == 3) {	// 동그니가 서쪽
					sum += curL + length;
				} else { // 동그니가 동쪽
					sum += curL + W - length;
				}
			} else if (dir == 2) {	// 상점이 남쪽
				if(curD == 1) { // 동그니가 북쪽
					int way1 = curL + length;
					int way2 = 2 * W - curL - length; 
					sum += Math.min(way1, way2) + H;
				} else if (curD == 2) {	// 동그니가 남쪽
					sum += Math.abs(curL - length);
				} else if (curD == 3) {	// 동그니가 서쪽
					sum += H - curL + length;
				} else { // 동그니가 동쪽
					sum += H - curL + W - length;
				}
			} else if (dir == 3) {	// 상점이 서쪽
				if(curD == 1) {	// 동그니가 북쪽
					sum += curL + length;
				} else if (curD == 2) {	// 동그니가 남쪽
					sum += H - length + curL;
				} else if (curD == 3) {	// 동그니가 서쪽
					sum += Math.abs(curL - length);
				} else { // 동그니가 동쪽
					int way1 = curL + length;
					int way2 = 2 * H - curL - length;
					sum += Math.min(way1, way2) + W;
				}
			} else {	// 상점이 동쪽
				if(curD == 1) {	// 동그니가 북쪽
					sum += W - curL + length;
				} else if (curD == 2) {	// 동그니가 남쪽
					sum += W - curL + H - length;
				} else if (curD == 3) { // 동그니가 서쪽
					int way1 = curL + length;
					int way2 = 2 * H - curL - length;
					sum += Math.min(way1, way2) + W;
				} else { // 동그니가 동쪽
					sum += Math.abs(curL - length);
				}
			}
		}
		System.out.println(sum);
		br.close();
	}
}
