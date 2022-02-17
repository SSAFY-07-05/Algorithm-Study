import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] tem = new int[N];
        int max = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i<N; i++){
            tem[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<N-K+1; i++){
            int sum = 0;
            for(int j = i; j<i+K; j++){
                sum += tem[j];
            }
            max = Math.max(sum, max);
        }

        System.out.println(max);

        br.close();
    }
}
