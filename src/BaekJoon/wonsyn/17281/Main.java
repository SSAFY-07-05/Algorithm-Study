package a0413_Main_bj_17281_야구공;

import java.io.*;
import java.util.*;

public class Main {
	static int N, idx, max;
	static int[] selected, hit = {-1, 1, 2, 3, 4};
	static int[][] person;
	static boolean[] v, base;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17281.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		person = new int[N][9];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 9; j++) {
				person[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = Integer.MIN_VALUE;
		selected = new int[9];
		v = new boolean[9];
		
		selected[3] = 0;
		v[0] = true;
		perm(0);
		
		System.out.println(max);
		br.close();
	}
	
	static void perm(int cnt) {
		if(cnt == 9) {
			int score = 0;
			idx = 0;
			for(int i = 0; i < N; i++) {
				base = new boolean[4];
				score += play(i);
			}
			
			if(max < score) {
				max = score;
			}
			return;
		}
		
		if (cnt == 3) cnt++;
		for(int i = 1; i < 9; i++) {
			if(v[i]) continue;
			
			v[i] = true;
			selected[cnt] = i;
			perm(cnt + 1);
			v[i] = false;
		}
		if (cnt == 4) cnt--;
	}
	
	static int play(int inning) {
		int outCnt = 0;
		int score = 0;
		
		while(outCnt < 3) {
			int h = person[inning][selected[idx]];
			idx = (idx + 1) % 9;
			if(h == 0) {
				outCnt++; continue;
			}
			
			score += baseRun(hit[h]);
		}
		
		return score;
	}
	
	static int baseRun(int num) {
		int score = 0;
		
		if(num == 4) {
			int cnt = 0;
			for(int b = 1; b < 4; b++) {
				if(base[b]) {
					base[b] = false;
					cnt++;
				}
			}
			score = 1 + cnt;
			return score;
		}
		
		for(int b = 3; b > 0; b--) {
			int goal;
			if(base[b]) {
				goal = b + num;
				base[b] = false;

				if(goal > 3) score++;
				else base[goal] = true;
			}
		}
		base[num] = true;
		return score;
	}
}
