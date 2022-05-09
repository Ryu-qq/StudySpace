package programmers.Greedy;

import java.util.Scanner;

public class 소수찾기 {
    static int answer =0;

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        solution(s);
    }

    static int[] ch, arr, dx;
    public static int solution(String numbers) {

        arr = new int[numbers.length()];
        ch = new int[numbers.length()];
        dx = new int[9999999];

        for(int i=0; i<numbers.length(); i++){
            arr[i] = Integer.parseInt(String.valueOf(numbers.charAt(i)));
        }

        DFS(0, "");
        System.out.println(answer);
        return answer;
    }

    public static void DFS(int Lv, String sum){
        if(Lv == ch.length &&!sum.equals("")){
            int i = Integer.parseInt(sum);
            if(dx[i] >0) return;
            else{
                dx[i] = i;
                if(isPrime(i)) answer++;
            }
        }else{
            for(int i=0; i<ch.length; i++){
                if(ch[i] ==0){
                    ch[i] =1;
                    DFS(Lv+1, sum +arr[i]);
                    DFS(Lv+1, sum);
                    ch[i] =0;
                }
            }
        }
    }



    public static boolean isPrime(int n){
        if(n ==1 || n ==0) return false;
        for(int i=2; i<n; i++){
            if(n%2 ==0) return false;
        }
        return true;
    }

}
