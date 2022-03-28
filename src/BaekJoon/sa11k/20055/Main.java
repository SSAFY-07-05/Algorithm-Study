import java.io.*;
import java.util.*;

public class Main {
    static int N, K, count = 0;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[N*2];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");

        for(int i = 0; i<N*2; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while(true){
            movebelt();
            count++;
            moveRobot();

            int end = 0;
            for(int i = 0; i<belt.length; i++){
                if(belt[i] == 0) end++;
            }

            if(end >= K){
                System.out.println(count);
                break;
            }
        }

        br.close();
    }

    static void movebelt(){
        int tmp = belt[belt.length-1];
        for(int i = belt.length-2; i>=0; i--){
            belt[i+1] = belt[i];
        }
        belt[0] = tmp;

        for(int i = N-2; i>=0; i--){
            robot[i+1] = robot[i];
        }
        robot[0] = false;
    }

    static void moveRobot(){
        if(robot[N-1]) robot[N-1] = false;

        for(int i = N-2; i>0; i--){
            if(robot[i] && !robot[i+1] && belt[i+1] > 0){
                robot[i+1] = true;
                robot[i] = false;
                belt[i+1] = belt[i+1] - 1;
            }
        }

        if(belt[0] > 0 && !robot[0]){
            robot[0] = true;
            belt[0] = belt[0] - 1;
        }
    }
}