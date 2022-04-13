import java.io.*;
import java.util.*;

public class Main{

    static int N, M;
    static int map[][];
    static boolean v[][];

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_4963.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (M == 0 && N == 0) break;
            map = new int[N][M];
            v = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && !v[i][j]) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    static int[] ni = {1, -1, 0, 0, -1, 1, 1, -1};
    static int[] nj = {0, 0, 1, -1, -1, 1, -1, 1};

    static void dfs(int i, int j) {

        v[i][j] = true;

        for (int d = 0; d < 8; d++) {
            int di = i + ni[d];
            int dj = j + nj[d];
            if (di < N && di >= 0 && dj < M && dj >= 0 && map[di][dj] == 1 && !v[di][dj]) {
                dfs(di, dj);
            }
        }
    }

}
