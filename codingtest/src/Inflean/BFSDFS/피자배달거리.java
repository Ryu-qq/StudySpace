package Inflean.BFSDFS;

import java.util.ArrayList;
import java.util.Scanner;

//0 빈칸, 1 집, 2 피자가게
class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class 피자배달거리{
    static int n, m, cnt, answer = Integer.MAX_VALUE;
    static int[] ch;
    static ArrayList<Point> h,p;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        ch = new int[m];

        p = new ArrayList<>();
        h = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = in.nextInt();
                if(temp ==1) h.add(new Point(i,j));
                if(temp ==2) p.add(new Point(i,j));
            }
        }

        cnt = p.size();
        DFS(0, 0);
        System.out.println(answer);

    }


   public static void DFS(int L, int s) {
        if(L == m ){
            int sum =0;
            for(Point house : h){
                int dis = Integer.MAX_VALUE;
                for(int i : ch){
                    dis = Math.min(dis, Math.abs(house.x - p.get(i).x) + Math.abs(house.y - p.get(i).y));
                }
                sum += dis;
            }
            answer = Math.min(answer, sum);
        }else{
            for(int i=s; i< cnt; i++){
                ch[L] =i;
                DFS(L+1, i+1);
                }
            }
    }
}
