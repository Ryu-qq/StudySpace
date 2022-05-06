package Inflean.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 원더랜드 {
    static int[] arr, dis;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int e = in.nextInt();

        arr = new int[n+1];
        dis = new int[n+1];

        ArrayList<Node> list = new ArrayList<>();

        for(int i=1; i<n+1; i++){
            arr[i] = i;
        }

        for(int i=0; i<e; i++){
            int start = in.nextInt();
            int end = in.nextInt();
            int cost = in.nextInt();
            list.add(new Node(start, end, cost));
        }

        Collections.sort(list);

        System.out.println(solution(list));
    }

    public static int solution(ArrayList<Node> list){
        int sum =0;
        for(Node n : list){
            int start = find(n.start);
            int end = find(n.end);
            if(start != end){
                sum += n.cost;
                union(n.start, n.end);
            }
        }

        return sum;

    }

    public static int find(int a){
        if(arr[a] ==a) return a;
        else return arr[a] = find(arr[a]);
    }

    public static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);

        if(fa != fb) arr[fa] = fb;
    }




    public static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int start ,int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}
