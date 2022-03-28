import java.io.*;
import java.util.*;

public class Main{

    static int rowLen, colLen;
    static int map[][], tmp[][], numCnt[];
    static ArrayList<map> list;

    public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_17140.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken()) -1;
        int c = Integer.parseInt(st.nextToken()) -1;
        int k = Integer.parseInt(st.nextToken());

        map = new int [3][3];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rowLen = 3;
        colLen = 3;
        int res = 0;
        while(true) {
            if(res>100) {
                res = -1;
                break;
            }
            // 조건에 성립하면 break
            if(r< rowLen && c<colLen && map[r][c]==k) break;
            // 임시배열 생성
            tmp = new int [101][101];
            // 행과 열 구분
            if(rowLen >= colLen){
                // R연산
                rightSort();
            } else {
                // C연산
                leftSort();
            }
            res ++;
        }
        System.out.println(res);
    }

    static void rightSort() {

        int maxLen = Integer.MIN_VALUE;
        // 행, 열의 크기만큼
        for (int i = 0; i < rowLen; i++) {
                numCnt = new int [101];
            for (int j = 0; j < colLen; j++) {
                if(map[i][j]!=0) numCnt[map[i][j]]++; // 숫자 카운트
            }

            list = new ArrayList<>();

            for (int j = 1; j < 101; j++) {
                if(numCnt[j] == 0) continue;
                list.add(new map(j, numCnt[j])); // 리스트에 수와 등장 횟수 넣기
            }

            Collections.sort(list); // 정렬

            int k = 0;
            //리스트 사이즈 만큼 반복하면서 임시배열에 정렬한 값 넣기
            for (int j = 0; j < list.size(); j++) {
                tmp[i][k++] = list.get(j).num;
                tmp[i][k++] = list.get(j).len;
            }
            // 리스트 사이즈 *2 한 값으로 가장 큰 열 찾기
            maxLen = Math.max(maxLen, list.size()*2);
        }

        colLen = maxLen;
        map = new int [rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    static void leftSort() {

        int maxLen = Integer.MIN_VALUE;
        // 행, 열의 크기만큼
        for (int i = 0; i < colLen; i++) {
                numCnt = new int [101];
            for (int j = 0; j < rowLen; j++) {
                if(map[j][i]!=0)numCnt[map[j][i]]++;
            }

            list = new ArrayList<>();

            for (int j = 1; j < 101; j++) {
                if(numCnt[j] == 0)continue;
                list.add(new map(j, numCnt[j]));
            }

            Collections.sort(list);

            int k = 0;
            for (int j = 0; j < list.size(); j++) {
                tmp[k++][i] = list.get(j).num;
                tmp[k++][i] = list.get(j).len;
            }

            maxLen = Math.max(maxLen, list.size()*2);
        }

        rowLen = maxLen;
        map = new int [rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                map[i][j] = tmp[i][j];
            }
        }
    }

    static class map implements Comparable<map> {
        int num;
        int len;

        public map(int num, int len){
            this.num = num;
            this.len = len;
        }
        @Override
        public int compareTo(map o) {
             if(this.len == o.len) return this.num - o.num;
             return this.len - o.len;
        }
    }
}
