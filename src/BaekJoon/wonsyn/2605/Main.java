package a0223_bj_2605_줄세우기;

import java.io.*;
import java.util.*;

public class Main {
	static int size = 0;

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2605.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] line = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			int temp = size - Integer.parseInt(st.nextToken());
			if (i == 0)  {
				line[0] = 1;
				size++;
				continue;
			}
			for (int j = size - 1; j >= temp; j--) {
				line[j + 1] = line[j];
			}
			
			line[temp] = i + 1;
			size++;
		}

		for (int i = 0; i < N; i++) {
			System.out.print(line[i] + " ");
		}
		
		br.close();
	}
}
