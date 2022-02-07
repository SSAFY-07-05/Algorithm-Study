package a0207;

import java.util.*;
import java.io.*;

public class Main_bj_2309_일곱난쟁이 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2309.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 파일 읽어올 Reader
		
		int[] input = new int[9];		// 처음 입력받는 9개 요소
		int[] choice = new int[7];		// 합이 100이 되는 7개 요소
		
		for (int i = 0; i < 9; i++) 	// 9개 라인 읽어서 값 저장
			input[i] = Integer.parseInt(br.readLine());
		
		comb(input, choice, 0, 0);	// 조합
		
		br.close();
	}
	
	static void comb(int[] input, int[] choice, int start, int cnt) {	// 조합 메서드
		// input : 입력 값 배열, choice : 선택한 요소들,  
		
		if(cnt == 7) {		// 요소 7개를 다 구했을 경우 처리
			if(sum(choice) == 100) {	// 합이 100일 경우에, 정렬 후 요소들을 출력
				Arrays.sort(choice);
				for (int i = 0; i < choice.length; i++) {
					System.out.println(choice[i]);
				}
			}
			return;		// 재귀 종료
		}
		
		for(int i = start; i < input.length; i++) {	// start부터 입력된 마지막 값까지
			choice[cnt] = input[i];					// cnt번째 요소로 input[i] 설정
			comb(input, choice, i + 1, cnt + 1);	// 조합 재귀 호출
		}
	}
	
	static int sum(int[] arr) {	// 배열의 합을 구하는 메서드
		int sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		return sum;
	}
}
