package Inflean.sort;

import java.util.Scanner;

public class 선택정렬 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }

        for(int x : soulution(n, arr)){
            System.out.print(x + " ");
        }

    }

    public static int[] soulution(int n, int[] arr){

        for(int i=0; i<n-1; i++){
            int idx = i;
            for(int j=i+1; j<n; j++){
                if(arr[j] < arr[idx]) idx = j;
            }
            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }

        return arr;

    }
}
