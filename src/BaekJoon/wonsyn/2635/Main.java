package a0225_bj_2635_수이어가기;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2635.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int max = -1;
		Integer[] result = { };
		int N = Integer.parseInt(br.readLine());
		
		for(int n = N; n >= N / 2; n--) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(N);
			list.add(n);
			
			while(list.get(list.size() - 2) >= list.get(list.size() - 1)) {
				list.add(list.get(list.size() - 2) - list.get(list.size() - 1));
			}
			if(max < list.size()) {
				max = list.size();
				result = list.toArray(new Integer[0]);
			}
		}
		
		if(result != null) {
			System.out.println(max);
			for (int i = 0; i < result.length; i++) {
				System.out.print(result[i] + " ");
			}
		}
		br.close();
	}
}
