import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws Exception {

        // System.setIn(new FileInputStream("res/input_1916.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        List<int[]> list[] = new List[N+1];
        int distance[] = new int [N+1];
        boolean v[] = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<int[]>();
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new int[] {to, cost});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        distance[start] = 0;

        for (int i = 0; i < N; i++) {
            int min = Integer.MAX_VALUE;
            int current = 0;
            for (int j = 1; j < N+1 ; j++) {
                if(!v[j] && distance[j] < min){
                    min = distance[j];
                    current = j;
                }
            }
            v[current] = true;

            for (int[] j:list[current]) {
                if(!v[j[0]] && distance[j[0]]>distance[current]+j[1]){
                    distance[j[0]]=distance[current]+j[1];
                }
            }
        }
        System.out.println(distance[end]);
    }
}