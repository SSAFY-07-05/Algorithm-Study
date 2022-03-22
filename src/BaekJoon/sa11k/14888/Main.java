import java.io.*;
import java.util.*;

public class Main {
    static int N, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] num, oper;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N];
        oper = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<4; i++){
            oper[i] = Integer.parseInt(st.nextToken());
        }

        calc(num[0], 1);

        System.out.println(max);
        System.out.println(min);

        br.close();
    }

    static void calc(int n, int idx){
        if(idx == N){
            max = Math.max(max, n);
            min = Math.min(min, n);
            return;
        }

        for(int i = 0; i<4; i++){
            if(oper[i]>0){
                oper[i]--;

                if(i==0)
                    calc(n + num[idx], idx + 1);

                else if(i==1)
                    calc(n - num[idx], idx + 1);

                else if(i==2)
                    calc(n * num[idx], idx + 1);

                else if(i==3)
                    calc(n / num[idx], idx+1);

                oper[i]++;
            }
        }
    }
}