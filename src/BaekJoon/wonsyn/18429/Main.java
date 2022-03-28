package a0328_bj_18429_근손실;

import java.io.*;
import java.util.*;

public class Main {
	static int N, K, count = 0;
	static int[] kit, select;
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_18429.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		kit = new int[N];
		select = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		perm(0, new boolean[N]);
		
		System.out.println(count);
		br.close();
	}
	
	static void perm(int cnt, boolean[] selected) {
		if(cnt == N) {
			int weight = 500;
			for(int i = 0; i < N; i++) {
				weight -= K;
				weight += select[i];
				if(weight < 500) return;
			}
			count++;
		}
		
		for(int i = 0; i < N; i++) {
			if(selected[i]) continue;
			
			select[cnt] = kit[i];
			selected[i] = true;
			perm(cnt + 1, selected);
			selected[i] = false;
		}
	}
}
