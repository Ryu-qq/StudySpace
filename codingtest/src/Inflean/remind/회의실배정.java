package Inflean.remind;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//그리디는 어떤순으로 정렬을할지 생각을 하고 모르겠으면 여러가지 정렬해본상태에서 비교해보자.

public class 회의실배정 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        ArrayList<Meeting> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            list.add(new Meeting(start, end));
        }
        Collections.sort(list);

        System.out.println(solution(list));

    }

    public static int solution(ArrayList<Meeting> list){
        int answer =1;

        int lt =0, rt =1;
        while(rt <list.size()){
            if(list.get(lt).end > list.get(rt).start) rt++;
            else {
                lt = rt;
                rt++;
                answer ++;
            }
        }
        return answer;
    }


    public static class Meeting implements Comparable<Meeting>{
        int start;
        int end;

        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end) return this.start - o.start;
            else return this.end - o.end;
        }
    }
}
