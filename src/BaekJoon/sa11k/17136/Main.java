import java.io.*;
import java.util.*;

public class Main {
    static int[][] grid = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i<10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<10; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);

        if(result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);

        br.close();
    }

    static void DFS(int idx, int cnt){
        if(idx == 100){
            result = Math.min(result, cnt);
            return;
        }
        if(result <= cnt) return;

        int i = idx / 10;
        int j = idx % 10;

        if(grid[i][j] == 1){
            for(int d = 5; d>0; d--){
                if(paper[d] > 0){
                    if(canAttach(i, j, d)){
                        // 붙이기
                        attach(i, j, d, 0);
                        paper[d]--;
                        DFS(idx+1, cnt+1);
                        // 떼기
                        attach(i, j, d, 1);
                        paper[d]++;
                    }
                }
            }
        }
        else
            DFS(idx+1, cnt);
    }

    static boolean canAttach(int i, int j, int size){
        if(i+size>10 || j+size>10) return false;
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                if(grid[y+i][x+j] != 1)
                    return false;
            }
        }
        return true;
    }

    static void attach(int i, int j, int size, int how){
        // how - 0: 붙이기, 1: 떼기
        for(int y=0; y<size; y++){
            for(int x=0; x<size; x++){
                grid[y+i][x+j] = how;
            }
        }
    }
}