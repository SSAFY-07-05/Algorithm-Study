package week_1;

import java.io.*;
import java.util.*;

public class Main_bj_9012_괄호_대전_5반_윤석찬 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("res/input_9012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 입력
		for (int tc = 0; tc < T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine()); // 문자열 st에 저장
			Stack<Character> stack = new Stack<Character>(); // char 형 스택 생성

			String str = st.nextToken(); // String에 문자열 입력
			String msg = null; // 출력 메세지를 저장하는 문자열

			int cnt_L = 0; // ( 괄호의 개수
			int cnt_R = 0; // ) 괄호의 개수

			for (int i = 0; i < str.length(); i++) { // 스택에 문자열 str을 char형태로 push
				stack.push(str.charAt(i));
			}

			while (!stack.isEmpty()) { // 스텍이 비어 있을때까지 반복

				// cnt_R + cnt_L = 0 일때 즉 처음 들어온 괄호가 '(' 일 경우 출력메세지에 NO 저장 후 break;
				if ((cnt_R + cnt_L) == 0 && stack.peek() == '(') {
					msg = "NO";
					break;
				}
				// 괄호가 '(' 일 경우 cnt_L++
				if (stack.peek() == '(') {
					cnt_L++;
					stack.pop();
				}
				// 괄호가 ')' 일 경우 cnt_R++
				else if (stack.peek() == ')') {
					cnt_R++;
					stack.pop();
				}
				// 괄호 카운트 중 cnt_L > cnt_R 인 경우 출력메세지에 NO 저장 후 break;
				if (cnt_L > cnt_R) {
					msg = "NO";
					break;
				}
				// 두 개의 괄호가 같을 경우 출력메세지에 YES 아닐경우 NO
				if (cnt_L == cnt_R)
					msg = "YES";
				else
					msg = "NO";
			}

			System.out.println(msg);
		}
	}
}
