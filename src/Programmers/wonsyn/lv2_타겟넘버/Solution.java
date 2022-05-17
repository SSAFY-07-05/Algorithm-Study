package a0512_programmers_kakao_lv2_타겟넘버;

public class Solution {
    boolean[] selected;
	int ans;
	public int solution(int[] numbers, int target) {
        int answer = 0;
        selected = new boolean[numbers.length];
        ans = 0;
        subset(numbers, 0, target);
        answer = ans;
        return answer;
    }
    
    void subset(int[] numbers, int cnt, int target) {
    	if (cnt == numbers.length) {
    		int sum = 0;
    		for(int i = 0; i < numbers.length; i++) {
    			if (selected[i]) sum += numbers[i];
    			else sum -= numbers[i];
    		}
    		if(sum == target) ans++;
    		return;
    	}
    	
    	selected[cnt] = true;
    	subset(numbers, cnt + 1, target);
    	selected[cnt] = false;
    	subset(numbers, cnt + 1, target);
    }
}
