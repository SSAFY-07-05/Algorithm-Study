import java.io.*;
import java.util.*;

public class Main {

    static int N, map[][], sharki, sharkj, sharkCnt, sharkSec, sharkSize;
    static boolean v[][];
    static int ni[] = {-1, 0, 0, +1}; // 상, 좌, 우, 하
    static int nj[] = {0, -1, +1, 0};
    static boolean flag = false;

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_16236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharki = i;
                    sharkj = j;
                    sharkSec = 0;
                    sharkCnt = 0;
                    sharkSize = 2;
                    map[i][j] = 0;
                }
            }
        }
        while (!flag) {
            bfs();
        }
        System.out.println(sharkSec);
        br.close();
    }

    static void bfs() {
        v = new boolean[N][N];
        List<Fish> list = new ArrayList<>();
        Queue<Shark> q = new LinkedList<>();
        q.offer(new Shark(sharki, sharkj, sharkCnt, sharkSec, sharkSize));

        while (!q.isEmpty()) {

            Shark shark = q.poll();

            if (map[shark.i][shark.j] != 0 && map[shark.i][shark.j] < sharkSize) {
                int dis = shark.sec - sharkSec;
                if(list.size() != 0 && list.get(0).dis < dis) break;
                list.add(new Fish(shark.i, shark.j, shark.sec, dis));
            }

            for (int d = 0; d < 4; d++) {
                int di = shark.i + ni[d];
                int dj = shark.j + nj[d];
                if (di < N && di >= 0 && dj < N && dj >= 0 && !v[di][dj] && map[di][dj] <= sharkSize) {
                    v[di][dj] = true;
                    q.offer(new Shark(di, dj, shark.cnt, shark.sec + 1, sharkSize));
                }
            }
        }

        if(list.size() !=0) {
            int tmpi = list.get(0).i;
            int tmpj = list.get(0).j;
            int tmpsec = list.get(0).sec;

            for (int i = 1; i < list.size(); i++) {

                if (tmpi != list.get(i).i) {
                    if (tmpi > list.get(i).i) {
                        tmpi = list.get(i).i;
                        tmpj = list.get(i).j;
                    }
                } else { //tmpi == list.get(i).i
                    if (tmpj > list.get(i).j) {
                        tmpi = list.get(i).i;
                        tmpj = list.get(i).j;
                    }
                }
            }
            sharki = tmpi;
            sharkj = tmpj;
            sharkSec = tmpsec;
            sharkCnt++;
            if (sharkCnt == sharkSize) {
                sharkCnt = 0;
                sharkSize++;
            }
            map[tmpi][tmpj] = 0;
        } else flag = true;
    }

    static class Shark {

        int i, j, cnt, sec, size;

        Shark(int i, int j, int cnt, int sec, int size) {
            this.i = i;
            this.j = j;
            this.sec = sec;
            this.cnt = cnt;
            this.size = size;
        }
    }

    static class Fish {

        int i, j, sec, dis;

        Fish(int i, int j, int sec, int dis) {
            this.i = i;
            this.j = j;
            this.sec = sec;
            this.dis = dis;
        }
    }
}