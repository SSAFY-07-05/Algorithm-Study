import java.io.*;
import java.util.*;

public class Main{

    static int res, map[][];
    static Fish fish[];
    static int ni[] = {-1, -1,  0, +1, +1, +1,  0, -1}; // 상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
    static int nj[] = {0, -1, -1, -1,  0, +1, +1, +1};

    public static void main(String[] args) throws Exception {

        // System.setIn(new FileInputStream("res/input_19236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int [4][4];
        fish = new Fish[17];
        res = 0;

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken())-1;
                fish[map[i][j]] = new Fish(i, j, dir, false);
            }
        }

        int dir = fish[map[0][0]].dir;
        int eat = map[0][0];
        fish[map[0][0]].death = true;
        map[0][0] = -1;

        dfs(0, 0, dir, eat);
        System.out.println(res);
    }

    static void dfs(int i, int j, int dir, int eat){
        res = Math.max(res, eat);

        int tmpMap[][] = new int[4][4];
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                tmpMap[y][x] = map[y][x];
            }
        }

        Fish tmpFish[] = new Fish[17];
        for (int k = 1; k < 17; k++) {
            tmpFish[k] = new Fish(fish[k].i, fish[k].j, fish[k].dir, fish[k].death);
        }

        moveFish();

        for(int k = 1; k < 4; k++) { //4*4 행렬로 1칸, 2칸, 3칸까지 최대로 이동 가능
            int di = i + ni[dir] * k;
            int dj = j + nj[dir] * k;

            //경계를 벗어나지 않고, 물고기가 없는 빈칸이 아닐 경우
            if(di >= 0 && di < 4 && dj >= 0 && dj < 4 && map[di][dj] != 0) {
                int eatFish = map[di][dj];
                int nd = fish[eatFish].dir;
                map[i][j] = 0;
                map[di][dj] = -1;
                fish[eatFish].death = true;

                dfs(di, dj, nd, eat+eatFish);

                fish[eatFish].death = false; // 물고기 상태, 상어의 위치 원래대로 되돌리기
                map[i][j] = -1;
                map[di][dj] = eatFish;
            }
        }

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                map[y][x] = tmpMap[y][x];
            }
        }

        for (int k = 1; k < 17; k++) {
            fish[k] = new Fish(tmpFish[k].i, tmpFish[k].j, tmpFish[k].dir, tmpFish[k].death);
        }
    }

    static void moveFish(){

        for (int i = 1; i < 17; i++) {
            if(fish[i].death) continue;

            int dir = fish[i].dir;
            for (int j = 0; j < 8; j++) {
                dir = dir%8;
                fish[i].dir = dir;

                int di = fish[i].i+ni[dir];
                int dj = fish[i].j+nj[dir];
                if (di< 4 && di>=0 && dj < 4 && dj>= 0 && map[di][dj] != -1) {

                    if (map[di][dj] == 0) {

                        map[fish[i].i][fish[i].j] = 0;
                        fish[i].i = di;
                        fish[i].j = dj;
                        map[di][dj] = i;

                    } else {
                        fish[map[di][dj]].i = fish[i].i;
                        fish[map[di][dj]].j = fish[i].j;
                        map[fish[i].i][fish[i].j] = map[di][dj];

                        fish[i].i = di;
                        fish[i].j = dj;
                        map[di][dj] = i;
                    }
                    break;
                }
                else dir++;
            }
        }
    }

    static class Fish {

        int i, j, dir;
        boolean death;

        Fish(int i, int j, int dir, boolean death){
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.death = death;
        }
    }
}