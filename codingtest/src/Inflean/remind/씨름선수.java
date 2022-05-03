package Inflean.remind;


import java.util.*;

public class 씨름선수 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        ArrayList<Person> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int height = in.nextInt();
            int kg = in.nextInt();
            list.add(new Person(height, kg));
        }
        Collections.sort(list);

        System.out.println(solution(list));

    }

    public static int solution(ArrayList<Person> list){
        int answer =Integer.MIN_VALUE;
        int cnt = 0;
        for(Person x : list){
            if(x.kg > answer){
                answer = x.kg;
                cnt++;
            }
        }

        return cnt;
    }


    public static class Person implements Comparable<Person>{
        int height;
        int kg;

        public Person(int height, int kg){
            this.height = height;
            this.kg = kg;
        }

        //자기자신에서 불러온 객체 뺴면 내림차순
        @Override
        public int compareTo(Person o) {
            if (o.height == this.height) return o.kg - this.kg;
            else return o.height - this.height;
        }
    }
}
