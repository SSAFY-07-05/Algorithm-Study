import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, result, sum;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] A;
    static boolean isMove = false;
    static boolean[][] visit;
    static ArrayDeque<int[]> deque;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        result = 0;

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            visit = new boolean[N][N];
            isMove = false;
            changePeople();
            if(!isMove) break;
            result++;
        }
        System.out.println(result);
        br.close();
    }

    static void changePeople(){
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                if(!visit[i][j]){
                    sum = 0;
                    deque = new ArrayDeque<>();
                    DFS(i, j);
                    int people = sum / deque.size();
                    for(int[] change : deque)
                        A[change[0]][change[1]] = people;
                }
            }
        }
    }

    static void DFS(int x, int y){
        deque.add(new int[] {x, y});
        sum += A[x][y];
        visit[x][y] = true;

        for(int d = 0; d<4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(0<=nx && 0 <=ny  && nx<N && ny<N && !visit[nx][ny]){
                if(Math.abs(A[x][y] - A[nx][ny]) >= L && Math.abs(A[x][y] - A[nx][ny]) <= R){
                    isMove = true;
                    DFS(nx, ny);
                }
            }
        }
    }
}