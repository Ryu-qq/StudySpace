package Inflean.remind;

import java.util.Scanner;

public class 합이같은부분집합 {
    static int n, Lv =0;
    static int[] arr;
    static int[] ch;
    static String answer = "NO";
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        arr = new int[n];
        ch = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }
        DFS(0, 0);
        System.out.println(answer);
    }

    public static String DFS(int num, int Lv){
        if(Lv ==n-1){
            int mSum =0, pSum=0;
            for(int i=0; i<ch.length; i++){
                if(ch[i] ==1) pSum += arr[i];
                else mSum += arr[i];
            }
            if(pSum ==mSum) {
                answer ="YES";
                return answer;
            }
        }else{
            ch[num] =1;
            DFS(num + 1, Lv+1);
            ch[num] =0;
            DFS(num + 1, Lv+1);
        }
        return answer;
    }
}
