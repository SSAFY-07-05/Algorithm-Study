import java.io.*;
import java.util.*;

public class Main {
    static int N, L, count = 0;
    static int[][] grid;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        grid = new int[N][N];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<N; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        rowcheck();
        colcheck();

        System.out.println(count);

        br.close();
    }

    static void rowcheck(){
         go:for(int i = 0; i<N; i++){
             // 경사로 설치 여부
             boolean[] yes = new boolean[N];
             for(int j = 0; j<N-1; j++){
                 int next = grid[i][j] - grid[i][j+1];

                 if(next > 1 || next < -1) continue go;
                 else if(next == -1){
                     for(int k = 0; k<L; k++){
                         if(j-k < 0 || yes[j-k]) continue go;
                         if(grid[i][j]!=grid[i][j-k]) continue go;
                         yes[j-k] = true;
                    }
                }
                 else if(next == 1){
                     for(int k = 1; k<=L; k++){
                         if(j+k>=N || yes[j+k]) continue go;
                         if(grid[i][j]-1 != grid[i][j+k]) continue go;
                         yes[j+k] = true;
                     }
                 }
            }
             count++;
        }
    }

    static void colcheck(){
        go:for(int j = 0; j<N; j++){
            // 경사로 설치 여부
            boolean[] yes = new boolean[N];
            for(int i = 0; i<N-1; i++){
                int next = grid[i][j] - grid[i+1][j];

                if(next > 1 || next < -1) continue go;
                else if(next == -1){
                    for(int k = 0; k<L; k++){
                        if(i-k < 0 || yes[i-k]) continue go;
                        if(grid[i][j]!=grid[i-k][j]) continue go;
                        yes[i-k] = true;
                    }
                }
                else if(next == 1){
                    for(int k = 1; k<=L; k++){
                        if(i+k>=N || yes[i+k]) continue go;
                        if(grid[i][j]-1 != grid[i+k][j]) continue go;
                        yes[i+k] = true;
                    }
                }
            }
            count++;
        }
    }
}