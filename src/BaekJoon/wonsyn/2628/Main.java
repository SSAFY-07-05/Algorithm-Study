package a0224_bj_2628_종이자르기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2628.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> wList = new ArrayList<>();
		ArrayList<Integer> hList = new ArrayList<>();
		int max = -1;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int N = Integer.parseInt(br.readLine());
		
		wList.add(0); hList.add(0);
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dir = Integer.parseInt(st.nextToken());
			
			if (dir == 0) wList.add(Integer.parseInt(st.nextToken()));
			else hList.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(wList); Collections.sort(hList);
		wList.add(H); hList.add(W);
		
		for (int i = 1; i < wList.size(); i++) {
			for (int j = 1; j < hList.size(); j++) {
				int area = (wList.get(i) - wList.get(i - 1)) * (hList.get(j) - hList.get(j - 1));
				max = Math.max(max, area);
			}
		}
		
		System.out.println(max);
		
		br.close();
	}
}
