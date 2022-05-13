package a0511_programmers_kakao_lv1_키패드누르기;

class Solution {
	int[][] del = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int[][] loc = {{3, 1}, 
    		{0, 0}, {0, 1}, {0, 2}, 
    		{1, 0}, {1, 1}, {1, 2},  
    		{2, 0}, {2, 1}, {2, 2},  
    };
    class Finger {
        int r, c;
        Finger(int r, int c) {this.r = r; this.c = c;}
    }
    
    public String solution(int[] numbers, String hand) {
        String answer = "";
        Finger left = new Finger(3, 0);
        Finger right = new Finger(3, 2);
        for(int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int col = num % 3;
            if(col == 2 || num == 0) {
                int l = getDistance(num, left);
                int r = getDistance(num, right);
                if(l == r) {
                	if(hand.equals("right")) {right.r = loc[num][0]; right.c = loc[num][1]; answer += "R";}
                	else {left.r = loc[num][0]; left.c = loc[num][1]; answer += "L";}
                } else {
                	if(l > r) {right.r = loc[num][0]; right.c = loc[num][1]; answer += "R";}
                	else {left.r = loc[num][0]; left.c = loc[num][1]; answer += "L";}
                }
            } else if(col == 1) {
            	left.r = loc[num][0]; left.c = loc[num][1]; answer += "L";
            } else if(col == 0 && num != 0) {
            	right.r = loc[num][0]; right.c = loc[num][1]; answer += "R";
            }
        }
        return answer;
    }
    
    public int getDistance(int num, Finger finger) {
        return Math.abs(loc[num][0] - finger.r) + Math.abs(loc[num][1] - finger.c);
    }
}
