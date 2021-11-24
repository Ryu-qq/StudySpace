package Inflean.BFSDFS;

import java.util.ArrayList;
import java.util.Scanner;

public class 중복순열구하기 {
    static int m, n;
    static int[] arr;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();

        arr = new int[m];

        DFS(0);
    }

    public static void DFS(int L){
        if(L == m){
            for(int x : arr) System.out.print(x + " ");
            System.out.println();


        }else{
            for(int i=1; i<=n; i++){
                arr[L] = i;
                System.out.println("i번째= " + i);
                DFS(L+1);

            }
        }
    }
}
