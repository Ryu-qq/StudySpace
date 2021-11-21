package Inflean.BFSDFS;

import com.sun.tools.javac.Main;

import java.util.Scanner;

//인접행렬
public class 경로탐색 {
    static int n, m, answer =0;
    static int[][] graph;
    static int[] ch;
    public void DFS(int v){
        if(v==n) answer++;
        else{
            for(int i=0; i<=n; i++){
                if(graph[v][i] ==1 && ch[i] ==0){
                    ch[i] = 1;
                    DFS(i);
                    ch[i] =0 ;
                }
            }
        }
    }
    
    public static void main(String[] args){
        경로탐색 T = new 경로탐색();
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        graph = new int[n+1][m+1];
        ch = new int[n+1];
        for(int i=0; i<m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a][b] =1;
        }
        ch[1] =1;
        T.DFS(1);
        System.out.println("answer = " + answer);
    }
}
