import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        int cut = Integer.parseInt(br.readLine());

        int[] widthNum = new int[cut+1];
        int[] heightNum = new int[cut+1];

        int widthCnt = 0, heightCnt = 0;

        for(int i = 0; i<cut; i++){
            st = new StringTokenizer(br.readLine(), " ");
            switch (Integer.parseInt(st.nextToken())){
                case 0:
                    widthNum[widthCnt++] = Integer.parseInt(st.nextToken());
                    break;
                case 1:
                    heightNum[heightCnt++] = Integer.parseInt(st.nextToken());
                    break;
            }
        }

        widthNum[widthCnt] = height;
        heightNum[heightCnt] = width;

        Arrays.sort(widthNum);
        Arrays.sort(heightNum);

        int[] resultNum = new int[(widthCnt+1)*(heightCnt+1)+1];
        int resultCnt = 0;

        for(int i = 0; i<=cut; i++){
            if(widthNum[i] == 0)
                continue;
            for(int j = 0; j<=cut; j++){
                if(heightNum[j] == 0)
                    continue;
                if(i == 0 && j == 0)
                    resultNum[resultCnt++] = widthNum[i] * heightNum[j];
                else if(i == 0)
                    resultNum[resultCnt++] = widthNum[i] * (heightNum[j]-heightNum[j-1]);
                else if(j == 0)
                    resultNum[resultCnt++] = (widthNum[i]-widthNum[i-1]) * heightNum[j];
                else
                    resultNum[resultCnt++] = (widthNum[i]-widthNum[i-1])*(heightNum[j]-heightNum[j-1]);
            }
        }

        Arrays.sort(resultNum);

        System.out.println(resultNum[resultCnt]);

        br.close();
    }
}
