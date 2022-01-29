package Inflean.recursive;

import java.util.Scanner;


public class 피보나치메모이제이션 {
    static int[] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        arr = new int[n+1];
        System.out.println(DFS(n));
    }


    public static int DFS(int n){
        //메모이제이션
        if(arr[n] !=0) return DFS(n);
        if(n ==1) return 1;
        else if (n ==2) return 1;
        else return DFS(n-2) + DFS(n-1);
    }
}
