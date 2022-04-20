import java.io.*;
import java.util.*;

public class Main {
    static class Shark{
        int x, y, dir, eatSum;

        Shark() {}

        Shark(int x, int y, int dir, int eatSum){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish implements Comparable<Fish>{
        int x, y, id, dir;
        boolean isAlive = true;

        Fish() {}

        Fish(int x, int y, int id, int dir, boolean isAlive){
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return this.id - o.id;
        }
    }
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for(int i = 0; i<4; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<4; j++){
                Fish f = new Fish();
                f.id = Integer.parseInt(st.nextToken());
                f.dir = Integer.parseInt(st.nextToken()) - 1;
                f.x = i;
                f.y = j;

                fishes.add(f);
                arr[i][j] = f.id;
            }
        }

        Collections.sort(fishes);

        Fish f = fishes.get(arr[0][0] - 1);
        Shark s = new Shark(0, 0, f.dir, f.id);
        f.isAlive = false;
        arr[0][0] = -1;

        DFS(arr, s, fishes);
        System.out.println(maxSum);

        br.close();
    }

    static void DFS(int[][] arr, Shark shark, List<Fish> fishes){
        if(maxSum < shark.eatSum){
            maxSum = shark.eatSum;
        }

        fishes.forEach(e -> moveFish(e, arr, fishes));

        for(int dist = 1; dist < 4; dist++){
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);

                arrCopies[shark.x][shark.y] = 0;
                Fish f = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1;

                DFS(arrCopies, newShark, fishCopies);
            }
        }
    }

    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes){
        if(!fish.isAlive) return;

        for(int i = 0; i<8; i++){
            int nextDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;

                if(arr[nx][ny] == 0){
                    fish.x = nx;
                    fish.y = ny;
                } else{
                    Fish tmp = fishes.get(arr[nx][ny] - 1);
                    tmp.x = fish.x;
                    tmp.y = fish.y;
                    arr[fish.x][fish.y] = tmp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.dir = nextDir;
                return;
            }
        }
    }

    static int[][] copyArr(int[][] arr){
        int[][] tmp = new int[4][4];

        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }

    static List<Fish> copyFishes(List<Fish> fishes){
        List<Fish> tmp = new ArrayList<>();
        fishes.forEach(e -> tmp.add(new Fish(e.x, e.y, e.id, e.dir, e.isAlive)));
        return tmp;
    }
}