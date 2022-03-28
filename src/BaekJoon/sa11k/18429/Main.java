import java.io.*;
import java.util.*;

public class Main {
    static int N, K, result = 0;
    static int[] kit, sports;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        kit = new int[N];
        sports = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<N; i++){
            kit[i] = Integer.parseInt(st.nextToken());
        }

        perm(0, new boolean[N]);

        System.out.println(result);

        br.close();
    }

    static void perm(int cnt, boolean[] isSelected){
        if(cnt == N){
            int w = 500;
            for(int i = 0; i<N; i++){
                w -= K;
                w += sports[i];
                if(w<500) return;
            }
            result++;
        }

        for(int i=0; i<N; i++){
            if(isSelected[i]) continue;

            sports[cnt] = kit[i];
            isSelected[i] = true;
            perm(cnt+1, isSelected);
            isSelected[i] = false;
        }
    }
}