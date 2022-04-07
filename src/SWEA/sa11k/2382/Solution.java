import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, K, result;
	static List<Integer>[][] grid;
	static int[][] microbe;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {-1, 0, 1, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc<=T; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			grid = new ArrayList[N][N];
			microbe = new int[K][4];	// 0 : 세로 위치, 1 : 가로 위치, 2 : 미생물 수, 3 : 이동방향

			for(int i = 0; i<N; i++){
				for(int j = 0; j<N; j++){
					grid[i][j] = new ArrayList<>();
				}
			}

			for(int i = 0; i<K; i++){
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<4; j++){
					microbe[i][j] = Integer.parseInt(st.nextToken());
				}
				switch (microbe[i][3]) {
					case 1:
						microbe[i][3] = 0;
						break;
					case 4:
						microbe[i][3] = 1;
						break;
				}
			}
			System.out.println("#" + tc + " " + solve());
		}
		br.close();
	}

	static int solve(){
		for(int k = 0; k<M; k++){
			for(int i = 0; i<K; i++){
				if(microbe[i][2] == 0) continue;
				microbe[i][0] += dy[microbe[i][3]];
				microbe[i][1] += dx[microbe[i][3]];
				if(microbe[i][0]<=0 || microbe[i][0]>=N-1 || microbe[i][1]<=0 || microbe[i][1]>=N-1){
					microbe[i][2] /= 2;
					if(microbe[i][2] > 0)
						microbe[i][3] = (microbe[i][3] + 2) % 4;
				}
				else
					grid[microbe[i][0]][microbe[i][1]].add(i);
			}

			for(int i = 0; i<N; i++){
				for(int j = 0; j<N; j++){
					if(grid[i][j].size() > 1){
						int maxIdx = 0;
						int max = 0;
						int sum = 0;
						for(int num : grid[i][j]){
							if(max < microbe[num][2]){
								max = microbe[num][2];
								maxIdx = num;
							}
							sum += microbe[num][2];
						}
						microbe[maxIdx][2] = sum;
						for(int num : grid[i][j]){
							if(num != maxIdx)
								microbe[num][2] = 0;
						}
					}
					grid[i][j].clear();
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i<K; i++)
			if(microbe[i][2] > 0)
				cnt += microbe[i][2];

		return cnt;

	}
}