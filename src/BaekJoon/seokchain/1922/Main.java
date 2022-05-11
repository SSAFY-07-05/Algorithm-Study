import java.io.*;
import java.util.*;

public class Main {

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N;
    static int[] parents;
    static Edge[] edgeList;

    // 단위집합 생성
    public static void makeSet() {
        parents = new int[N+1];
        // 자신의 부모노드를 자신의 값으로 세팅
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    // a의 집합 찾기 : a의 대표자 찾기
    public static int findSet(int a) {
        if (a == parents[a])
            return a;
        return parents[a] = findSet(parents[a]); // path compression
    }

    // a,b 두 집합 합치기
    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("res/input_1922.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        edgeList = new Edge[E];

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edgeList); // 간선비용 오름차순 정렬
        makeSet();

        int result = 0, cnt = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                result += edge.weight;
                if (++cnt == N - 1)
                    break;
            }
        }
        System.out.println(result);
    }

}
