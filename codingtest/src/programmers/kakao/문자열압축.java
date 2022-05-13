package programmers.kakao;

import java.util.Scanner;

public class 문자열압축 {
    public static char[] temp;
    public static String answer  ="";
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        String n = in.next();
        temp = new char[150];
        String[] arr = new String[n.length()];


        DFS(0, n.toCharArray());
        System.out.println(answer);

    }

    public static void DFS(int Lv, char[] arr){
        if(Lv == arr.length){

        }else{

        }
    }
}
