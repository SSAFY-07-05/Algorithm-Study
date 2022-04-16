package a0416_bj_5972_택배배송;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_5972.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]>[] list = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[from].add(new int[] {to, w});
			list[to].add(new int[] {from, w});
		}
		
		int[] distance = new int[N + 1];
		boolean[] v = new boolean[N + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[1] = 0;
		pq.offer(new int[] {1, distance[1]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(v[cur[0]]) continue;
			
			v[cur[0]] = true;
			if(cur[0] == N) break;
			
			for(int i = 0; i < list[cur[0]].size(); i++) {
				int[] next = list[cur[0]].get(i);
				if(distance[next[0]] > distance[cur[0]] + next[1]) {
					distance[next[0]] = distance[cur[0]] + next[1];
					pq.offer(new int[] {next[0], distance[next[0]]});
				}
			}
		}
		
		System.out.println(distance[N]);
		br.close();
	}
}
