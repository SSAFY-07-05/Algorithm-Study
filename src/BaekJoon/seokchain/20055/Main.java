import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("res/input_20055.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int arr[] = new int[N*2];
        boolean v[] = new boolean[N*2];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N*2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println(Arrays.toString(arr));
        int cnt = 1;

        while(true) {

            // 벨트 회전
            int tmp = arr[N*2-1];
            for (int i = N*2-1; i >0; i--) {
                arr[i] = arr[i-1];
                v[i] = v[i-1];
                if(v[N-1]) v[N-1] = false;
            }
            arr[0] = tmp;
            v[0] = false;

            // 로봇 이동
            for (int i = N-2; i >= 0; i--) {
                if(v[i] && arr[i+1]!=0 && !v[i+1]) {
                    v[i] = false;
                    v[i+1] = true;
                    arr[i+1] -= 1;
                }
                if(v[N-1]) v[N-1] = false;
            }

            // 로봇 올리기
            if(arr[0]!=0) { // 올리는칸에 내구도가 0이 아니라면
                v[0] = true;
                arr[0] -= 1; // 로봇이 올라감
            }

            // 벨트 내구도 검사
            int zeroCnt = 0;
            for (int i = 0; i < N*2; i++) {
                if(arr[i]==0) zeroCnt++;
            }

            // 내구도가 0인 벨트가 K와 같거나 크다면 종료
            if(zeroCnt >= K) break;
            cnt ++;

        }
        System.out.println(cnt);
        br.close();
    }
}
