package Inflean.remind;

import java.util.Scanner;

public class 조합의경우수 {
    static int[][] dy = new int[35][35];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int r = in.nextInt();

        System.out.println(DFS(n, r));

    }

    public static int DFS(int n, int r){
        if(dy[n][r] >0) return dy[n][r];
        if(r ==1) return n;
        if(r == n) return 1;

        return dy[n][r] = DFS(n-1, r-1) + DFS(n-1, r);
    }

}
