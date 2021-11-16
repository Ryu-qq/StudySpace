package Inflean.StackQueue;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/***
 * 설명
 * 입력된 문자열에서 소괄호 ( ) 사이에 존재하는 모든 문자를 제거하고 남은 문자만 출력하는 프로그램을 작성하세요.
 * 입력
 * 첫 줄에 문자열이 주어진다. 문자열의 길이는 100을 넘지 않는다.
 * 출력
 * 남은 문자만 출력한다.
 */
public class 괄호문자제거 {
    public static void main(String[] args) {
        Scanner in =new Scanner(System.in);
        String c = in.next();

        System.out.println(solution(c));

    }

    public static String solution(String c){
        String answer ="";
        Stack<Character> s = new Stack<>();
        ArrayList<Character> list = new ArrayList<>();

        for(char x : c.toCharArray()){
            if(x =='(') s.push(x);
            else if(x == ')') s.pop();
            else if(s.isEmpty()) list.add(x);

        }

        for(char x : list){
            answer += x;
        }
        return answer;

    }
}
