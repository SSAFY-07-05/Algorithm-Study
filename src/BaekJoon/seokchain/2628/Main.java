package week_3;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine()); // 종이 자르는 횟수
		
		List<Integer> listX = new ArrayList<>();
		List<Integer> listY = new ArrayList<>();

		for (int i = 0; i < k; i++) {

			st = new StringTokenizer(br.readLine(), " ");

			int type = Integer.parseInt(st.nextToken()); // 가로,세로 구분
			int num = Integer.parseInt(st.nextToken()); // 점선 번호

			if (type == 0)
				listX.add(num);
			if (type == 1)
				listY.add(num);
		}
		
		listX.add(0);
		listX.add(M);
		
		listY.add(0);
		listY.add(N);
		
		Collections.sort(listX);
		Collections.sort(listY);
		
		int xMax = 0;
		for (int i = 0; i < listX.size()-1; i++) {
			xMax = Math.max(xMax, listX.get(i+1)-listX.get(i));
		}

		int yMax = 0;
		for (int i = 0; i < listY.size()-1; i++) {
			yMax = Math.max(yMax, listY.get(i+1)-listY.get(i));
		}
		
		int res = (int) ((xMax*N) * ((double)yMax/N));
		System.out.println(res);
	}
}
