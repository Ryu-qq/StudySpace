package Inflean.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Meeting implements Comparable<Meeting>{
    int s;
    int e;

    Meeting(int s, int e){
        this.s =s ;
        this.e = e;
    }

    @Override
    public int compareTo(Meeting o){
        if(this.e == o.e) return this.s -o.s;
        else return this.e- o.e;
    }
}

public class 회의실배정 {
    static int n;
    static int cnt = 1;
    static int answer = Integer.MIN_VALUE;
    static int[] ch;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        ArrayList<Meeting> list = new ArrayList<>();
        ch = new int[n];

        for (int i = 0; i < n; i++) {
            int sT = in.nextInt();
            int eT = in.nextInt();
            list.add(new Meeting(sT, eT));
        }

        Collections.sort(list);
        System.out.println(DFS(list));
    }

    public static int DFS(ArrayList<Meeting> list) {
        int rt =1;
        int lt =0;
        while(rt< list.size()){
           if(list.get(lt).e <=list.get(rt).s){
               lt = rt;
               rt++;
               cnt++;
           }else{
               rt++;
           }

        }

        return cnt;

    }
}