import java.io.*;
import java.util.*;

public class Main{
	
	static int max, cnt;
	static int secondNum;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_2635.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		max = Integer.MIN_VALUE;
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			cnt = 1;
			function(N, i, i);
		}
		sb.append(max).append("\n").append(N).append(" ").append(secondNum);
		printRes(N, secondNum);
		System.out.println(sb.toString());
	}
	
	static void function(int A, int B, int idx) {	
		cnt ++;
		if((A-B)<0) {
			if (cnt>max) {
				max = cnt;
				secondNum = idx;
			}
			return ;
		}
		function(B, A-B, idx);
	}
	
	static void printRes(int A, int B) {
		
		if((A-B)<0) return ;
		sb.append(" ").append((A-B));
		printRes(B, A-B);
		
	}
}
