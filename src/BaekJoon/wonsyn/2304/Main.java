package a0209_bj_2304_창고다각형;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_bj_2304.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Stack<Stick> s1 = new Stack<>();				// 앞에서 뒤, 뒤에서 앞 순회할 스택 2개 선언
		Stack<Stick> s2 = new Stack<>();
		ArrayList<Stick> arr = new ArrayList<>();		// Stick 데이터를 받아올 ArrayList
		
		int N = Integer.parseInt(br.readLine());		// 데이터 개수 입력
		
		for(int l = 0; l < N; l++) {					// 데이터 입력후 Stick 변환, arr에 저장
			st = new StringTokenizer(br.readLine(), " ");
			arr.add(new Stick(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(arr);							// Stick.idx값 기준으로 정렬
		
		for (int i = 0; i < N; i++) {			// 왼쪽 -> 오른쪽 순회값 스택에 push
			Stick s = arr.get(i);
			if(s1.empty()) s1.push(s);
			if(s1.peek().h < s.h) s1.push(s);
		}
		for (int i = N - 1; i >= 0; i--) {		// 오른쪽 -> 왼쪽 순회값 스택에 push
			Stick s = arr.get(i);
			if(s2.empty()) s2.push(s);
			if(s2.peek().h < s.h) s2.push(s);
		}
		
		int sum = 0;							// 넓이 합 변수
		int center = ((s2.peek().idx + 1) - s1.peek().idx) * s1.peek().h;	// 중간에서 교차하는 지점의 기둥 넓이
		sum += center;							// sum에 추가
		
		while(!s1.isEmpty()) {							// s1 스택이 빌 때까지
			Stick high = s1.pop();						// 앞부분 기둥 pop
			if(!s1.isEmpty()) {							// pop후 스택이 비지 않았다면
				Stick low = s1.peek();					// 해당 기둥 peek
				sum += (high.idx - low.idx) * low.h;	// 두 기둥 idx차이와 낮은기둥의 높이를 곱해 넓이를 구하고, sum에 합
			}
		}
		while(!s2.isEmpty()) {							// s2 스택이 빌 때까지
			Stick high = s2.pop();						// 앞부분 기둥 pop
			if(!s2.isEmpty()) {							// pop후 스택이 비지 않았다면,				
				Stick low = s2.peek();					// 해당 기둥 peek
				sum += ((low.idx) - (high.idx)) * low.h;	// 두 기둥 idx차이와 낮은 기둥의 높이를 곱해 넓이를 구하고, sum에 합
			}
		}
		
		System.out.println(sum);						// sum 출력
		
	}
	
	static class Stick implements Comparable<Stick>{		// 기둥을 정렬하기 위한 클래스
		int idx;			// 기둥 인덱스
		int h;				// 기둥 높이
		
		Stick (int idx, int h){		// 기둥 초기화, 생성
			this.idx = idx;
			this.h = h;
		}

		@Override
		public int compareTo(Stick o) {		// 정렬에 필요한 메서드
			if(this.idx > o.idx) return 1;
			else if (this.idx < o.idx) return -1;
			return 0;
		}
		
//		@Override			// 디버그를 위한 코드
//		public String toString() {
//			return "{" + this.idx + ", " + this.h + "}";
//		}
	}
}
