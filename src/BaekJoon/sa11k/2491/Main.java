import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int downlen = 1, uplen = 1, result = 1;
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] num = new int[N];

        for(int i = 0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<N-1; i++){
            if(num[i]<=num[i+1]) uplen++;
            else{
                uplen = 1;
            }
            if(result<uplen) result = uplen;
        }

        for(int i = 0; i<N-1; i++){
            if(num[i]>=num[i+1]) downlen++;
            else{
                downlen = 1;
            }
            if(result<downlen) result = downlen;
        }

        System.out.println(result);

        br.close();
    }
}

