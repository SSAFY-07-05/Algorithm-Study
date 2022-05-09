import java.io.*;
import java.util.*;

public class Main {

    static int map[][], copyMap[][], numbers[];
    static boolean[][] v;
    static int N, M, res, tmp;
    static List<Pos> vlist;
    static int ni[] = {1,-1,0,0};
    static int nj[] = {0,0,-1,1};
    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_17142.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        res = Integer.MAX_VALUE;
        map = new int[N][N];
        copyMap = new int[N][N];
        vlist = new ArrayList<>();

        int blankCount = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = Integer.parseInt(st.nextToken());
                if (copyMap[i][j] == 2) { // 바이러스
                    vlist.add(new Pos(i, j));
                    copyMap[i][j] = -2;
                } else if(copyMap[i][j] == 1){ // 벽인경우
                    copyMap[i][j] = -3;
                } else if(copyMap[i][j] == 0){
                    blankCount++;
                }
            }
        }
        // 빈칸 0, 비활성 바이러스 -2, 벽 : -3
        numbers = new int[vlist.size()];
        if(blankCount == 0){
            System.out.println(0);
        } else {
            copy();
            combi(0, 0);
            if (res == Integer.MAX_VALUE) res = -1;
            System.out.println(res);
        }
    }

    static void combi(int cnt, int start){
        if(cnt == M){
            bfs();
            if(check())res = Math.min(tmp, res);
            copy();
            return;
        }

        for (int i = start; i < vlist.size(); i++){
            numbers[cnt] = i;
            combi(cnt+1, i+1);
        }
    }

    static void bfs(){
        Queue<Node> q = new LinkedList<>();
        v = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            int idx = numbers[i];
            v[vlist.get(idx).i][vlist.get(idx).j] = true;
            q.offer(new Node(vlist.get(idx).i, vlist.get(idx).j, 0));
        }
        while (!q.isEmpty()){
            Node node = q.poll();
            for (int d = 0; d < 4; d++) {
                int di = node.i + ni[d];
                int dj = node.j + nj[d];
                if(di< N && di >= 0 && dj < N && dj >= 0 && !v[di][dj]){
                    if(map[di][dj]==0) { // 확산하는 경우
                        v[di][dj] = true;
                        map[di][dj] = node.cnt + 1;
                        tmp = node.cnt + 1;
                        q.offer(new Node(di, dj, node.cnt + 1));
                    }
                    if(map[di][dj]==-2){ // 통과만 하는경우
                        v[di][dj] = true;
                        q.offer(new Node(di, dj, node.cnt + 1));
                    }
                }
            }
        }
    }
    static boolean check(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0) return false;
            }
        }
        return true;
    }
    static void copy(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = copyMap[i][j];
            }
        }
    }
    static class Pos {
        int i, j;
        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
    static class Node{
        int i, j, cnt;
        Node(int i, int j, int cnt){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
        }
    }
}

