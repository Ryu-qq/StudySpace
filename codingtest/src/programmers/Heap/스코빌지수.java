package programmers.Heap;

import java.util.*;

public class 스코빌지수 {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int i=0; i<scoville.length; i++){
            q.offer(scoville[i]);
        }

        while(q.peek() <K){
            if(q.size() <2) return -1;
            int x = q.poll();
            int y = q.poll();

            int tot = x + (y *2);
            q.offer(tot);
            answer++;
        }

        return answer;
    }
}
