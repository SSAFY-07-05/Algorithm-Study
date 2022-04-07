package a0407_d9_2383_점심식사시간;

import java.io.*;
import java.util.*;

public class Solution {
	static class Person {
		int r, c;
		int stair;
		int moveTime;
		int startTime;
		
		Person(int r, int c) {
			this.r = r; this.c = c;
		}
		
		void calcMT() {
			moveTime = Math.abs(r - stairs[stair].r) + Math.abs(c - stairs[stair].c);
		}
	}
	
	static class Stair {
		int r, c, k;
		
		Stair(int r, int c, int k) {
			this.r = r; this.c = c; this.k = k;
		}
	}
	
	static int N, ans;
	static ArrayList<Person> list; 
	static Queue<Person>[] qlist;
	static boolean[] v;
	static Stair[] stairs;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d9_2383.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			list = new ArrayList<>();
			qlist = new Queue[2];
			qlist[0] = new ArrayDeque<>();
			qlist[1] = new ArrayDeque<>();
			stairs = new Stair[2];
			ans = Integer.MAX_VALUE;
			
			int idx = 0;
			for(int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for(int c = 1; c <= N; c++) {
					int n = Integer.parseInt(st.nextToken());
					
					if(n == 0) continue;
					else if (n == 1) list.add(new Person(r, c));
					else stairs[idx++] = new Stair(r, c, n);
				}
			}
			
			go(0);
			
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}

	static void go(int cnt) {
		if(cnt == list.size()) {
			v = new boolean[cnt];
			int time = simul();
			ans = Math.min(ans, time);
			return;
		}
		
		list.get(cnt).stair = 0;
		list.get(cnt).calcMT();
		go(cnt + 1);
		
		list.get(cnt).stair = 1;
		list.get(cnt).calcMT();
		go(cnt + 1);
	}

	static int simul() {
		int cnt = 0;
		int time = 1;
		while(true) {
			for(Queue<Person> q : qlist) {
				int size = q.size();
				
				for(int i = 0; i < size; i++) {
					Person p = q.poll();
					if(p.startTime + stairs[p.stair].k <= time) continue;
					q.offer(p);
				}
			}
			
			if(cnt == list.size() && qlist[0].isEmpty() && qlist[1].isEmpty()) return time;
			
			for(int i = 0; i < list.size(); i++) {
				if(v[i]) continue;
				
				Person p = list.get(i);
				Queue<Person> q = qlist[p.stair];
				
				if(q.size() < 3 && p.moveTime + 1 <= time) {
					p.startTime = time;
					v[i] = true;
					q.offer(p);
					cnt++;
				}
			}
			time++;
		}
	}
}
