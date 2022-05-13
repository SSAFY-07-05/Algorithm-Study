class Solution_lv1_키보드누르기 {
    static int[][] map = {{1,  2,  3},
                   {4,  5,  6},
                   {7,  8,  9},
                   {-1, 0, -1}};
    static int tmpi, tmpj;
    static String ans = "";
    static Pos lpos = new Pos(3, 0);
    static Pos rpos = new Pos(3, 2);

    public String solution(int[] numbers, String hand) {
        String answer = "";
        for (int i = 0; i < numbers.length; i++) {
            search(numbers[i]);
            if (tmpj == 0) left();
            else if (tmpj == 2) right();
            else {
                int ldis = Math.abs(lpos.i - tmpi) + Math.abs(lpos.j - tmpj);
                int rdis = Math.abs(rpos.i - tmpi) + Math.abs(rpos.j - tmpj);
                if (ldis < rdis) left();
                else if (ldis > rdis)right();
                else {
                    if (hand.equals("right")) {
                        right();
                    } else {
                        left();
                    }
                }
            }
        }
        answer += ans;
        return answer;
    }
    static class Pos {
        int i, j;
        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static void search(int num) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == num) {
                    tmpi = i;
                    tmpj = j;
                    return;
                }
            }
        }
    }
    static void left(){
        lpos.i = tmpi;
        lpos.j = tmpj;
        ans += "L";
    }
    static void right(){
        rpos.i = tmpi;
        rpos.j = tmpj;
        ans +="R";
    }
}
