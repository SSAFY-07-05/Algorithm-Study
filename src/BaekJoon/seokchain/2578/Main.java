package week_3;

import java.io.*;
import java.util.*;

public class Main{

	static int map[][];
	static int res;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new int[5][5];
		int num[] = new int[25];

		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int idx = 0;
		for (int i = 0; i < 5; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				num[idx] = Integer.parseInt(st1.nextToken());
				idx++;
			}
		}

		for (int cnt = 0; cnt < 25; cnt++) {

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (map[i][j] == num[cnt]) map[i][j] = 0;
				}
			}
			bingo();
			if (res >= 3) {
				System.out.println(cnt + 1);
				break;
			}
		}
		br.close();
	}

	static void bingo() {

		res = 0;

		// "ㅡ"
		for (int i = 0; i < 5; i++) {
			int cnt = 0;
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 0) cnt++;
				if (cnt == 5) res++;
			}
		}

		// "ㅣ"
		for (int j = 0; j < 5; j++) {
			int cnt = 0;
			for (int i = 0; i < map.length; i++) {
				if (map[i][j] == 0) cnt++;
				if (cnt == 5) res++;
			}
		}
		
		// "\"
		int cnt1 = 0;
		for (int i = 0; i < 5; i++) {
			if (map[i][i] == 0) cnt1++;
			if (cnt1 == 5) res++;
		}

		// "/"
		int cnt2 = 0;
		int dj = 4;
		for (int i = 0; i < 5; i++) {
			if (map[i][dj] == 0) cnt2++;
			if (cnt2 == 5) res++;
			dj--;
		}
	}
}
