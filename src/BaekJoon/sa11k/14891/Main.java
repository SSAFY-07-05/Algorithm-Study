import java.io.*;
import java.util.*;

public class Main {
    static int GearN, dir, ndir, result;
    static int [][] Gear, turnGear;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Gear = new int[4][8];
        turnGear = new int[4][1];
        result = 0;

        for(int i = 0; i<4; i++){
            String s = br.readLine();
            for(int j = 0; j<8; j++){
                Gear[i][j] = s.charAt(j)-'0';
            }
        }

        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            GearN = Integer.parseInt(st.nextToken()) - 1;
            dir = Integer.parseInt(st.nextToken());
            int nowDir = dir;
            turnGear = new int[4][1];
            turnGear[GearN][0] = nowDir;

            // 오른쪽 탐색
            for(int j = GearN; j<3; j++){
                if(Gear[j][2] != Gear[j+1][6]){
                    if(nowDir == 1) ndir = -1;
                    else ndir = 1;
                    turnGear[j][0] = nowDir;
                    turnGear[j+1][0] = ndir;
                    nowDir = ndir;
                }
                else break;
            }

            // 왼쪽 탐색
            nowDir = dir;
            for(int j = GearN; j>0; j--){
                if(Gear[j][6] != Gear[j-1][2]){
                    if(nowDir == 1) ndir = -1;
                    else ndir = 1;
                    turnGear[j][0] = nowDir;
                    turnGear[j-1][0] = ndir;
                    nowDir = ndir;
                }
                else break;
            }

            for(int j = 0; j<4; j++){
                if(turnGear[j][0] != 0){
                    turn(j, turnGear[j][0]);
                }
            }
        }

        for(int i = 0; i<4; i++){
            if(Gear[i][0] == 1){
                result += Math.pow(2, i);
            }
        }

        System.out.println(result);

        br.close();
    }

    static void turn(int G, int D){
        int[] tmp = new int[8];
        switch (D){
            case 1:
                tmp[0] = Gear[G][7];
                int gearI = 0;
                for(int i = 1; i<8; i++){
                    tmp[i] = Gear[G][gearI];
                    gearI++;
                }
                Gear[G] = tmp;
                break;
            case -1:
                gearI = 1;
                for(int i = 0; i<7; i++){
                    tmp[i] = Gear[G][gearI];
                    gearI++;
                }
                tmp[7] = Gear[G][0];
                Gear[G] = tmp;
                break;
        }
    }

}