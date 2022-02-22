import java.util.*;
import java.io.*;

public class Main {
    static int[][] bingo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        bingo = new int[5][5];

        for(int i = 0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<5; j++){
                bingo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] callBingo = new int[25];
        int idx = 0;
        for(int i = 0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<5; j++){
                callBingo[idx++] = Integer.parseInt(st.nextToken());
            }
        }

        for(int bin = 0; bin<25; bin++){
            label : for(int i = 0; i<5; i++){
                for(int j = 0; j<5; j++){
                    if(bingo[i][j] == callBingo[bin]){
                        bingo[i][j] = 0;
                        break label;
                    }
                }
            }

            if(check()){
                System.out.println(bin+1);
                break;
            }
        }
        br.close();
    }

    static boolean check(){
        int count = 0;
        int sum = 0;

        // 가로
        for(int i = 0; i<5; i++){
            sum = 0;
            for(int j = 0; j<5; j++){
                sum += bingo[i][j];
            }
            if(sum == 0) count++;
        }

        // 세로
        for(int j = 0; j<5; j++){
            sum = 0;
            for(int i = 0; i<5; i++){
                sum += bingo[i][j];
            }
            if(sum == 0) count++;
        }

        // 왼쪽 대각선
        sum = 0;
        for(int i = 0; i<5; i++){
            sum += bingo[i][i];
        }
        if(sum == 0){
            count++;
        }

        // 오른쪽 대각선
        sum = 0;
        for(int i = 0; i<5; i++){
            sum += bingo[i][4-i];
        }
        if(sum == 0) {
            count++;
        }


        if(count >= 3)
            return true;

        return false;
    }
}
