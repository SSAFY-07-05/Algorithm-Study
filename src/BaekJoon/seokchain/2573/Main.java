import java.io.*;
import java.util.*;

public class Main{

    static int N, M;
    static int map[][], copyMap[][];
    static boolean v[][];
    static int ni[] = {1, -1, 0, 0};
    static int nj[] = {0, 0, 1, -1};

    static Queue<Pos> q;

    public static void main(String[] args) throws Exception{

        //System.setIn(new FileInputStream("res/input_2573.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]!=0) q.offer(new Pos(i, j, map[i][j]));
            }
        }
        int res = 0;

        while (true){

            copyMap = new int[N][M];
            v = new boolean[N][M];
            bfs(); // 1년후
            copy();
            res ++;
            if(q.size()==0){
                res = 0;
                break;
            }
            if(cntArea())break; //  덩어리 세기
        }
        System.out.println(res);
    }

    static void bfs() {
        int size = q.size();
        for (int i = 0; i < size; i++) {
            Pos pos = q.poll();
            int num = pos.cnt;
            for (int d = 0; d < 4; d++) {
                int di = pos.i + ni[d];
                int dj = pos.j + nj[d];
                if(map[di][dj] == 0){
                    num--;
                }
            }
            if(num <0) num = 0;
            copyMap[pos.i][pos.j] = num;
            if(num !=0 ) q.offer(new Pos(pos.i, pos.j, num));
        }
    }

    static boolean cntArea() {
        int areaCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!v[i][j] && map[i][j] != 0) {
                    dfs(i, j);
                    areaCnt++;
                }
                // 구역 2개 이상 나오면 true 리턴
                if (areaCnt == 2) return true;
            }
        }
        return false;
    }

    static void dfs(int i, int j){
        v[i][j] = true;
        for (int d = 0; d < 4; d++) {
            int di = i+ni[d];
            int dj = j+nj[d];
            if(!v[di][dj] && map[di][dj] != 0) dfs(di, dj);
        }
    }

    static void copy(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }

    static class Pos{
        int i, j, cnt;
        Pos(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}