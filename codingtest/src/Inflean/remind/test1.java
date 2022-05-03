package Inflean.remind;

import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();

        System.out.println(solution(a, b));
    }

    public static String solution(int a, int b){
        String answer = "case-0";

        answer = answer + String.valueOf(solutionA(a)+solutionB(b));
        return answer;
    }


    public static int solutionA(int A){
        int answer = 0;
        if(A>= 1000) answer = 1;
        else if(A<1000 && A>=100) answer =4;
        else if(A<100) answer =7;

        return answer;
    }

    public static int solutionB(int B){
        int answer = 0;
        if(B> 8000) answer = 0;
        else if(B <=8000 && B >800) answer =1;
        else if(B<=800) answer =2;

        return answer;
    }

}
