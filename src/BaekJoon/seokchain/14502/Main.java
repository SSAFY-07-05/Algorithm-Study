import java.io.*;
import java.util.*;

public class Main{

    static int N, M, virusCnt;
    static int[][] map, tmp, virusMap;
    static int res;

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_14502.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tmp = new int[N][M];

        virusCnt = 0;
        virusMap = new int[10][2];

        res = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 바이러스 개수 카운트 및 위치 저장
                if (map[i][j] == 2) {
                    virusMap[virusCnt][0] = i;
                    virusMap[virusCnt][1] = j;
                    virusCnt++;
                }
            }
        }
        wall(0);
        System.out.println(res);
    }

    static void wall(int cnt) {

        if (cnt == 3) {
            // 임시배열에 map 값 저장
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tmp[i][j] = map[i][j];
                }
            }
            // 바이러스 퍼트리기
            for (int n = 0; n < virusCnt; n++) {
                virus(virusMap[n][0], virusMap[n][1]);
            }
            // 안전구역 체크
            safeZone();
            return;
        }

        // 벽짓기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    wall(cnt + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static int di[] = {-1, 0, +1, 0}; // 상 우 하 좌
    static int dj[] = {0, +1, 0, -1};

    static void virus(int y, int x) {

        for (int k = 0; k < 4; k++) {
            int dy = y + di[k];
            int dx = x + dj[k];
            if (dy < N && 0 <= dy && dx < M && 0 <= dx && tmp[dy][dx] == 0) {
                tmp[dy][dx] = 2;
                virus(dy, dx);
            }
        }
    }

    static void safeZone() {
        int zeroCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmp[i][j] == 0) zeroCnt++;
            }
        }
        res = Math.max(zeroCnt, res);
    }
}

