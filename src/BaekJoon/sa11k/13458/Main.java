import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] exam = new int[N];
        long count = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i<N; i++){
            exam[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++){
            exam[i] -= first;
            count++;
            if(exam[i]>0){
                if(exam[i] % second == 0) count += exam[i] / second;
                else count += exam[i] / second + 1;
            }
        }

        System.out.println(count);

        br.close();
    }
}