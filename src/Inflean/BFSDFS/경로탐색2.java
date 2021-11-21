package Inflean.BFSDFS;

import java.util.ArrayList;
import java.util.Scanner;

//인접리스트
//정점의 수가 많아지면 인접행렬은 너무 비효율적
public class 경로탐색2 {
    static int n, m, answer =0;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] ch;
    public void DFS(int v){
        if(v==n) answer++;
        else{
            for(int nv :graph.get(v)){
                if(ch[nv] ==0){
                    ch[nv] =1;
                    DFS(nv);
                    ch[nv] =0;
                }
            }

        }

    }

    public static void main(String[] args){
        경로탐색 T = new 경로탐색();
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        ch = new int[n+1];
        for(int i=0; i<m; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
        }
        ch[1] =1;
        T.DFS(1);
        System.out.println("answer = " + answer);
    }


}
