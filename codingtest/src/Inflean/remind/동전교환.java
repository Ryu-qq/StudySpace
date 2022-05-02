package Inflean.remind;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class 동전교환 {
    static int change, answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        Integer[] arr = new Integer[n];

        for(int i=0; i<n; i++)  arr[i] = in.nextInt();

        Arrays.sort(arr, Collections.reverseOrder());

        change = in.nextInt();

        DFS(0,0 ,arr);

        System.out.println(answer);

    }

    public static void DFS(int sum ,int Lv, Integer[] arr){
        if(sum > change) return;
        if(sum == change){
            answer = Math.min(answer, Lv);
        }else{
            for(int i=0; i<arr.length; i++){
                DFS(sum+arr[i], Lv+1,arr);
            }
        }
    }
}
