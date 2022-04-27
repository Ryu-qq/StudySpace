package Inflean.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 이분검색 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[] arr = new int[n];


        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }

        Arrays.sort(arr);

        System.out.println();
    }
}
