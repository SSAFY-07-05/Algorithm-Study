import java.io.*;
import java.util.*;

public class Main {

    static int distance[];
    static boolean v[];
    static List<Node> list[];

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_5972.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        distance = new int[N + 1];
        v = new boolean[N + 1];

        list = new List[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, cost));
            list[to].add(new Node(from, cost));
        }

        distance[1] = 0;
        pq();
        System.out.println(distance[N]);
    }

    public static void pq() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(1, 0));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (!v[cur.no]) v[cur.no] = true;
            else continue;

            for (Node node : list[cur.no]) {
                if (distance[node.no] > distance[cur.no] + node.cost) {
                    distance[node.no] = distance[cur.no] + node.cost;
                    q.offer(new Node(node.no, distance[node.no]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int no;
        int cost;

        Node(int no, int cost) {
            this.no = no;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
