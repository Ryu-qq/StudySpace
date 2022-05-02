package Inflean.remind;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 미로의최단거리통로 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] arr, board;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        arr =new int[8][8];
        board =new int[8][8];

        for(int i=1; i<=7; i++){
            for(int j=1; j<=7; j++){
                arr[i][j] = in.nextInt();
            }
        }


        BFS(1, 1);
        if(board[7][7] >0) System.out.println(board[7][7]);
        else System.out.println(-1);

    }

    public static void BFS(int x, int y){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        while(!q.isEmpty()){
            Point pos = q.poll();
            for(int i=0; i<4; i++){
                int cX = pos.x + dx[i];
                int cY = pos.y + dy[i];
                if(cX>= 1 && cX <=7 && cY >=1 && cY <=7 && arr[cX][cY] ==0){
                    arr[cX][cY] = 1;
                    q.offer(new Point(cX, cY));
                    board[cX][cY] = board[pos.x][pos.y] + 1;

                }
            }
        }
    }

    public static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
