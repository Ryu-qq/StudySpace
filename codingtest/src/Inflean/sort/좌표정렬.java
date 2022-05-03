package Inflean.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 좌표정렬 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Point> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            int x = in.nextInt();
            int y = in.nextInt();
            list.add(new Point(x, y));
        }

        Collections.sort(list);

        for(Point o : list) System.out.println(o.x + " " + o.y);

    }

    public  static class  Point implements Comparable<Point> {

        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //자기 자신에서 불러온 객체를 빼면 오름차순
        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) return this.y - o.y;
            else return this.x -o.x;
        }

    }




}



