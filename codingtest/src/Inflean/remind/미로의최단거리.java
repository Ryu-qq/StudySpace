package Inflean.remind;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 미로의최단거리 {


    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dis, board ;
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        board =  new int[8][8];
        dis =  new int[8][8];
        for(int i=1; i<=7; i++){
            for(int j=1; j<=7; j++){
                board[i][j] = in.nextInt();
            }
        }

        BFS(1,1);
        if(dis[7][7] == 0) System.out.println(-1);
        else System.out.println(dis[7][7]);




    }

    public static void BFS(int x, int y){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));
        board[x][y] =1;
        while(!q.isEmpty()){
            Point cur = q.poll();
            for(int i=0; i<4; i++){
                int cx = cur.x + dx[i];
                int cy = cur.y + dy[i];
                if(cx >=1 && cy >= 1 && cx <=7 && cy <=7 && board[cx][cy] ==0){
                    board[cx][cy] =1;
                    q.offer(new Point(cx, cy));
                    dis[cx][cy] = dis[cur.x][cur.y]+1;
                }
            }

        }
    }
}
