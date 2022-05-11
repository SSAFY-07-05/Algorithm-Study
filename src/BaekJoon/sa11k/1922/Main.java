import java.io.*;
import java.util.*;

public class Main {
    static int N, M, parent[];
    static ArrayList<Edge> edgeList;
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return weight - o.weight;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edgeList = new ArrayList<>();
        for(int i = 0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            edgeList.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        parent = new int[N+1];

        for(int i = 1; i<=N; i++){
            parent[i] = i;
        }

        Collections.sort(edgeList);

        int result = 0;
        for(int i = 0; i<edgeList.size(); i++){
            Edge e = edgeList.get(i);

            if(find(e.start) != find(e.end)){
                result += e.weight;
                union(e.start, e.end);
            }
        }

        System.out.println(result);
        br.close();
    }

    static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y)
            parent[y] = x;
    }
}