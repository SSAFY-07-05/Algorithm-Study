class Solution {
    static int len = 0, tar = 0, answer = 0;
    static int[] target = {-1, 1};
    static int[] result;
    static int[] num;
    public int solution(int[] numbers, int target) {
        len = numbers.length;
        result = new int[len];
        tar = target;

        num = new int[len];
        for(int i = 0; i<len; i++){
            num[i] = numbers[i];
        }

        perm(0);
        return answer;
    }

    static void perm(int cnt){
        if(cnt == len){
            if(cal() == tar){
                answer++;
                return;
            }
            return;
        }

        for(int i = 0; i<2; i++){
            result[cnt] = target[i];
            perm(cnt+1);
        }
    }

    static int cal(){
        int ans = 0;
        for(int i = 0; i<len; i++){
            ans += result[i] * num[i];
        }
        return ans;
    }
}