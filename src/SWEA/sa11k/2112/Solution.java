import java.io.*;
import java.util.*;

public class Solution{
	static int D, W, K, result;
	static int[][] grid, temp;
	static boolean[] check;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc<=T; tc++){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			grid = new int[D][W];
			temp = new int[D][W];
			result = Integer.MAX_VALUE;

			for(int i = 0; i<D; i++){
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j<W; j++){
					int in = Integer.parseInt(st.nextToken());
					grid[i][j] = temp[i][j] = in;
				}
			}

			if(pass()){
				result = 0;
			}else{
				injection(0, 0);
			}
			System.out.println("#" + tc + " " + result);
		}

		br.close();
	}

	static void injection(int cnt, int layer) {
		if(cnt>=result) return;
		if(layer == D){
			if(pass()){
				result = result > cnt ? cnt : result;
			}

			return;
		}

		injection(cnt, layer + 1);

		for(int j = 0; j<W; ++j) temp[layer][j] = 0;
		injection(cnt + 1, layer + 1);

		for(int j = 0; j<W; ++j) temp[layer][j] = 1;
		injection(cnt + 1, layer + 1);

		for(int j = 0; j<W; ++j) temp[layer][j] = grid[layer][j];
	}

	static boolean pass(){
		for(int j = 0; j<W; j++){
			int cnt = 1;
			int in = temp[0][j];
			boolean stop = false;

			for(int i = 1; i<D; i++){
				if(in == temp[i][j])
					cnt ++;
				else{
					in = temp[i][j];
					cnt = 1;
				}

				if(cnt == K){
					stop = true;
					break;
				}
			}
			if(!stop) return false;
		}
		return true;
	}
}

/*
1
6 8 4
1 1 0 0 0 1 1 0
1 0 1 0 0 1 1 1
0 1 0 0 1 1 0 0
1 0 1 0 0 0 0 0
1 1 0 0 0 0 0 0
1 0 0 0 1 1 1 1
 */