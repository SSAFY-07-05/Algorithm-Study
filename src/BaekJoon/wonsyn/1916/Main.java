package a0414_Main_bj_1916_최소비용구하기;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<int[]>[] g;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_1916.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		g = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			g[i] = new ArrayList<int[]>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			g[from].add(new int[] {to, cost});
		}
		st = new StringTokenizer(br.readLine(), " ");
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int[] distance = new int[N];
		boolean[] v = new boolean[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		pq.offer(new int[] {start, distance[start]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			if(v[cur[0]]) continue;
			
			v[cur[0]] = true;
			if(cur[0] == end) break;
			
			for(int i = 0; i < g[cur[0]].size(); i++) {
				int[] next = g[cur[0]].get(i);
				if(distance[next[0]] > distance[cur[0]] + next[1]) {
					distance[next[0]] = distance[cur[0]] + next[1];
					pq.offer(new int[] {next[0], distance[next[0]]});
				}
			}
		}
		
		System.out.print(distance[end]);
		br.close();
	}
}
