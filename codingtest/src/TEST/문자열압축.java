package TEST;

import java.util.Scanner;

public class 문자열압축 {
    public static int[] temp;
    public static String answer  ="";
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        temp = new int[n+1];
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = in.nextInt();
        }

        DFS(0, arr);
        System.out.println(answer);
    }

    public static void DFS(int Lv, int[] arr){
        if(Lv == arr.length){
            for(int i=1; i<temp.length; i++){
                if(temp[i] ==0) continue;
                answer += String.valueOf(i) + String.valueOf(temp[i]);
            }
        }else{
            temp[arr[Lv]] ++;
            DFS(Lv+1, arr);
        }
    }
}
