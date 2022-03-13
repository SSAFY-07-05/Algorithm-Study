import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K, rCount = 3, cCount = 3, count = 0;
    static int[][] tmp, A = new int[101][101];
    static int[] countA;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 1; i<=3; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j<=3; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            countA = new int[101];
            if(A[R][C] == K) break;
            count++;
            tmp = new int[101][101];
            if(rCount>=cCount) Rchange();
            else Cchange();

            if(count>100){
                count = -1;
                break;
            }
        }

        System.out.println(count);

        br.close();
    }

    static void Rchange(){
        int c = Integer.MIN_VALUE;
        for(int i = 1; i<=rCount; i++){
            countA = new int[101];
            for(int j = 1; j<=cCount; j++){
                countA[A[i][j]]++;
            }

            int maxIdx = 0;
            for(int j = 1; j<101; j++){
                maxIdx = Math.max(maxIdx, countA[j]);
            }

            int index = 1;
            for(int j = 1; j<=maxIdx; j++){
                for(int k = 1; k<101; k++){
                    if(countA[k] == j){
                        tmp[i][index++] = k;
                        tmp[i][index++] = countA[k];
                    }
                }
            }
            c = Math.max(c, index-1);
        }
        A = tmp;
        cCount = c;
    }

    static void Cchange(){
        int r = Integer.MIN_VALUE;
        for(int i = 1; i<=cCount; i++){
            countA = new int[101];
            for(int j = 1; j<=rCount; j++){
                countA[A[j][i]]++;
            }

            int maxIdx = 0;
            for(int j = 1; j<101; j++){
                maxIdx = Math.max(maxIdx, countA[j]);
            }

            int index = 1;
            for(int j = 1; j<=maxIdx; j++){
                for(int k = 1; k<101; k++){
                    if(countA[k] == j){
                        tmp[index++][i] = k;
                        tmp[index++][i] = countA[k];
                    }
                }
            }
            r = Math.max(r, index-1);
        }
        A = tmp;
        rCount = r;
    }
}