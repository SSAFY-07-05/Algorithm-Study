import java.io.*;
import java.util.*;

public class Main {
    static class Virus{
        int x, y, time;

        Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int N, M, result = Integer.MAX_VALUE, originEmpty = 0;
    static int[][] arr;
    static List<Virus> viruses = new ArrayList<>();
    static Virus[] active;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        active = new Virus[M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0)
                    originEmpty++;
                else if(arr[i][j] == 2)
                    viruses.add(new Virus(i, j, 0));
            }
        }

        if(originEmpty == 0)
            System.out.println(0);
        else{
            selectVirus(0, 0);
            System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }
        br.close();
    }

    static void selectVirus(int start, int cnt){
        if(cnt == M){
            spreadVirus(originEmpty);
            return;
        }

        for(int i = start; i<viruses.size(); i++){
            active[cnt] = viruses.get(i);
            selectVirus(i+1, cnt+1);
        }
    }

    static void spreadVirus(int empty) {
        Deque<Virus> q = new ArrayDeque<>();
        boolean[][] infected = new boolean[N][N];

        for(int i = 0; i<M; i++){
            Virus v = active[i];
            infected[v.x][v.y] = true;
            q.add(v);
        }

        while (!q.isEmpty()){
            Virus v = q.poll();

            for(int d = 0; d<4; d++){
                int nx = v.x + dx[d];
                int ny = v.y + dy[d];

                if(nx<0 || nx>=N || ny<0 || ny>=N || infected[nx][ny] || arr[nx][ny]==1)
                    continue;

                if(arr[nx][ny] == 0)
                    empty--;

                if(empty == 0){
                    result = Math.min(result, v.time + 1);
                    return;
                }

                infected[nx][ny] = true;
                q.add(new Virus(nx, ny, v.time+1));
            }
        }
    }
}