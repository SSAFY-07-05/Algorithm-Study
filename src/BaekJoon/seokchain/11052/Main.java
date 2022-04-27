import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int card[] = new int[N+1];
        int max[] = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <=N ; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <=N; i++) {
            for (int j = 1; j <=i; j++) {
                max[i] = Math.max(max[i], max[i-j]+card[j]);
            }
        }
        System.out.println(max[N]);
    }
}
