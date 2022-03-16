package a0308_bj_14981_톱니바퀴;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] wheel;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_14981.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new int[4][8];	// 12시부터 시작
		// left : 6, right : 2
		
		for(int i = 0; i < 4; i++) {
			String str = br.readLine();
			for(int j = 0; j < 8; j++)
				wheel[i][j] = str.charAt(j) - '0';	// N : 0, S : 1
		}
			
		int K = Integer.parseInt(br.readLine());
		int[][] rotate = new int[K][2];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			rotate[i][0] = Integer.parseInt(st.nextToken()) - 1;	// 0 1 2 3
			rotate[i][1] = Integer.parseInt(st.nextToken());	// 시계 : 1, 반시계 : 2
		}
		
		for (int r = 0; r < K; r++) {
			if(rotate[r][1] == 1) {
				// 시계방향 회전
				clock(rotate[r][0], new boolean[4]);
			}
			else if(rotate[r][1] == -1) {
				// 반시계방향 회전
				declock(rotate[r][0], new boolean[4]);
			}
		}
		
		int score = 0;
		for(int i = 0; i < 4; i++) {
			if(wheel[i][0] == 1) {
				score = score + (int)Math.pow(2, i);
			}
		}
		
		System.out.println(score);
		br.close();
	}
	
	static void clock(int startwheel, boolean[] v) {
		v[startwheel] = true;
		int left = wheel[startwheel][6];
		int right = wheel[startwheel][2];
		int leftOpp = -1, rightOpp = -1;
		if(startwheel != 0)
			leftOpp = wheel[startwheel - 1][2];
		if(startwheel != 3)
			rightOpp = wheel[startwheel + 1][6];
		
		int temp = wheel[startwheel][7];
		for(int i = 6; 0 <= i; i--) {
			wheel[startwheel][i + 1] = wheel[startwheel][i];
		}
		wheel[startwheel][0] = temp;
		
		if(leftOpp != -1 && left != leftOpp && !v[startwheel - 1]) declock(startwheel - 1, v);
		if(rightOpp != -1 && right != rightOpp && !v[startwheel + 1]) declock(startwheel + 1, v);
	}
	
	static void declock(int startwheel, boolean[] v) {
		v[startwheel] = true;
		int left = wheel[startwheel][6];
		int right = wheel[startwheel][2];
		int leftOpp = -1, rightOpp = -1;
		if(startwheel != 0)
			leftOpp = wheel[startwheel - 1][2];
		if(startwheel != 3)
			rightOpp = wheel[startwheel + 1][6];
		
		int temp = wheel[startwheel][0];
		for(int i = 0; i <= 6; i++) {
			wheel[startwheel][i] = wheel[startwheel][i + 1];
		}
		wheel[startwheel][7] = temp;
		
		if(leftOpp != -1 && left != leftOpp && !v[startwheel - 1]) clock(startwheel - 1, v);
		if(rightOpp != -1 && right != rightOpp && !v[startwheel + 1]) clock(startwheel + 1, v);
	}
}
