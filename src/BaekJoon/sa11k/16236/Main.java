import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Deque<int[]> q = new ArrayDeque<>();
    static int[][] grid, dist;
    static List<Fish> fishList = new ArrayList<>();

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Fish{
        int i, j, num;

        public Fish(int i, int j, int num) {
            this.i = i;
            this.j = j;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 9){
                    q.add(new int[] {i, j, 0});
                    grid[i][j] = 0;
                }
            }
        }

        goShark();

        br.close();
    }

    static void goShark(){
        int eat = 0;
        int time = 0;
        int num = 2;
        while(true){
            fishList = new ArrayList<>();
            dist = new int[N][N];

            while(!q.isEmpty()){
                int[] current = q.poll();

                for(int d = 0; d<4; d++){
                    int ni = current[0] + dy[d];
                    int nj = current[1] + dx[d];

                    if(ni>=0 && nj>=0 && ni<N && nj<N && dist[ni][nj]==0 && grid[ni][nj] <= num){
                        dist[ni][nj] = dist[current[0]][current[1]] + 1;
                        q.add(new int[] {ni, nj, dist[ni][nj]});
                        if(1<=grid[ni][nj] && grid[ni][nj]<=6 && grid[ni][nj] < num) fishList.add(new Fish(ni, nj, dist[ni][nj]));
                    }
                }
            }

            if(fishList.isEmpty()){
                System.out.println(time);
                return;
            }

            Fish currentF = fishList.get(0);
            for(int i = 1; i<fishList.size(); i++){
                if(currentF.num > fishList.get(i).num){
                    currentF = fishList.get(i);
                }
                else if(currentF.num == fishList.get(i).num){
                    if(currentF.i > fishList.get(i).i) currentF = fishList.get(i);
                    else if(currentF.i == fishList.get(i).i){
                        if(currentF.j > fishList.get(i).j) currentF = fishList.get(i);
                    }
                }
            }

            time += currentF.num;
            eat++;
            grid[currentF.i][currentF.j] = 0;

            if(eat == num){
                num++;
                eat = 0;
            }
            q.add(new int[] {currentF.i, currentF.j, 0});
        }
    }
}