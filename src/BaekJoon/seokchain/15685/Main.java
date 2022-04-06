import java.io.*;
import java.util.*;

public class Main{

    static int x , y , d ,g;
    static boolean map[][];

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_15685.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        map = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken()); // 방향
            g = Integer.parseInt(st.nextToken()); // 세대
            curve();
        }

        int res = 0;

        // 사각형 체크
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if(map[i][j] && map[i][j+1] && map[i+1][j+1] && map[i+1][j]) res++;
            }
        }

        System.out.println(res);
        br.close();
    }

    static int ni[] = {0, -1, 0, 1};
    static int nj[] = {1, 0, -1, 0};

    static void curve(){
        ArrayList<Integer> list = new ArrayList<>();
        map[x][y] = true;
        list.add(d);

        while(g!=0) {
            for (int i = list.size()-1; i>=0; i--) {
                int dir = (list.get(i) + 1) % 4;
                list.add(dir);
            }
            g--;
        }

        for (int i = 0; i<list.size(); i++) {
            int dir = list.get(i);
            x += ni[dir];
            y += nj[dir];
            map[x][y] = true;
        }
    }
}
