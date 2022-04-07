import java.io.*;
import java.util.*;

public class Solution{
    static int ni[] = {-1, +1, 0, 0}; //상 하 좌 우
    static int nj[] = {0, 0, -1, 1}; //
    static Queue<Node> q;
    static int N, M, K;
    static ArrayList<Node> map[][];

    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("res/input_s_2382.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T ; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken()); // 맵 크기
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 군집 개수
            q = new LinkedList<>();
            map = new ArrayList[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = new ArrayList<>();
                }
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                q.offer(new Node(y, x, cnt , dir));
            }

            for (int i = 0; i < M; i++) {
                bfs();
            }
            System.out.println("#"+tc+" "+count());
        }
    }

    static void bfs(){
        while (!q.isEmpty()){

            Node node = q.poll();

            int d = node.dir;
            int cnt = node.cnt;
            int di = node.i + ni[d-1];
            int dj = node.j + nj[d-1];

            if(di==N-1 || di==0 || dj==N-1 || dj==0){
                cnt = cnt/2;
                if(cnt==0) continue;
                d = changeDir(d);
            }
            map[di][dj].add(new Node(di, dj, cnt, d));
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j].size()==0) continue;
                if(map[i][j].size()==1) q.offer(map[i][j].get(0));
                else {
                    int sumCnt = 0;
                    int max = 0;
                    int dir = 0;
                    int maxi = 0;
                    int maxj = 0;
                    for (int k = 0; k < map[i][j].size(); k++) {
                        Node node = map[i][j].get(k);
                        sumCnt += node.cnt;
                        if(max<node.cnt) {
                            max = node.cnt;
                            dir = node.dir;
                            maxi = node.i;
                            maxj = node.j;
                        }
                    }
                    q.offer(new Node(maxi,maxj,sumCnt,dir));
                }
                map[i][j].clear();
            }
        }
    }

    static int changeDir(int dir){

        switch (dir){
            case 1 : dir = 2; break;
            case 2 : dir = 1; break;
            case 3 : dir = 4; break;
            case 4 : dir = 3; break;
        }
        return dir;
    }

    static int count() {
        int res = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            res += node.cnt;
        }
        return res;
    }

    static class Node{
        int i, j, cnt, dir;
        Node(int i, int j, int cnt, int dir){
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.dir = dir;
        }
    }
}