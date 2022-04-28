package Inflean.remind;


import java.util.Scanner;

public class 동전교환 {
    static int change, answer =0;
    static int[] arr;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }

        change = in.nextInt();

        DFS(0,0 );

    }

    public static int DFS(int sum ,int Lv){
        if(sum == change){

        }else{
            for(int i=0; i<arr.length; i++){
                DFS(sum+arr[i], Lv+1);
            }
        }


    }





}
