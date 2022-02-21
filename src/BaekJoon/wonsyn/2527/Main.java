package a0216_bj_2527_직사각형;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2527.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int[][] s = new int[4][2]; // (s[0], s[1] : 1, s[2], s[3] : 2)
			for(int i = 0; i < s.length; i ++)
				for (int j = 0; j < 2; j++)
					s[i][j] = Integer.parseInt(st.nextToken());

			String str;
			
			if((s[0][0] == s[3][0] && s[0][1] == s[3][1]) ||		// 점
					(s[0][0] == s[3][0] && s[1][1] == s[2][1]) ||
					(s[1][0] == s[2][0] && s[1][1] == s[2][1]) ||
					(s[1][0] == s[2][0] && s[0][1] == s[3][1]))
				str = "c";
			else if ((s[0][0] > s[3][0] || s[1][1] < s[2][1] ||
					s[1][0] < s[2][0] || s[0][1] > s[3][1]))	// 공통부분이 없음
				str = "d";
			else if ((s[0][0] == s[3][0] || s[1][1] == s[2][1] ||
					s[1][0] == s[2][0] || s[0][1] == s[3][1]))	// 선분
				str = "b";
			else
				str = "a";
			
			sb.append(str).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
}
