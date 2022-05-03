package Inflean.remind;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 섬나라 {
    static int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1};
    static int[] dy = {0, 1, 0, -1, 1, -1, -1, 1};
    static int n, cnt;
    static int[][] arr, ch;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        arr = new int[n][n];


        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                arr[i][j] = in.nextInt();
            }
        }

        solution(arr);

        System.out.println(cnt);
    }

    public static void BFS(int n, int m, int[][] arr ){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(n, m));
        while(!q.isEmpty()){
            Point pos = q.poll();
            for(int i=0; i<8; i++){
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if(nx >= 0 && nx <7 && ny >=0 && ny <7 && arr[nx][ny] ==1){
                    arr[nx][ny] =0;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    public static void solution(int[][] arr){
        for(int i=0; i<7; i++){
            for (int j=0; j<7; j++){
                if(arr[i][j] ==1){
                    arr[i][j] =0;
                    BFS(i, j, arr);
                    cnt++;
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
