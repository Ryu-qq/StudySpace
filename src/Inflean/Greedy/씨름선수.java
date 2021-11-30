package Inflean.Greedy;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

class Person implements Comparable<Person>{
    int h;
    int w;

    Person(int h, int w){
        this.h =h ;
        this.w = w;
    }
    @Override
    public int compareTo(Person o){
        return o.h- this.h;
    }
}

public class 씨름선수 {
    static int n;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        ArrayList<Person> list = new ArrayList<>();

        for(int i=0; i<n; i++){
            int h = in.nextInt();
            int w = in.nextInt();
            list.add(new Person(h,w));
        }

        System.out.println(solution(list));

    }

    public static int solution(ArrayList<Person> list){
        int answer = Integer.MIN_VALUE;
        int cnt =0;

        for(Person x : list){
            if(x.w > answer){
                answer = x.w;
                cnt++;
            }
        }

        return cnt;

    }
}
