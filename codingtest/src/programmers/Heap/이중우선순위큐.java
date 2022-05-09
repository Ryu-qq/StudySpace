package programmers.Heap;

import java.util.*;

public class 이중우선순위큐 {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<Integer> maxq = new PriorityQueue<>(Comparator.reverseOrder());

        for(String s : operations){
            StringTokenizer st = new StringTokenizer(s);
            String jd = st.nextToken();
            int i = Integer.parseInt(st.nextToken());

            if(jd.equals("I")) {
                q.add(i);
                maxq.add(i);
            }

            if(jd.equals("D") && !q.isEmpty() && !maxq.isEmpty()) {
                if (i == 1) {
                    int max = maxq.poll();
                    q.remove(max);
                } else {
                    int min = q.poll();
                    maxq.remove(min);
                }
            }


        }

        if(!q.isEmpty()){
            answer[0] = maxq.poll();
            answer[1] = q.poll();
        }


        return answer;
    }
}
