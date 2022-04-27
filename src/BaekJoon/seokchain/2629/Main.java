import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bj_2629 {

        static int N;
        static int arr[];
        static boolean[][] dp;

        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int M = Integer.parseInt(br.readLine());
            int[] ball = new int[M];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++)
                ball[i] = Integer.parseInt(st.nextToken());

            dp = new boolean[N + 1][55001];

            dfs(0, 0);

            for (int i = 0; i < M; i++) {
                if (dp[N][ball[i]]) {
                    System.out.print("Y ");
                    continue;
                }

                System.out.print("N ");
            }
        }

        public static void dfs(int cnt, int weight) {
            if (dp[cnt][weight]) return;

            dp[cnt][weight] = true;

            if (cnt == N) return;

            dfs(cnt + 1, weight + arr[cnt]);              //추 무게 합
            dfs(cnt + 1, weight);                           //해당 추를 사용 안하는 경우
            dfs(cnt + 1, Math.abs(weight - arr[cnt]));    //구슬이 있는 쪽에 추를 더하는
        }
    }