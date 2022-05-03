package Inflean.remind;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m;
    static int[][] arr, board;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        arr = new int[m][n];
        board = new int[m][n];
        Queue<Point> q = new LinkedList<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                arr[i][j] = in.nextInt();
                if(arr[i][j] ==1) q.offer(new Point(i, j));
            }
        }

        BFS(arr,q);

        boolean flag = true;

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) flag =false;
            }
        }

        if(flag){
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++) {
                    answer = Math.max(answer, board[i][j]);
                }
            }
            System.out.println(answer);
        }else System.out.println(-1);

    }

    public static int BFS(int[][] arr, Queue<Point> q){
        int answer = 0;

        while(!q.isEmpty()){
            Point pos = q.poll();
            for(int i=0; i<4; i++){
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];
                if(nx >= 0 && nx <m && ny >=0 && ny <n && arr[nx][ny] ==0){
                    arr[nx][ny] =1;
                    q.offer(new Point(nx, ny));
                    board[nx][ny] = board[pos.x][pos.y] +1;
                }
            }
        }

        return answer;
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
