import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H, min=Integer.MAX_VALUE;
    static int[][] grid;
    static int[] cdx = {-2,-1,2,1,-2,-1,2,1};   // 체스 나이트 이동 방향
    static int[] cdy = {-1,-2,-1,-2,1,2,1,2};
    static int[] dx = {0, 1, 0, -1};            // 상 우 하 좌
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][][] visit;

    static class Node{
        int x, y, cnt, k;

        public Node(int x, int y, int cnt, int k){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        visit = new boolean[H][W][K+1];
        grid = new int[H][W];
        for(int i = 0; i<H; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<W; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = BFS(0, 0);

        if(min==Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(min);
    }

    public static int BFS(int x, int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0, K));
        visit[x][y][K] = true;

        while (!q.isEmpty()){
            Node c = q.poll();
            if(c.x == H-1 && c.y == W-1) return c.cnt;

            for(int d = 0; d<4; d++){
                int nx = c.x + dx[d];
                int ny = c.y + dy[d];
                if(nx>=0 && ny>=0 && nx<H && ny<W && !visit[nx][ny][c.k] && grid[nx][ny]==0){
                    visit[nx][ny][c.k] = true;
                    q.offer(new Node(nx, ny, c.cnt + 1, c.k));
                }
            }

            if(c.k > 0){
                for(int d = 0; d<8; d++){
                    int nx = c.x + cdx[d];
                    int ny = c.y + cdy[d];
                    if(nx>=0 && ny>=0 && nx<H && ny<W && !visit[nx][ny][c.k-1] && grid[nx][ny]==0){
                        visit[nx][ny][c.k - 1] = true;
                        q.offer(new Node(nx, ny, c.cnt + 1, c.k - 1));
                    }
                }
            }
        }
        return min;
    }
}
