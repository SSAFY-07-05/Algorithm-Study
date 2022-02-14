package a0211_bj_2116_주사위쌓기;

import java.io.*;
import java.util.*;

public class Main {
	static int[] oppSide = {5, 3, 4, 1, 2, 0};
	static int max = 0;
	static int sum = 0;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2116.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		int[][] dices = new int[N][6];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 6; j++) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < 6; i++) {
			int upSide = dices[0][i];
			sum = getSideMax(dices[0], i, oppSide[i]);
			for(int f = 1; f < N; f++) {
				for(int j = 0; j < 6; j++) {
					if(dices[f][j] == upSide) {
						sum += getSideMax(dices[f], oppSide[j], j);
						upSide = dices[f][oppSide[j]];
						break;
					}
				}
			}
			
			max = sum > max? sum : max;
		}
		System.out.println(max);
	}
	
	static int getSideMax(int[] dice, int upper, int under) {
		int max = 0;
		for (int i = 0; i < 6; i++) {
			if (i == upper || i == under) continue;
			
			max = dice[i] < max? max : dice[i]; 
		}
		return max;
	}
	
}
