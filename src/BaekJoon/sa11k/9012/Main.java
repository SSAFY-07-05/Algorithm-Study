import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        next : for(int i = 0; i<T; i++){
            String ps = br.readLine();
            Stack<String> stack = new Stack<>();
            for(int j = 0; j<ps.length(); j++){
                if(ps.charAt(j)=='('){
                    stack.push("( ");
                }
                else if(ps.charAt(j)==')') {
                    if (stack.isEmpty()) {
                        System.out.println("NO");
                        continue next;
                    } else {
                        if (stack.pop().equals(")")) {
                            System.out.println("NO");
                            continue next;
                        }
                    }
                }
            }
            if(!stack.isEmpty()){
                System.out.println("NO");
            }else{
                System.out.println("YES");
            }
        }

    }
}
