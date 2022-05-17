class Solution {
    static int answer, tarNum;
    static int numArr[];

    public int solution(int[] numbers, int target) {
        answer = 0;
        numArr = numbers;
        tarNum = target;

        dfs(0, 0);

        return answer;
    }

    static void dfs(int cnt, int sum) {

        if(cnt == numArr.length){
            if(sum == tarNum) answer ++;
            return;
        }

        dfs(cnt+1, sum+numArr[cnt]);
        dfs(cnt+1, sum-numArr[cnt]);

    }
}