class Solution {
    static int[] two = {3, 1, 0, 1, 2, 1, 2, 3, 2, 3, 4, 4};
    static int[] five = {2, 2, 1, 2, 1, 0, 1, 2, 1, 2, 3, 3};
    static int[] eight = {1, 3, 2, 3, 2, 1, 2, 1, 0, 1, 2, 2};
    static int[] zero = {0, 4, 3, 4, 3, 2, 3, 2, 1, 2, 1, 1};
    static int left = 10, right = 11;
    static String answer = "";
    static String H = "";

    public String solution(int[] numbers, String hand) {
        H = hand;
        for(int i = 0; i<numbers.length; i++){
            int now = numbers[i];
            if(now == 1 || now == 4 || now == 7){
                left = now;
                answer += "L";
            }
            else if(now == 3 || now == 6 || now == 9){
                right = now;
                answer += "R";
            }
            else{
                caldir(now);
            }
        }
        return answer;
    }

    static void caldir(int now){
        if(now == 2){
            int dleft = two[left];
            int dright = two[right];
            comcal(dleft, dright, now);
        }
        else if(now == 5){
            int dleft = five[left];
            int dright = five[right];
            comcal(dleft, dright, now);
        }
        else if(now == 8){
            int dleft = eight[left];
            int dright = eight[right];
            comcal(dleft, dright, now);
        }
        else if(now == 0){
            int dleft = zero[left];
            int dright = zero[right];
            comcal(dleft, dright, now);
        }
    }

    static void comcal(int l, int r, int now){
        if(l<r){
            answer += "L";
            left = now;
        }
        else if(l>r){
            answer += "R";
            right = now;
        }
        else{
            if(H.equals("left")){
                answer += "L";
                left = now;
            }
            else{
                answer += "R";
                right = now;
            }
        }
    }
}
