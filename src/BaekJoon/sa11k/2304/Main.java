import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] location = new int[N][2];
        int maxY = 0, maxIdx = 0, nowY = 0, nowX = 0;
        int result = 0;

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            location[i][0] = Integer.parseInt(st.nextToken());
            location[i][1] = Integer.parseInt(st.nextToken());
        }

        // 2차원 배열 오름차순 정렬
        Arrays.sort(location, Comparator.comparingInt(o1 -> o1[0]));

        // 최대 Y 값을 가진 기둥의 위치 찾기
        for(int i = 0; i<N; i++){
            if(maxY<location[i][1]){
                maxIdx = i;
                maxY = location[i][1];
            }
        }

        // 최고 높이의 기둥 전까지의 좌표 구하기
        for(int i = 0; i<=maxIdx; i++){
            if(nowY<=location[i][1]){
                result += (location[i][0]-nowX) * nowY;
                nowX = location[i][0];
                nowY = location[i][1];
            }
        }

        nowX = 0;
        nowY = 0;

        // 최고 높이의 기둥 뒷 부분은 x 좌표를 하나씩 늘려줌
        for(int i = N-1; i>=maxIdx; i--){
            location[i][0] += 1;
        }

        // 뒤에서부터 최고 높이의 기둥 이후의 좌표 구하기
        for(int i = N-1; i>=maxIdx; i--){
            if(nowY<=location[i][1]){
                result += (nowX-location[i][0]) * nowY;
                nowX = location[i][0];
                nowY = location[i][1];
            }
        }

        // 최고 높이의 기둥 면적 더해주기
        result += maxY;

        System.out.println(result);
    }
}
