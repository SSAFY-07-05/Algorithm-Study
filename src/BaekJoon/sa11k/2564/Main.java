import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int X = Integer.parseInt(st.nextToken());   // 가로 길이
        int Y = Integer.parseInt(st.nextToken());   // 세로 길이
        int storeNum = Integer.parseInt(br.readLine());

        int[][] store = new int[storeNum+1][2];
        int result = 0;

        for(int i = 0; i<storeNum+1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            store[i][0] = Integer.parseInt(st.nextToken());
            store[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i<storeNum; i++){
            if(store[storeNum][0] == 1){    // 동근이 위치 : 북쪽
                int left = store[storeNum][1];
                int right = X-left;
                if(store[i][0] == 1)
                    result += Math.abs(left-store[i][1]);
                else if(store[i][0] == 2){
                    int result1 = Y + left + store[i][1];
                    int result2 = Y + right + X - store[i][1];
                    result += Math.min(result1, result2);
                }
                else if(store[i][0] == 3)
                    result += left + store[i][1];
                else if(store[i][0] == 4)
                    result += right + store[i][1];
            }

            else if(store[storeNum][0] == 2){   // 동근이 위치 : 남쪽
                int left = store[storeNum][1];
                int right = X-left;
                if(store[i][0] == 1){
                    int result1 = Y + left + store[i][1];
                    int result2 = Y + right + X - store[i][1];
                    result += Math.min(result1, result2);
                }
                else if(store[i][0] == 2)
                    result += Math.abs(left-store[i][1]);
                else if(store[i][0] == 3)
                    result += left + Y - store[i][1];
                else if(store[i][0] == 4)
                    result += right + Y - store[i][1];
            }

            else if(store[storeNum][0] == 3){   // 동근이 위치 : 서쪽
                int down = store[storeNum][1];
                int up = Y - down;
                if(store[i][0] == 1)
                    result += down + store[i][1];
                else if(store[i][0] == 2)
                    result += up + store[i][1];
                else if(store[i][0] == 3)
                    result += Math.abs(down - store[i][1]);
                else if(store[i][0] == 4){
                    int result1 = X + down + store[i][1];
                    int result2 = X + up + Y - store[i][1];
                    result += Math.min(result1, result2);
                }
           }

            else if(store[storeNum][0] == 4){   // 동근이 위치 : 동쪽
                int down = store[storeNum][1];
                int up = Y - down;
                if(store[i][0] == 1)
                    result += down + X - store[i][1];
                else if(store[i][0] == 2)
                    result += up + X - store[i][1];
                else if(store[i][0] == 3){
                    int result1 = X + down + store[i][1];
                    int result2 = X + up + Y - store[i][1];
                    result += Math.min(result1, result2);
                }
                else if(store[i][0] == 4)
                    result += Math.abs(down-store[i][1]);
            }
        }
        System.out.println(result);
    }
}
