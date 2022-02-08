package a0208_bj_9012_괄호;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_9012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력 Reader
		
		int T = Integer.parseInt(br.readLine());		// tc 개수
		
		for (int tc = 1; tc <= T; tc++) {
			boolean VPS = true;				// VPS 판별 boolean 변수
			String s = br.readLine();		// 괄호 문자열 받아옴
			ArrayDeque<Character> stack = new ArrayDeque<>();	// 스택 생성
			
			for (int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == '(') {				// i번째 문자가 '(' 인 경우
					if (i != s.length() - 1)				// 마지막 문자가 아니라면 push
						stack.push(s.charAt(i));
					else {									// 마지막 문자라면 VPS가 아니다 -> false후 판별 종료
						VPS = false;
						break;
					}
				}
				else {													// i번째 문자가 ')' 인 경우
					if (stack.size() == 0 || stack.peek() != '(') {			// 스택에 저장된 값이 없거나, peek의 값이 '('가 아니어서
						VPS = false;										// 짝이 맞지 않으면 VPS = false후 판별 종료
						break;
					} else stack.pop();										// 스택에 저장된 값이 '(' 라면 pop
				}
			}
			
			if (stack.size() != 0) VPS = false;		// 반복문을 모두 돌았는데 스택에 남은 괄호가 있다면
													// 짝이 맞지 않으므로 VPS = false
			if(VPS == false) System.out.println("NO");		// VPS에 맞게 출력
			else System.out.println("YES");
		}
		
		br.close();
	}

}
