package a0311_bj_20055_컨베이어벨트위의로봇;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_20055.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] belt = new int[2][N];
		boolean[] robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			belt[0][i] = Integer.parseInt(st.nextToken());
		for(int i = N - 1; i >= 0; i--)
			belt[1][i] = Integer.parseInt(st.nextToken());
		
		int level = 0;  // 단계
		int zero = 0;
		while(!(zero >= K)) {
			level++;
			
			// 벨트 회전
			int temp = belt[1][0];
			for(int i = 0; i < N - 1; i++)
				belt[1][i] = belt[1][i + 1];
			belt[1][N - 1] = belt[0][N - 1];
			for(int i = N - 1; i > 0; i--) {
				belt[0][i] = belt[0][i - 1];
				robot[i] = robot[i - 1];
			}
			belt[0][0] = temp;
			robot[0] = false; robot[N - 1] = false;
			
			// 로봇 이동
			for(int i = N - 1; i > 0; i--) {
				if(robot[i - 1] && !robot[i] && belt[0][i] > 0) {
					robot[i] = true;
					robot[i - 1] = false;
					if(--belt[0][i] == 0) zero++;
				}
			}
			
			if(belt[0][0] > 0) {
				robot[0] = true;
				if(--belt[0][0] == 0) zero++;
			}
		}
		
		System.out.println(level);
		br.close();
	}
}
