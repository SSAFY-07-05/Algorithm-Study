import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        ArrayList<Integer> result = new ArrayList<>();

        for(int n = num/2; n<=num; n++){
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(num);
            arr.add(n);
            while(arr.get(arr.size()-2) - arr.get(arr.size()-1) >= 0) {
                arr.add(arr.get(arr.size() - 2) - arr.get(arr.size() - 1));
            }

            if(arr.size() > result.size()){
                result.clear();
                for(int j = 0; j<arr.size(); j++){
                    result.add(arr.get(j));
                }
            }
        }

        System.out.println(result.size());
        for(int i = 0; i< result.size(); i++){
            System.out.print(result.get(i)+" ");
        }
        System.out.println();

        br.close();
    }
}