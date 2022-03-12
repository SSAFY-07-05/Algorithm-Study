import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) throws Exception {

        //System.setIn(new FileInputStream("res/input_14503.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        // 0 : 북, 1: 동 , 2: 남, 3: 서
        int direction = Integer.parseInt(st.nextToken());

        int map[][] = new int [N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
               
        int cleanCnt = 0;
        
        int[] di = {-1, 0, 1, 0}; // 북 동 남 서
        int[] dj = {0, 1, 0, -1};

  
       here: while(true) {
        	 // 현재 위치 청소
        	if(map[r][c]==0) {
        		cleanCnt++;
        		map[r][c] = 2;
        	}
        	
        	for (int d = 0; d < 4; d++) {
        		
        		int dIdx = (direction+3)%4;
        		int dy = r+di[dIdx];
        		int dx = c+dj[dIdx];
        		
        		// 청소를 이미 했거나 벽인경우
        		if (map[dy][dx]== 2 || map[dy][dx]== 1) {
        			direction = dIdx;
        			continue;
        		// 청소 가능한 경우	
				} else {
					r = dy;
					c = dx;
					direction = dIdx;
					continue here;
					
				}
			}
        	
        	// 청소가 불가능할 경우
    		r = r-di[direction];
    		c = c-dj[direction];
    		
    		// 후진했을때 벽인 경우 탈출
    		if(map[r][c]==1) break;
        	
        }
        System.out.println(cleanCnt);
        br.close();
    }
}
