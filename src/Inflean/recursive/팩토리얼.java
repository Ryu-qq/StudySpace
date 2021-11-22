package Inflean.recursive;

import java.util.Scanner;

public class 팩토리얼 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        System.out.println(DFS(n));
    }

    public static int DFS(int n){

        if(n ==0) return 1;
        else{
            return DFS(n-1) * n;
        }

    }
}
