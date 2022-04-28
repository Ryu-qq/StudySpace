package Inflean.BFSDFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 송아지찾기 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();


        System.out.println(solution(n, m));
    }

    public static int solution(int n, int m) {
        int Lv = 0;
        int[] arr = new int[10001];
        int [] dx = new int[]{1, -1, 5};

        Queue<Integer> q = new LinkedList();
        arr[n] = 1;
        q.offer(n);

        while(!q.isEmpty()){
            int len = q.size();
            for(int i=0; i<len; i++){
                int pos = q.poll();
                for(int j=0; j<3; j++) {
                    int x = pos+dx[j];
                    if (x == m) return Lv+1;
                    if (arr[x] == 0 && x< 10000 && x > 0) {
                        arr[x] = 1;
                        q.offer(x);
                    }
                }
            }
            Lv++;
        }
        return 0;
    }
}
