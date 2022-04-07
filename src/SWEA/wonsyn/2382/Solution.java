package a0405_d9_2382_미생물격리;

import java.io.*;
import java.util.*;

public class Solution {
	static class Micro implements Comparable<Micro>{
		int x, y, cnt, d;
		
		Micro(int x, int y, int cnt, int d) {
			this.x = x; this.y = y; this.cnt = cnt; this.d = d;
		}

		@Override
		public int compareTo(Micro o) {
			int x = this.x - o.x;
			if (x == 0) {
				int y = this.y - o.y;
				if (y == 0) {
					return this.cnt - o.cnt;
				}
				return y;
			}
			return x;
		}
	}
	
	static int N, M, K;
	static int[][] del = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static ArrayList<Micro> list;
			
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d9_2382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new Micro(x, y, cnt, d - 1));
			}
			for(int m = 0; m < M; m++) {
				move();
				Collections.sort(list);
				merge();
			}
			
			int sum = 0;
			for(Micro m : list) {
				sum += m.cnt;
			}
			
			sb.append(sum).append("\n");
		}
		
		
		System.out.println(sb.toString());
		br.close();
	}
	
	static void move() {
		for(int s = 0; s < list.size(); s++) {
			Micro m = list.get(s);
			
			m.x = m.x + del[m.d][0];
			m.y = m.y + del[m.d][1];
			
			if(0 == m.x || m.x == N - 1 || 0 == m.y || m.y == N - 1) {
				m.cnt = m.cnt / 2;
				if (m.cnt == 0) {
					list.remove(s--);
					continue;
				}
				m.d = changeDir(m.d);
			}
		}
	}
	
	static void merge() {
		for(int i = 0; i < list.size() - 1; i++) {
			Micro m1 = list.get(i);
			Micro m2 = list.get(i + 1);
			
			if(m1.x == m2.x && m1.y == m2.y) {
				m2.cnt += m1.cnt;
				list.remove(i--);
			}
		}
	}
	
	static int changeDir(int direction) {
		if(direction % 2 == 1) return direction - 1;
		else return direction + 1;
	}
}
