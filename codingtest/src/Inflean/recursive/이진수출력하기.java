package Inflean.recursive;

import java.util.Scanner;

public class 이진수출력하기 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        DFS(n);
    }

    public static void DFS(int n){
        if(n ==0) return;
        else{
            System.out.print(n%2);
            DFS(n/2);
        }

    }
}
