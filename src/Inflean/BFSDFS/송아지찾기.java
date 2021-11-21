package Inflean.BFSDFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 송아지찾기 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();


        System.out.println(solution(n, m));
    }

    public static int solution(int n, int m){

        int lev=0;
        int[] dx ={1, -1, 5};
        int[] visited = new int[10001];

        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        while(!q.isEmpty()){
            int len =q.size();
            for(int i=0; i<len; i++){
                int pos = q.poll();
                for(int j=0; j<3; j++){
                    if(pos +dx[j]  == m) return lev+1;
                    if(visited[pos+dx[j]] ==0 && pos+dx[j] <10000 && pos+dx[j]>0){
                        visited[pos+ dx[j]] = 1;
                        q.offer(pos+ dx[j]);
                    }
                }
            }
            lev++;
        }

        return lev;
    }
}
