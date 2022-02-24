import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());

            if(num == 0) list.add(i+1);
            else list.add(list.size()-num, i+1);
        }

        for(int l : list)
            System.out.print(l+" ");

        br.close();
    }
}
