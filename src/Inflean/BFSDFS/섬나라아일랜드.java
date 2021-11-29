package Inflean.BFSDFS;

import java.util.Scanner;

public class 섬나라아일랜드 {
    static int n;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1};
    static int[] dy = {0, 1, 0, -1, 1, -1, -1, 1};
    static int cnt =0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();

        board = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = in.nextInt();
            }
        }
        solution(board);
        System.out.println(cnt);
    }
    //DFS
    public static void solution(int[][] board){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] ==1){
                    cnt++;
                    board[i][j] =0;
                    DFS(i,j, board);
                }
            }
        }
    }

    public static void DFS(int x, int y, int[][] board) {

        for (int i = 0; i < 8; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < n && cy < n && board[cx][cy] == 1){
                board[cx][cy] =0;
                DFS(cx, cy,board);
            }
        }
    }




}


