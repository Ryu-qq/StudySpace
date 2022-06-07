package programmers.sort;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class 가장큰수 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Num[] arr = new Num[n];

        for(int i=0; i<n; i++){
            arr[i] = new Num(in.nextInt());
        }

        Arrays.sort(arr);
        for(Num m : arr){
            System.out.println(m.number);
        }
        solution(arr);
    }

    public static String solution(Num[] arr){
        String answer ="";
        List<String> collect = Arrays.stream(arr).map(o -> String.valueOf(o.number))
                .collect(Collectors.toList());

        if(collect.get(0).equals("0")) return "0";
        for(String x : collect){
            answer += x;
        }

        return answer;
    }


    public static class Num implements Comparable<Num>{

        int number;

        public Num(int number){
            this.number = number;
        }

        @Override
        public int compareTo(Num o) {
            String num1 = String.valueOf(o.number);
            String num2 = String.valueOf(this.number);

            int s = Integer.valueOf(num1 + num2);
            int r = Integer.valueOf(num2 + num1);

            return s-r;
        }
    }



    static int max =Integer.MIN_VALUE;
    static int[] ch;
    public static void DFS(int Lv, String sum, int[] arr){
        if(Lv == arr.length){
            max = Math.max(max, Integer.valueOf(sum));
        }else{
            for(int i=0; i<arr.length; i++){
                if(ch[i] ==0){
                    ch[i] =1;
                    DFS(Lv+1, sum + String.valueOf(arr[i]), arr);
                    ch[i] =0;
                }
            }
        }
    }
}
