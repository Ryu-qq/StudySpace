package Inflean.StackQueue;

import java.util.Scanner;
import java.util.Stack;

/***
 * 후위연산식이 주어지면 연산한 결과를 출력하는 프로그램을 작성하세요.
 * 만약 3*(5+2)-9 을 후위연산식으로 표현하면 352+*9- 로 표현되며 그 결과는 12입니다.
 *
 * 입력
 * 첫 줄에 후위연산식이 주어집니다. 연산식의 길이는 50을 넘지 않습니다.
 * 식은 1~9의 숫자와 +, -, *, / 연산자로만 이루어진다.
 * 352+*9-
 *
 * 출력
 * 연산한 결과를 출력합니다.
 * 12
 */

public class 후위식연산 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String n = in.next();

        System.out.println(solution(n));

    }

    public static int solution(String n){
        int answer =0;

        Stack<Integer> s = new Stack<>();

        for(char x : n.toCharArray()){
            if(Character.isDigit(x)) {
                s.push(x-48);
            }else{
                int item2 =s.pop();
                int item1 =s.pop();
                if(x =='*'){
                    s.push( item1 * item2);
                }else if(x =='-'){
                    s.push(item1 - item2);
                }else if(x =='+'){
                    s.push(item1 +item2);
                }else if(x =='/'){
                    s.push(item1 / item2);
                }
            }

        }
        answer = s.pop();
        return answer;

    }


}
