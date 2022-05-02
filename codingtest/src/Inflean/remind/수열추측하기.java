package Inflean.remind;

import java.util.Scanner;

public class 수열추측하기 {
    static int[][] cb = new int[35][35];
    static int[] ch, p, c;
    static int n, f;
    static boolean flag= false;
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        f = in.nextInt();

        p = new int[n]; // 퍼뮤테이션 넣는 배열
        c = new int [n]; // 콤비네이션 넣는 배열
        ch = new int[n+1]; //퍼뮤테이션을 위한 체크 배열

        for(int i=0; i<n; i++){
            c[i] = combi(n-1, i);
        }

        DFS(0, 0);
    }

    public static int combi(int n, int r){
        if(cb[n][r] >0) return cb[n][r];
        if(n == r || r ==0) return 1;
        else return cb[n][r] = combi(n-1, r-1) + combi(n-1, r);
    }

    public static void DFS(int L, int sum){
        if(flag) return;
        if(L ==n){
            if(sum == f){
                for(int x : p) System.out.print(x + " ");
                flag = true;
            }

        }else{
            for(int i=1; i<=n; i++){
                if(ch[i] ==0){
                    ch[i] =1;
                    p[L] =i;
                    DFS(L+1, sum+(p[L] * c[L]));
                    ch[i] =0;
                }
            }
        }
    }
}
