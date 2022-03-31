import java.io.*;
import java.util.*;

public class Main {

    static int N, K, res;
    static int num[], result[];
    static boolean v[];

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_18429.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new int[N];
        result = new int[N];
        v = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        System.out.println(res);
    }

    static void perm(int cnt) {

        if (cnt == N) {
            int tmp = 500;
            for (int i = 0; i < N; i++) {
                tmp -= K;
                tmp += result[i];
                if (tmp < 500) return;
            }
            res++;
        }

        for (int i = 0; i < N; i++) {

            if (v[i]) continue;
            result[cnt] = num[i];
            v[i] = true;

            perm(cnt + 1);
            v[i] = false;
        }
    }
}