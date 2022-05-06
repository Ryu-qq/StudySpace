package Inflean.Greedy;

import java.util.*;

/**
 * 설명
 *
 * 현수는 유명한 강연자이다. N개이 기업에서 강연 요청을 해왔다. 각 기업은 D일 안에 와서 강연을 해 주면 M만큼의 강연료를 주기로 했다.
 * 각 기업이 요청한 D와 M를 바탕으로 가장 많을 돈을 벌 수 있도록 강연 스케쥴을 짜야 한다.
 *
 * 단 강연의 특성상 현수는 하루에 하나의 기업에서만 강연을 할 수 있다.
 * 입력
 * 첫 번째 줄에 자연수 N(1<=N<=10,000)이 주어지고, 다음 N개의 줄에 M(1<=M<=10,000)과 D(1<=D<=10,000)가 차례로 주어진다.
 *
 *
 * 출력
 * 첫 번째 줄에 최대로 벌 수 있는 수입을 출력한다.
 */
public class 최대수입스케쥴 {
    static int n, max = Integer.MIN_VALUE;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        ArrayList<Seminar> list = new ArrayList<>();

        for(int i=0; i<n; i++) {
            int pay = in.nextInt();
            int due = in.nextInt();
            list.add(new Seminar(pay,due));
            if(due > max) max = due;
        }

        System.out.println(solution(list));



    }

    public static int solution(ArrayList<Seminar> list){
        int answer =0;
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        Collections.sort(list);

        int j=0;
        for(int i=max; i>0; i--){
            for(; j<n; j++) {
                if (list.get(j).due <i) break;
                pQ.offer(list.get(j).pay);
            }
            if(!pQ.isEmpty()) answer += pQ.poll();
        }
        return answer;

    }

    public static class Seminar implements Comparable<Seminar>{
        int pay;
        int due;

        public Seminar(int pay, int due){
            this.pay = pay;
            this.due = due;
        }

        @Override
        public int compareTo(Seminar o) {
            return o.due - this.due;
        }
    }
}
