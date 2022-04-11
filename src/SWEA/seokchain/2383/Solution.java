import java.io.*;
import java.util.*;

public class Solution{

    static class Pos {
        int x;
        int y;
        int on;
        int floor;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Pos(int x, int y, int floor, int on) {
            this.x = x;
            this.y = y;
            this.floor = floor;
            this.on = on;
        }
    }

    static int min;
    static int N;
    static ArrayList<Pos> persons;
    static ArrayList<Pos> stairs;
    static boolean[] visited;
    static int[] floors;
    static boolean[] checks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());

        for (int test = 1; test <= testNum; test++) {
            N = Integer.parseInt(br.readLine());
            persons = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String[] temp = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    int t = Integer.parseInt(temp[j]);
                    if (t == 1)
                        persons.add(new Pos(i, j));
                    else if (t >= 1)
                        stairs.add(new Pos(i, j, t, 0));
                }
            }

            min = Integer.MAX_VALUE;
            for (int i = 0; i <= persons.size() / 2; i++) {
                visited = new boolean[persons.size()];
                comb(0, i, persons.size());
            }

            System.out.println("#" + test + " " + min);
        }
        br.close();
    }

    public static void comb(int start, int r, int num) {
        if (r == 0) {
            // 선택한 계단과의 거리 구하기
            ArrayList<Integer> dist = new ArrayList<>();
            ArrayList<Integer> dist2 = new ArrayList<>();
            boolean[] visited2 = new boolean[num];

            // 계단과의 거리 구하기
            for (int i = 0; i < num; i++) {
                visited2[i] = !visited[i];
                if (visited[i]) {
                    dist.add(Math.abs(persons.get(i).x - stairs.get(0).x) + Math.abs(persons.get(i).y - stairs.get(0).y));
                    dist2.add(Math.abs(persons.get(i).x - stairs.get(1).x) + Math.abs(persons.get(i).y - stairs.get(1).y));
                } else {
                    dist.add(Math.abs(persons.get(i).x - stairs.get(1).x) + Math.abs(persons.get(i).y - stairs.get(1).y));
                    dist2.add(Math.abs(persons.get(i).x - stairs.get(0).x) + Math.abs(persons.get(i).y - stairs.get(0).y));
                }
            }

            // 계단으로 움직이기
            int time = move(dist, num, visited);
            min = min > time ? time : min;

            // 계단을 반대로 이용하기
            time = move(dist2, num, visited2);
            min = min > time ? time : min;

            return;
        }
        for (int i = start; i < num; i++) {
            visited[i] = true;
            comb(i + 1, r - 1, num);
            visited[i] = false;
        }
    }

    public static int move(ArrayList<Integer> dist, int num, boolean[] visited) {
        int cnt = 0;
        int time = 0;

        // 계단을 이용중인 사람 관리
        // queue의 크기가 계단을 이용중인 사람 수를 나타낸다.
        Queue<Integer> q[] = new LinkedList[2];
        q[0] = new LinkedList<>();  // 첫 번째 계단 이용
        q[1] = new LinkedList<>();  // 두 번째 계단 이용


        while (true) {
            // 모두 아래층에 도착한 경우 탈출
            if (cnt == num)
                break;
            // 거리를 감소시키며 계단으로 이동 시키기
            for (int i = 0; i < num; i++) {
                // 계단을 내려가고 있는 중
                if (dist.get(i) < 0)
                    continue;
                // 계단에 도착한 경우
                if (dist.get(i) == 0) {
                    if (visited[i]) {
                        if (q[0].size() < 3) {
                            dist.set(i, -1);
                            q[0].offer(stairs.get(0).floor);
                        }

                    } else {
                        if (q[1].size() < 3) {
                            dist.set(i, -1);
                            q[1].offer(stairs.get(1).floor);
                        }
                    }
                    // 계단에 이미 도착하였기 때문에 거리를 줄이지 않고(움직이지 않고) continue
                    continue;
                }
                dist.set(i, dist.get(i) - 1);
            }

            // 계단에 있는 사람들 이동시키기
            for (int i = 0; i < 2; i++) {
                int size = q[i].size();
                for (int j = 0; j < size; j++) {
                    int floor = q[i].poll();
                    // 내려가야 하는 계단 수가 1이상이면 내려감
                    if (floor > 1)
                        q[i].offer(floor - 1);
                    else
                        // 다 내려왔으면 count 처리
                        cnt++;
                }
            }
            time++;
        }
        return time + 1;
    }
}