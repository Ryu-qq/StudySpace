package Inflean.BFSDFS;

import java.util.Scanner;

/***
 * nCr 공식 사용하지않고
 * nCr = n-1Cr-1 + n-1Cr  이 공식을 사용하여 재귀함수로 표현하시오
 */

public class 조합의경우수 {
    static int n;
    static int r;
    static int[][] arr;
    static int answer = 0;
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        r = in.nextInt();
        arr =new int[n+1][r+1];
        DFS(n,r);
        System.out.println(answer);

    }
    //
    public static void DFS(int n, int r){
        if(n<1) return;
        if(r<0) return;
        if(arr[n][r] ==1) return;
        if(n == r || r ==0) answer++;
        if(r ==1) answer +=n;

        arr[n][r] =1;
        System.out.println("n = " + n);
        System.out.println("r = " + r);
        DFS(n - 1, r - 1);
        DFS(n - 1, r);
    }

    public int DFS2(int n, int r){
        if(arr[n][r] >0) return arr[n][r];
        if(n == r || r ==0) return 1;
        else{
            return arr[n][r] = DFS2(n - 1, r - 1) +  DFS2(n - 1, r);
        }
    }
}
