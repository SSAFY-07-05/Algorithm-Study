import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] Bus;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        Bus = new int[N][N];

        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                Bus[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if(Bus[start-1][destination-1]>value){
                Bus[start-1][destination-1] = value;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int checkStart = Integer.parseInt(st.nextToken()) - 1;
        int checkEnd = Integer.parseInt(st.nextToken()) - 1;

        int start = checkStart;
        int[] distance = new int[N];
        boolean[] visit = new boolean[N];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        for(int i = 0; i<N; i++){
            int min = Integer.MAX_VALUE, current = 0;
            for(int j = 0; j<N; j++){
                if(!visit[j] && min > distance[j]){
                    min = distance[j];
                    current = j;
                }
            }
            visit[current] = true;

            for(int j = 0; j<N; j++){
                if(!visit[j] && Bus[current][j] != Integer.MAX_VALUE &&
                    distance[j] > distance[current] + Bus[current][j]){
                    distance[j] = distance[current] + Bus[current][j];
                }
            }

            if(visit[checkEnd]){
                System.out.println(distance[checkEnd]);
                break;
            }
        }

        br.close();
    }
}

/*
7
12
1 2 7
1 5 3
1 6 10
5 2 2
2 6 6
2 3 4
2 4 10
5 7 5
5 4 11
3 4 2
6 4 9
7 4 4
1 3
 */

/*
3
4
1 2 3
1 2 10
1 3 1
2 3 1
1 2
 */