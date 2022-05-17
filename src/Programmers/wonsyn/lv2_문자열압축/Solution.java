package a0516_programmers_kakao_lv2_문자열압축;

class Solution {
    
    public int solution(String s) {
        int answer = s.length();
        
        for(int i = 1; i <= s.length() / 2; i++) {
            String temp = s.substring(0, i);
            int count = 1;
            String stack = "";
            String remain = "";
            
            for(int j = i; j < s.length(); j += i) {
            	if(i + j > s.length()) {
            		remain = s.substring(j);
            		break;
            	}
            	
            	if(temp.equals(s.subSequence(j, j + i))) {
            		count++;
            	} else {
            		if(count == 1) stack += temp;
            		else stack += count + temp;
            		temp = s.substring(j, j + i);
            		count = 1;
            	}
            }
            
            if(count == 1) stack += temp + remain;
            else stack += count + temp + remain;
            
            answer = Math.min(answer, stack.length());
        }
        
        return answer;
    }
}