import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String state = "";
        for(int tc = 1; tc<=4; tc++){
            int[][] box = new int[4][2];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i<4; i++){
                box[i][0] = Integer.parseInt(st.nextToken());
                box[i][1] = Integer.parseInt(st.nextToken());
            }

            // 공통부분이 없음
            if(box[2][0]>box[1][0] || box[0][0]>box[3][0] || box[2][1]>box[1][1] || box[0][1]>box[3][1]) state = "d";
            // 점
            else if((box[2][0]==box[1][0]&&box[2][1]==box[1][1])
                    || (box[2][0]==box[1][0]&&box[0][1]==box[3][1])
                    || (box[0][0]==box[3][0]&&box[0][1]==box[3][1])
                    || (box[0][0]==box[3][0]&&box[1][1]==box[2][1])) state = "c";
            // 선분
            else if((box[2][0]==box[1][0]&&box[2][1]!=box[1][1])
                    || (box[2][0]!=box[1][0]&&box[0][1]==box[3][1])
                    || (box[0][0]==box[3][0]&box[0][1]!=box[3][1])
                    || (box[0][0]!=box[3][0]&box[1][1]==box[2][1])) state = "b";
            // 직사각형
            else state = "a";

            System.out.println(state);
        }

        br.close();
    }
}