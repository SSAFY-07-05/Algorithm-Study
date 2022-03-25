import java.io.*;
import java.util.*;

public class Main {
    static int M, N, K, count, area;
    static int[][] grid;
    static int[] dx = {0, 1, 0,-1}; // 상 우 하 좌
    static int[] dy = {-1, 0, 1, 0}; // 상 우 하 좌

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        grid = new int[M][N];

        for(int i = 0; i<K; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int leftX = Integer.parseInt(st.nextToken());
            int leftY = M - Integer.parseInt(st.nextToken());
            int rightX = Integer.parseInt(st.nextToken());
            int rightY = M - Integer.parseInt(st.nextToken());

            for(int y = rightY; y<leftY; y++){
                for(int x = leftX; x<rightX; x++){
                    grid[y][x] = 1;
                }
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i<M; i++){
            for(int j = 0; j<N; j++){
                if(grid[i][j] == 0){
                    area = 0;
                    DFS(i, j);
                    count++;
                    queue.add(area);
                }
            }
        }

        System.out.println(count);
        while(!queue.isEmpty()){
            System.out.print(queue.poll()+" ");
        }
        br.close();
    }

    static void DFS(int y, int x){
        grid[y][x] = 1;
        area++;

        for(int d = 0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(0<=nx && nx<N && 0<=ny && ny<M && grid[ny][nx]==0)
                DFS(ny, nx);
        }
    }
}