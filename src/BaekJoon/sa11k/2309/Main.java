import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] realDwarf, dwarf, printdwarf;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printdwarf = new int[7];
        realDwarf = new int[7];
        dwarf = new int[9];

        for(int i = 0; i<9; i++){
            dwarf[i] = sc.nextInt();
        }

        find(0,0);
        Arrays.sort(printdwarf);

        for(int i = 0; i<7; i++){
            System.out.println(printdwarf[i]);
        }

        sc.close();
    }

    public static void find(int cnt, int start){
        if(cnt==7) {
            int sum = 0;
            for(int i = 0; i<7; i++){
                sum += realDwarf[i];
            }

            if(sum == 100){
                for(int j = 0; j<7; j++){
                    printdwarf[j] = realDwarf[j];
                }
            }
            return;
        }

        for(int i = start; i<9; i++){
            realDwarf[cnt] = dwarf[i];
            find(cnt+1, i+1);
        }
    }
}
