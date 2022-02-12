import java.io.*;
import java.util.*;

public class Main {
    static int[][] dice;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dice = new int[N][6];
        int nowMax = 0, resultMax = 0;    // 첫번째 주사위 윗면 값, 현재 턴에서 가장 큰 옆면 합, 최종 최대값

        // 주사위 값 받아오기
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<6; j++){
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫번째 주사위에서 윗면 값 고르고, 옆면 중 최대값 구하기
        for(int i = 0; i<6; i++){
            nowMax = 0;
            int Top = dice[0][i];
            int Bottom = dice[0][oppositeNum(i)];;
//            System.out.println(Top);
//            System.out.println(Bottom);

            // 첫번째 주사위에서 옆면의 수 중 가장 큰 수 구하기
            nowMax += findMax(0, i, oppositeNum(i));
//            System.out.println("nowMax : " + nowMax);
//            System.out.println();

            next : for(int j = 1; j<N; j++){
                for(int k = 0; k<6; k++){
                    if(dice[j][k] == Top){
                        Bottom = dice[j][k];
//                        System.out.println("Bottom : " + Bottom);
                        Top = dice[j][oppositeNum(k)];
//                        System.out.println("Top : " + Top);
//                        System.out.println("findMax : " + findMax(j, oppositeNum(k), k));
                        nowMax += findMax(j, oppositeNum(k), k);
//                        System.out.println("nowMax : " + nowMax);
                        continue next;
                    }
                }
//                System.out.println();
            }
            if(resultMax<nowMax) resultMax = nowMax;
//            System.out.println("resultMax : " + resultMax);
        }
        System.out.println(resultMax);
    }

    static int oppositeNum(int index){
        int nextTop = 0;
        switch (index){
            case 0 :
                nextTop = 5;
                break;
            case 1 :
                nextTop = 3;
                break;
            case 2 :
                nextTop = 4;
                break;
            case 3 :
                nextTop = 1;
                break;
            case 4 :
                nextTop = 2;
                break;
            case 5 :
                nextTop = 0;
                break;
        }
        return nextTop;
    }

    static int findMax(int diceIdx, int Top, int Bottom){
        int nowMax = 0;
        for(int j = 0; j<6; j++){
            if(j == Bottom || j == Top){
                continue;
            }else{
                if(nowMax<dice[diceIdx][j]){
                    nowMax = dice[diceIdx][j];
                }
            }
        }
        return nowMax;
    }
}
