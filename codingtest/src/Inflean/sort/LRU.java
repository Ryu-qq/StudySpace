package Inflean.sort;

import java.util.Scanner;

public class LRU {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] arr = new int[m];

        for(int i=0; i<m; i++){
            arr[i] = in.nextInt();
        }

        for(int x : soulution(n, arr)){
            System.out.print(x + " ");
        }

    }

    public static int[] soulution(int n, int[] arr) {
        int[] cache = new int[n];

        for(int x: arr){
            int pos = -1;
            for(int j=0; j<n; j++) if(x == cache[j]) pos = j;

            if(pos == -1){
                for(int j=n-1; j>=1; j--){
                    cache[j] =cache[j-1];
                }
                cache[0] = x;
            }else{
                for(int j = pos; j>=1; j--){
                    cache[j] =cache[j-1];
                }
                cache[0] = x;
            }

        }
        return cache;

    }
}
