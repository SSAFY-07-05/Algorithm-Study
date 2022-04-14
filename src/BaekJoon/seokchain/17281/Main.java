import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_bj_17281 {

    static boolean v[];
    static int number[], arr[][];
    static int N, score, max;

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("res/input_17281.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N  = Integer.parseInt(br.readLine());

        arr = new int [N][9+1];
        v = new boolean[10];
        number = new int[9];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <=9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0);
        System.out.println(max);
    }

    static void perm(int cnt) {
        if(cnt==3) {
            number[3] = 1;
            cnt++;
        }

        if(cnt == 9){
            play(number);
            max = Math.max(max, score);
            return;
        }

        for (int i = 2; i <= 9; i++) {
            if(v[i]) continue;
            number[cnt] = i;
            v[i] = true;
            perm(cnt+1);
            v[i] = false;
        }
    }

    static void play(int mans[]){

        score = 0;
        int start = 0;

        for (int round = 0; round < N; round++) {
            boolean base[] = new boolean[3];
            int outcnt = 0;

           outer : while (true) {
                for (int i = start; i < 9; i++) { // 1~3번 타자
                    if (arr[round][mans[i]] == 0) outcnt += 1; // 아웃
                    if(outcnt == 3) {
                        if(start==8) start = 0;
                        else start = i+1;
                        break outer;
                    }

                    if (arr[round][mans[i]] == 1) { // 1루타
                        if (base[2]) {
                            base[2] = false;
                            score++;
                        }
                        if (base[1]) {
                            base[1] = false;
                            base[2] = true;
                        }
                        if (base[0]) {
                            base[1] = true;
                            base[0] = false;
                        }
                        base[0] = true;
                    }
                    if (arr[round][mans[i]] == 2) { // 2루타
                        if (base[2]) {
                            base[2] = false;
                            score++;
                        }
                        if (base[1]) {
                            base[1] = false;
                            score++;
                        }
                        if (base[0]) {
                            base[0] = false;
                            base[2] = true;
                        }
                        base[1] = true;
                    }
                    if (arr[round][mans[i]] == 3) { // 3루타
                        if (base[2]) {
                            base[2] = false;
                            score++;
                        }
                        if (base[1]) {
                            base[1] = false;
                            score++;
                        }
                        if (base[0]) {
                            base[0] = false;
                            score++;
                        }
                        base[2] = true;
                    }
                    if (arr[round][mans[i]] == 4) { // 홈런
                        for (int j = 0; j < 3; j++) {
                            if (base[j]) {
                                score++;
                                base[j] = false;
                            }
                        }
                        score++;
                    }
                }
               start = 0;
           }
        }
    }
}

