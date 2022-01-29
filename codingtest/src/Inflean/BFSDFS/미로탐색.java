package Inflean.BFSDFS;

import java.util.Scanner;

public class 미로탐색 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dxdy ;
    static int cnt =0;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        dxdy =  new int[8][8];

        for(int i=1; i<=7; i++){
            for(int j=1; j<=7; j++){
                dxdy[i][j] = in.nextInt();
            }
        }

        dxdy[1][1]=1;
        DFS(1,1);
        System.out.println("cnt = " + cnt);
    }
    public static void DFS(int x, int y){
        if(x ==7 && y == 7){
            cnt++;
        }else{
            for(int i=0; i<4; i ++){
                int cx = x + dx[i];
                int cy = y + dy[i];
                if(cx >=1 && cy >=1 && cx <=7 && cy <=7 && dxdy[cx][cy] ==0){
                    dxdy[cx][cy] =1;
                    DFS(cx, cy);
                    dxdy[cx][cy] =0;
                }
            }
        }
    }
}
