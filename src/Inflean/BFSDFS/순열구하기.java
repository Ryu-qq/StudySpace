package Inflean.BFSDFS;

import java.util.Scanner;

public class 순열구하기 {
    static int n, m;
    static int[] pm, ch;
    public void DFS(int L, int[] arr){
        if(L == m){
            for(int x :pm) System.out.println(x + " ");
            System.out.println();
        }
        else{
            for(int i =0; i<n; i++){
                if(ch[i] ==0){
                    ch[i] =1;
                    pm[L] = arr[i];
                    DFS(L+1,arr);
                    ch[i] =0;
                }
            }
        }
    }



    public static void main(String args[]){
        순열구하기 T = new 순열구하기();
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();

        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }
        ch = new int[n];
        pm = new int[m];
        T.DFS(0, arr);
    }

}
