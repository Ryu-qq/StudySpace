package Inflean.remind;

import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr =new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }

        solution(n, arr);
    }

    public static void solution(int n, int[] arr){
        int[] answer = new int[n];
        int num;
        for(int i=0; i<n; i++){
            num = arr[i]+1;
            if(num>=10) {
                int len =  String.valueOf(num).toCharArray().length;
                num = Integer.parseInt(String.valueOf(num).substring(len-1, len));
                answer[i] = num;
            }else{
                answer[i] = num;
            }

        }

        for(int x: answer){
            System.out.println(x);
        }

    }
}
