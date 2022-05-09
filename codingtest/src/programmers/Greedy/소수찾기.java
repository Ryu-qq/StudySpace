package programmers.Greedy;

import java.util.Scanner;
import java.util.TreeSet;

public class 소수찾기 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        solution(s);
    }

    static int[] ch;
    static int answer;
    static TreeSet<Integer> set;
    public static int solution(String numbers) {

        ch = new int[numbers.length()];
        set = new TreeSet<>();

        DFS(0, "", numbers);

        for(int i : set){
            if(isPrime(i)) answer++;
        }

        return answer;
    }


    public static void DFS(int Lv, String s, String numbers){
        if(Lv == numbers.length()){
            set.add(Integer.parseInt(s));
            System.out.println(s);
        }else{
            for(int i=0; i<numbers.length(); i++){
                if(ch[i] ==1) DFS(Lv+1, s, numbers);
                else {
                    ch[i] = 1;
                    DFS(Lv + 1, s + numbers.charAt(i), numbers);
                    ch[i] = 0;
                }

            }
        }
    }


    public static boolean isPrime(int n){
        if(n ==1 || n ==0) return false;
        for(int i=2; i<n; i++){
            if(n%i ==0) return false;
        }
        return true;
    }

}
