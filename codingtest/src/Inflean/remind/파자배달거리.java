package Inflean.remind;

import java.util.ArrayList;
import java.util.Scanner;

public class 파자배달거리 {

    static int n, p, answer = Integer.MAX_VALUE;
    static Point[] selectP;
    static int[][] arr;
    static ArrayList<Point> houses, pizzas;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        p = in.nextInt();

        arr = new int[n][n];

        houses = new ArrayList<>();
        pizzas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
                if(arr[i][j] == 1) houses.add(new Point(i, j));
                if(arr[i][j] == 2) pizzas.add(new Point(i, j));
            }
        }

        selectP = new Point[p];

        DFS(0, 0);
        System.out.println(answer);
    }

    public static void DFS(int Lv, int start){
        if(Lv == p){
            int sum=0;
            for(int i=0; i< houses.size(); i++){
                int dis=Integer.MAX_VALUE;

                for(int j=0; j<p; j++){
                    dis = Math.min(dis,CaculateDistance(houses.get(i), selectP[j]));
                }
                sum += dis;
            }
            answer = Math.min(answer, sum);

        }else{
            for(int i=start; i< pizzas.size(); i++){
                selectP[Lv] = pizzas.get(i);
                DFS(Lv +1, start +1);
            }

        }
    }

    public static int CaculateDistance(Point house, Point pizza){
        int dx = house.x -pizza.x;
        int dy = house.y -pizza.y;

        if(dx <0) dx = dx*(-1);
        if(dy <0) dy=  dy*(-1);

        return dx + dy;
    }


    public static class Point{
        int x;
        int y;

        public  Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
