import java.io.*;
import java.util.*;

public class Main {
    static int M, N, result;			// 세로 크기, 가로 크기, 경로의 개수
    static int[][] grid, dp;			// 산의 지형, 내용 저장
    static int[] dx = {0, 1, 0, -1};	// 상 우 하 좌
    static int[] dy = {-1, 0, 1, 0};	// 상 우 하 좌

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());	// 세로 크기
        N = Integer.parseInt(st.nextToken());	// 가로 크기
        grid = new int[M][N];					// 산의 지형
        dp = new int[M][N];                     // 내용 저장

        for(int i = 0; i<M; i++) {				// 산의 지형 입력
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j<N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        result = DFS(0, 0);								// 경로 탐색

        sb.append(result);

        System.out.println(sb);		//출력
        br.close();
    }

    static int DFS(int i, int j) {
        if(i==M-1 && j==N-1) {		// 끝지점에 도달했을 때
            return 1;					// 탐색 끝
        }

        if(dp[i][j] != -1) return dp[i][j];

        else{
            dp[i][j] = 0;
            for(int d = 0; d<4; d++) {	// 4방 탐색
                int nx = j + dx[d];
                int ny = i + dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;	// 진행할 수 없는 지역
                if(grid[i][j]>grid[ny][nx]) {	// 다음 이동할 좌표가 현재 지형값보다 작으면(내리막길이면)
                    dp[i][j] += DFS(ny, nx);		// 다음으로 탐색
                }
            }
        }
        return dp[i][j];
    }
}