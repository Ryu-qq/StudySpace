package Inflean.remind;

import java.util.Scanner;

public class 바둑이승차 {

    static int limitKg, n, answer=0;
    static int[] arr, ch;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        limitKg = in.nextInt();
        n = in.nextInt();

        arr = new int[n];
        ch =  new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }
        DFS(0, 0, 0);
        System.out.println(answer);
    }

    public static void DFS(int num, int Lv, int sum){
        if(limitKg < sum) return;
        if(Lv == n){
            answer = Math.max(answer, sum);
        }else{
            DFS(num+1, Lv+1, sum + arr[Lv]);
            DFS(num+1, Lv+1, sum);
        }
    }
}
