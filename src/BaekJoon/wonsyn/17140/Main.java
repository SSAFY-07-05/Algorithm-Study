package a0315_bj_17140_이차원배열과연산;

import java.io.*;
import java.util.*;

public class Main {
	static int[][] arr;
	static int rowLen = 3, colLen = 3;
	
	static class Pair implements Comparable<Pair> {
		int num, count;
		
		Pair(int num, int count) {
			this.num = num;
			this.count = count;
		}
		
		@Override
		public int compareTo(Pair o) {
			if(this.count > o.count) return 1;
			else if (this.count < o.count) return -1;
			else {
				if(this.num > o.num) return 1;
				else if(this.num < o.num) return -1;
				else return 0;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_17140.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		arr = new int[101][101];
		rowLen = 3; colLen = 3;
		
		for(int i = 1; i <= colLen; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j <= rowLen; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			if(arr[R][C] == K) {
				break;
			}
			
			if(time == 100) {
				time = -1;
				break;
			}
			
			if (rowLen <= colLen) rowOper();
			else colOper();
			
			time++;
		}

		System.out.println(time);
		br.close();
	}
	
	static void rowOper() {
		int max = 0;
		for(int i = 1; i <= colLen; i++) {
			int[] countNum = new int[101];
			for(int j = 1; j <= rowLen; j++) {
				if(arr[i][j] != 0) {
					countNum[arr[i][j]]++;
				}
			}
			
			ArrayList<Pair> temp = new ArrayList<Pair>();
			for(int j = 1; j <= 100; j++) {
				if(countNum[j] != 0)
					temp.add(new Pair(j, countNum[j]));
			}

			Collections.sort(temp);

			int rowSize = (temp.size() > 50)? 100 : (temp.size() * 2);
			max = Math.max(max, rowSize);
			for(int j = 1; j <= rowSize / 2; j++) {
				arr[i][2 * j - 1] = temp.get(j - 1).num;
				arr[i][2 * j] = temp.get(j - 1).count;
			}
			for(int j = rowSize + 1; j <= 100; j++) arr[i][j] = 0;
		}
		rowLen = max;
	}
	
	static void colOper() {
		int max = 0;
		for(int i = 1; i <= rowLen; i++) {
			int[] countNum = new int[101];
			for(int j = 1; j <= colLen; j++) {
				if(arr[j][i] != 0) {
					countNum[arr[j][i]]++;
				}
			}
			
			ArrayList<Pair> temp = new ArrayList<Pair>();
			for(int j = 1;  j <= 100; j++) {
				if(countNum[j] != 0)
					temp.add(new Pair(j, countNum[j]));
			}

			Collections.sort(temp);
			int colSize = (temp.size() > 50)? 100 : (temp.size() * 2); 
			max = Math.max(max, colSize);
			for(int j = 1; j <= colSize / 2; j++) {
				arr[2 * j - 1][i] = temp.get(j - 1).num;
				arr[2 * j][i] = temp.get(j - 1).count;
			}
			for(int j = colSize + 1; j <= 100; j++) arr[j][i] = 0;
		}
		colLen = max;
	}
}
