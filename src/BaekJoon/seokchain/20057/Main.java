import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int dy[] = {0, +1, 0, -1}; // 서 남 동 북
    static int dx[] = {-1, 0, +1, 0};
    static int map[][];
    static int N, res;

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_20057.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = N / 2;
        int c = N / 2;
        int k = 1;
        outer:
        while (true) {
            if (k % 2 == 1)
                for (int d = 0; d < 2; d++) {
                    for (int i = 0; i < k; i++) {
                        r = r + dy[d];
                        c = c + dx[d];
                        wind(r, c, d);
                        if (r == 0 && c == 0) break outer;
                    }
                }
            else {
                for (int d = 2; d < 4; d++) {
                    for (int i = 0; i < k; i++) {
                        r = r + dy[d];
                        c = c + dx[d];
                        wind(r, c, d);
                    }
                }
            }
            k++;
        }
        System.out.println(res);
    }
        static int dr[][] =
                // 1   1   2   2   7   7  10  10   5   차
                {{-1,  1, -2,  2, -1,  1, -1,  1,  0,  0}, // 서, 남, 동, 북 방향
                 {-1, -1,  0,  0,  0,  0,  1,  1,  2,  1},
                 {-1,  1, -2,  2, -1,  1, -1,  1,  0,  0},
                 { 1,  1,  0,  0,  0,  0, -1, -1, -2, -1}};
        static int dc[][] =
                {{ 1,  1,  0,  0,  0,  0, -1, -1, -2, -1},
                 {-1,  1, -2,  2, -1,  1, -1,  1,  0,  0},
                 {-1, -1,  0,  0,  0,  0,  1,  1,  2,  1},
                 {-1,  1, -2,  2, -1,  1, -1,  1,  0,  0}};

        static void wind(int r, int c, int type){

            double arr[] = {0.01, 0.01, 0.02, 0.02, 0.07, 0.07, 0.10, 0.10, 0.05};
            int sand = map[r][c];
            map[r][c] = 0;
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                int rN = r + dr[type][i];
                int cN = c + dc[type][i];

                if(rN<N && rN>=0 && cN<N && cN>=0) {
                    map[rN][cN] = map[rN][cN] + (int)(sand * arr[i]);
                    sum += (int)(sand * arr[i]);
                } else {
                    res += (int)(sand * arr[i]);
                    sum += (int)(sand * arr[i]);
                }
            }

            int rN = r+dr[type][9];
            int cN = c+dc[type][9];
            if(rN<N && rN>=0 && cN<N && cN>=0) {
                map[rN][cN] = map[rN][cN] + sand - sum;
            } else res += sand - sum;
        }
}
