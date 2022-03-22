package week_6;

import java.io.*;
import java.util.*;

public class Main{

	static int N, L, res;
	static int[][] map;
	static boolean[][] runWay;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_14890.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		runWay = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			boolean isPossible = true;
			for(int j = 0; j < N - 1; j++) {
				if(Math.abs(map[i][j] - map[i][j + 1]) == 1) {
					if(map[i][j] > map[i][j + 1]) {
						if(j + L > N - 1) {
							isPossible = false;
							continue;
						}
						for(int k = 1; k <= L; k++) {
							if(runWay[i][j + k] || map[i][j + k] + 1 != map[i][j]) {
								isPossible = false;
								break;
							}
						}
						if(isPossible) {
							for(int k = 1; k <= L; k++) {
								runWay[i][j + k] = true;
							}
						}
					}else {	
						if(j - L + 1 < 0) {
							isPossible = false;
							continue;
						}
						for(int k = 0; k < L; k++) {
							if(runWay[i][j - k] || map[i][j - k] + 1 != map[i][j + 1]) {
								isPossible = false;
								break;
							}
						}
						if(isPossible) {
							for(int k = 0; k < L; k++) {
								runWay[i][j - k] = true;
							}
						}
					}
				}else if(Math.abs(map[i][j] - map[i][j + 1]) > 1) {
					isPossible = false;
				}
			}
			if(isPossible) {
				res++;
			}
		}
		
		runWay = new boolean[N][N];
		
		
		for(int j = 0; j < N; j++) {
			boolean isPossible = true;
			for(int i = 0; i < N - 1; i++) {
				if(Math.abs(map[i][j] - map[i + 1][j]) == 1) {
					if(map[i][j] > map[i + 1][j]) {
						if(i + L > N - 1) {
							isPossible = false;
							continue;
						}
						for(int k = 1; k <= L; k++) {
							if(runWay[i + k][j] || map[i + k][j] + 1 != map[i][j]) {
								isPossible = false;
								break;
							}
						}
						if(isPossible) {
							for(int k = 1; k <= L; k++) {
								runWay[i + k][j] = true;
							}
						}
					}else {	
						if(i - L + 1 < 0) {
							isPossible = false;
							continue;
						}
						for(int k = 0; k < L; k++) {
							if(runWay[i - k][j] || map[i - k][j] + 1 != map[i + 1][j]) {
								isPossible = false;
								break;
							}
						}
						if(isPossible) {
							for(int k = 0; k < L; k++) {
								runWay[i - k][j] = true;
							}
						}
					}
				}else if(Math.abs(map[i][j] - map[i + 1][j]) > 1) {
					isPossible = false;
				}
			}
			if(isPossible) {
				res++;
			}
		}
		System.out.println(res);
	}
}


