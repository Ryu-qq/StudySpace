package Inflean.sort;

import java.util.Arrays;
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

        System.out.println(solution(n,m,arr));
    }

    public static int solution(int n, int m, int[] arr){
        int answer = 0;
        int lt=0, rt=n-1;

        while(lt <=rt){
            int mi = (lt+rt) / 2;
            if(arr[mi] == m){
                answer = mi+1;
                break;
            }

            if(arr[mi] >m) rt = mi-1;
            else lt = mi+1;

        }

        return answer;

    }
}
