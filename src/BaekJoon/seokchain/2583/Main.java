import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M, N, tmp;
    static int map[][];

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_2583.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); // 직사각형 갯수

        map = new int[M][N];

        for (int d = 0; d < K; d++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int i = y1; i < y2; i++) {
                for (int j = x1; j < x2; j++) {
                    map[i][j] = 1;
                }
            }
        }

        int res = 0;
        int resArr[] = new int[K];

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    tmp = 0;
                    dfs(i, j);
                    resArr[res] = tmp;
                    res++;
                }
            }
        }
        Arrays.sort(resArr);
        sb.append(res  + "\n");

        for (int i = 0; i < K; i++) {
            if(resArr[i]!=0) sb.append(resArr[i]).append(" ");
        }
        System.out.println(sb);
    }

    static int di[] = {0, 1, 0, -1};  // 동, 남, 서, 북
    static int dj[] = {1, 0, -1, 0};

    static void dfs(int i, int j) {
        map[i][j] = 1;
        tmp++;
        for (int d = 0; d < 4; d++) {
            int dy = i + di[d];
            int dx = j + dj[d];
            if (dy < M && dy >= 0 && dx < N && dx >= 0) {
                if(map[dy][dx] == 0)dfs(dy, dx);
            }
        }
    }
}

