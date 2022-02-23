package week_3;

import java.io.*;
import java.util.*;

public class Main{

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_2605.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		List<Integer> list = new ArrayList<>();

		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 1; i <N+1; i++) {
			int com = Integer.parseInt(st.nextToken());
			
			list.add(list.size()-com, i);
		}
		
		for (Integer integer : list) {
			sb.append(integer+ " ");
		}
		sb.deleteCharAt(sb.lastIndexOf(" "));
		
		System.out.println(sb.toString());
	}
}
