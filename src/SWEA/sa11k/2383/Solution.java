import java.io.*;
import java.util.*;

public class Solution {
	private static int[][] map;
	private static int N, ans;
	private static ArrayList<pair> ppl;
	private static ArrayList<pair> stair;
	private static int[] set;
	private static PriorityQueue<pair2> pq;

	static class pair {
		int y;
		int x;

		public pair(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}

	static class pair2 implements Comparable<pair2> {
		int time;
		int stair;
		int status;

		public pair2(int time, int stair, int status) {
			super();
			this.time = time;
			this.stair = stair;
			this.status = status;
		}

		@Override
		public int compareTo(pair2 o) {
			// TODO Auto-generated method stub
			if (this.time == o.time) {
				return o.status - this.status;
			} else {
				return this.time - o.time;
			}
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			ppl = new ArrayList<>();
			stair = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						ppl.add(new pair(i, j));
					if (map[i][j] > 1)
						stair.add(new pair(i, j));
				}
			}
			set = new int[ppl.size()];
			ans = Integer.MAX_VALUE;
			whichStair(0);

			System.out.println("#" + tc + " " + ans);
		}
	}

	public static void whichStair(int len) {
		if (len == ppl.size()) {
			pq = new PriorityQueue<>();

			for (int i = 0; i < len; i++) {
				int py = ppl.get(i).y;
				int px = ppl.get(i).x;
				int sy = stair.get(set[i]).y;
				int sx = stair.get(set[i]).x;
				int t = Math.abs(py - sy) + Math.abs(px - sx);
				pq.add(new pair2(t, set[i], -1));
			}
			goStair();
			return;
		}

		for (int i = 0; i < stair.size(); i++) {
			set[len] = i;
			whichStair(len + 1);
		}
	}

	public static void goStair() {
		int min = 0;
		int[] inStair = new int[stair.size()];
		while (!pq.isEmpty()) {
			min++;

			while (!pq.isEmpty()) {
				pair2 front = pq.peek();
				if (front.time != min)
					break;
				pq.poll();
				int mystair = front.stair;
				if (front.status != 1) {
					if (inStair[mystair] < 3) {
						int ntime = 0;
						if (front.status == -1) {
							ntime = front.time + 1 + map[stair.get(mystair).y][stair.get(mystair).x];
						} else if (front.status == 0) {
							ntime = front.time + map[stair.get(mystair).y][stair.get(mystair).x];
						}
						pq.add(new pair2(ntime, front.stair, 1));
						inStair[mystair]++;
					} else {
						pq.add(new pair2(front.time + 1, front.stair, 0));
					}
				} else {
					inStair[mystair]--;
				}
			}
		}
		ans = ans > min ? min : ans;
	}
}