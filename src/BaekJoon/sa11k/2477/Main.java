import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int N = 6;
        int[][] location = new int[N][2];    // 받아온 내용을 저장할 배열(0 : 방향, 1: 값)

        int max = Integer.MIN_VALUE;         // 큰 사각형 넓이
        int min = Integer.MAX_VALUE;        // 작은 사각형 넓이
        int maxIdx = -1;

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            location[i][0] = Integer.parseInt(st.nextToken());     // 방향
            location[i][1] = Integer.parseInt(st.nextToken());     // 값
        }

        // 넓이가 최대가 되는 인덱스를 구해줌 (넓이가 최대면 큰 사각형을 구할 수 있음)
        for(int i = 0; i<N; i++){
            if(i == N-1) {
                if(location[i][1] * location[0][1]>max){
                    max = location[i][1] * location[0][1];
                    maxIdx = 0;
                }
            }
            else{
                if(location[i][1] * location[i+1][1]>max){
                    max = location[i][1] * location[i+1][1];
                    maxIdx = i+1;
                }
            }
        }

        // 작은 사각형 구하기
        // 반시계 방향으로 돌고 있기 때문에 가장 긴 변이 연속되면(넓이가 최대임 - 이때의 인덱스를 위에서 구해줬음)
        // 다다음 인덱스와 그 다음 인덱스의 값이 작은 사각형의 변!
        if(maxIdx + 3 < N){
            min = location[maxIdx+2][1] * location[maxIdx+3][1];
        }else{
            if(maxIdx + 2 < N){
                min = location[maxIdx+2][1] * location[maxIdx+3-N][1];
            }else{
                min = location[maxIdx+2-N][1] * location[maxIdx+3-N][1];
            }
        }

        // 참외 개수 구하기 (1m^2의 넓이에 자라는 참외 개수 * 넓이(큰 넓이 - 작은 넓이))
        int result = num * (max - min);

        System.out.println(result);

    }
}
