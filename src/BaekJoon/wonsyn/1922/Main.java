package a0510_bj_1922_네트워크연결;

import java.io.*;
import java.util.*;

public class Main {
	static class Edge implements Comparable<Edge> {
		int from, to, cost;
		Edge(int from, int to, int cost) {
			this.from = from; this.to = to; this.cost = cost; 
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	static int N, M;
	static int[] parents;
	static ArrayList<Edge> g;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1922.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		g = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			g.add(new Edge(from, to, cost));
		}
		Collections.sort(g);
		
		makeSet();
		int res = 0, cnt = 0;
		for(Edge e : g) {
			if(union(e.from, e.to)) {
				res += e.cost;
				if(++cnt == N - 1) break;
			}
		}
		
		System.out.print(res);
		br.close();
	}
	
	static void makeSet() {
		parents = new int[N + 1];
		for(int i = 0; i < N; i++) parents[i] = i;
	}
	
	static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int ar = findSet(a);
		int br = findSet(b);
		if(ar == br) return false;
		parents[br] = ar;
		return true;
	}
}
