import java.io.*;
import java.util.*;

public class Main {
    static int N, min, max;
    static int operator[], num[], result[];
    static boolean v[];

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_14888.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 숫자의 개수
        num = new int [N]; // 입력된 숫자 배열
        operator = new int [N-1]; // 연산자 배열
        result = new int [N-1];
        v = new boolean[N-1];

        // 숫자 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력 받기
        // 0 : +,    1 : -,     2: x,    3: /
        st = new StringTokenizer(br.readLine(), " ");
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int oNum = Integer.parseInt(st.nextToken());
            while(oNum!=0) {
                operator[cnt] = i;
                cnt ++;
                oNum --;
            }
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        perm(0);

        System.out.println(max);
        System.out.println(min);

    }

    static void perm(int cnt) {

        if(cnt == N-1) {
            getOperator();
        }

        for (int i = 0; i < N-1; i++) {
            if(v[i]) continue;
            result[cnt] = operator[i];
            v[i] = true;
            perm(cnt+1);
            v[i] = false;
        }
    }

    static void getOperator() {

        int res = num[0];

        for (int i = 0; i < N-1; i++) {
            if(result[i] == 0) {      // 더하기
                res = res + num[i+1];
            }else if(result[i] == 1){ // 빼기
                res = res - num[i+1];
            }else if(result[i] == 2){ // 곱하기
                res = res * num[i+1];
            }else {                   // 나누기
                res = res / num[i+1];
            }
        }

        min = Math.min(res, min);
        max = Math.max(res, max);
    }
}