import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[][] players;
    static boolean[] select;
    static int[] lineUp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        players = new int[N+1][10];

        for(int i = 1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j<=9; j++){
                players[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select = new boolean[10];
        lineUp = new int[10];

        select[4] = true;
        lineUp[4] = 1;

        result = 0;
        perm(2);

        sb.append(result).append("\n");
        System.out.println(sb);
        br.close();
    }

    static void perm(int num){
        if(num == 10){
            play();
            return;
        }

        for(int i = 1; i<=9; i++){
            if(select[i]) continue;

            select[i] = true;
            lineUp[i] = num;
            perm(num + 1);
            select[i] = false;
        }
    }

    static void play(){
        int score = 0;
        int startP = 1;
        boolean[] base;

        for(int i = 1; i<=N; i++){
            int out = 0;
            base = new boolean[4];

            go: while(true){
                for(int j = startP; j<=9; j++){
                    int  hitP = players[i][lineUp[j]];

                    switch (hitP){
                        case 0:
                            out++;
                            break;
                        case 1:
                            for(int k = 3; k>=1; k--){
                                if(base[k]){
                                    if(k == 3){
                                        score++;
                                        base[k] = false;
                                        continue;
                                    }

                                    base[k] = false;
                                    base[k + 1] = true;
                                }
                            }
                            base[1] = true;
                            break;
                        case 2:
                            for(int k = 3; k>=1; k--){
                                if(base[k]){
                                    if(k == 3 || k == 2){
                                        score++;
                                        base[k] = false;
                                        continue;
                                    }

                                    base[k] = false;
                                    base[k+2] = true;
                                }
                            }
                            base[2] = true;
                            break;
                        case 3:
                            for(int k = 3; k>=1; k--){
                                if(base[k]){
                                    score++;
                                    base[k] = false;
                                }
                            }

                            base[3] = true;
                            break;
                        case 4:
                            for(int k = 1; k<=3; k++){
                                if(base[k]){
                                    score++;
                                    base[k] = false;
                                }
                            }
                            score++;
                            break;
                    }

                    if(out == 3){
                        startP = j + 1;
                        if(startP == 10) startP = 1;
                        break go;
                    }
                }
                startP = 1;
            }
        }
        result = Math.max(result, score);
    }
}
