import java.io.*;
import java.util.*;

public class Main_bj_1600 {

    static int[] ni = {-1, 0, 1, 0}; // 상 우 하 좌
    static int[] nj = {0, 1, 0, -1};
    static int[] hi = {-2, -2, 2, 2, 1, -1, 1, -1};
    static int[] hj = {1, -1, 1, -1, 2, 2, -2, -2};

    static int K, N, M;
    static int map[][];
    static boolean v[][][];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_1600.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[M][N][K + 1];
        int res = bfs(0, 0);
        System.out.println(res);

    }

    static int bfs(int x, int y) {
        int min = Integer.MAX_VALUE;
        Queue<Node> q = new ArrayDeque<>();
        v[0][0][0] = true;
        q.offer(new Node(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.x == M - 1 && n.y == N - 1) {
                min = Math.min(n.cnt, min);
                return min;
            }

            // 원숭이처럼 움직이는 경우 -> 4방
            for (int d = 0; d < 4; d++) {
                int di = n.x + ni[d];
                int dj = n.y + nj[d];
                if (di < M && di >= 0 && dj < N && dj >= 0 && map[di][dj] == 0 && !v[di][dj][n.k]) {
                    v[di][dj][n.k] = true;
                    q.offer(new Node(di, dj, n.cnt + 1, n.k));
                }
            }

            // 이미 말처럼 움직일 횟수를 모두 쓴 원숭이라면, 말처럼 움직이지 못함
            if (n.k == K) continue;

            // 말처럼 움직이는 경우 -> 8방
            for (int d = 0; d < 8; d++) {
                int di = n.x + hi[d];
                int dj = n.y + hj[d];
                if (di < M && di >= 0 && dj < N && dj >= 0 && map[di][dj] == 0 && !v[di][dj][n.k + 1]) {
                    v[di][dj][n.k + 1] = true;
                    q.offer(new Node(di, dj, n.cnt + 1, n.k + 1));
                }
            }
        }
        return -1;
    }

    static class Node {
        int x;
        int y;
        int cnt;
        int k;

        public Node(int x, int y, int cnt, int k) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }
}
