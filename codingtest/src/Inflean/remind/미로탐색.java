package Inflean.remind;

import java.util.Scanner;

public class 미로탐색 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;
    static int[][] arr;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        arr =new int[8][8];

        for(int i=1; i<=7; i++){
            for(int j=1; j<=7; j++){
                arr[i][j] = in.nextInt();
            }
        }
        arr[1][1] = 1;
        DFS(1, 1);
        System.out.println(answer);
    }

    public static void DFS(int x, int y){
        if(x ==7 && y ==7) answer ++;
        else{
            for(int i=0; i<4; i++){
                int cX = x + dx[i];
                int cY = y + dy[i];

                if(cX>= 1 && cX <=7 && cY >=1 && cY <=7 && arr[cX][cY] ==0){
                    arr[cX][cY] = 1;
                    DFS(cX, cY);
                    arr[cX][cY] = 0;
                }

            }
        }
    }
}
