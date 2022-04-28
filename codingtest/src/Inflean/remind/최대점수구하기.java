package Inflean.remind;

import java.util.Scanner;

public class 최대점수구하기 {

    static class Quiz{
        int score;
        int spend;

        public Quiz(int score, int spend){
            this.score = score;
            this.spend = spend;
        }
    }

    public static int n, LimitTime, spendAll, answer;
    public static Quiz[] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        LimitTime = in.nextInt();

        arr = new Quiz[n];
        int[] ch = new int[n];

        for(int i =0; i<n; i++){
            int score = in.nextInt();
            int spend = in.nextInt();
            arr[i] = new Quiz(score, spend);
        }

        DFS(0, 0, 0);
        System.out.println(answer);

    }

    public static void DFS(int Lv, int sum, int spendAll){
        if(LimitTime < spendAll) return;
        if(Lv == n){
            answer = Math.max(answer, sum);
        }else{
            DFS(Lv+1, sum + arr[Lv].score, spendAll+ arr[Lv].spend);
            DFS(Lv+1, sum, spendAll);

        }

    }

}
