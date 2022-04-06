import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[101][101];
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());

            dragonCurve(x, y, dir, gen);
        }

        resultCount();

        System.out.println(result);

        br.close();
    }
    static void dragonCurve(int x, int y, int dir, int gen){
        ArrayList<Integer> list = new ArrayList<Integer>();

        int direction = dir;
        list.add(direction);
        for(int i = 1; i<=gen; i++){
            for(int j = list.size()-1; j>=0; j--){
                direction = (list.get(j) + 1) % 4;
                list.add(direction);
            }
        }

        int tmpX = x;
        int tmpY = y;

        for(int i = 0; i<list.size(); i++){
            map[tmpY][tmpX] = 1;
            tmpX += dx[list.get(i)];
            tmpY += dy[list.get(i)];
        }

        map[tmpY][tmpX] = 1;
    }

    static void resultCount(){
        int[] dx = {1, 0, 1};
        int[] dy = {0, 1, 1};
        int check = 0;
        for(int y = 0; y<map.length; y++){
            for(int x = 0; x<map[0].length; x++){
                if(map[y][x] == 1){
                    for(int d = 0; d<3; d++){
                        int tmpY = y + dy[d];
                        int tmpX = x + dx[d];

                        if(tmpY>=0 && tmpX>=0 && tmpY<101 && tmpX<101 && map[tmpY][tmpX]==1){
                            check++;
                        }
                    }
                    if(check == 3){
                        result++;
                    }
                    check = 0;
                }
            }
        }
    }
}
