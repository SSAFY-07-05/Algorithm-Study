import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int arr[][];

	public static void main(String[] args) throws Exception {

		//System.setIn(new FileInputStream("res/input_14501.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		//for (int[] is : arr) {System.out.println(Arrays.toString(is));}

		combi(0, 0);
		System.out.println(max);

	}
	static int max = Integer.MIN_VALUE;
	static void combi(int start, int cost) {
		
		if(start>N) return;
		
		max = Math.max(max,  cost);
		
		for (int i = start; i < N; i++) {
			combi(i+arr[i][0], cost+arr[i][1]);
		}
	}
}
