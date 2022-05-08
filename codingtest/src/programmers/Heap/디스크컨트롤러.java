package programmers.Heap;

import java.util.*;

public class 디스크컨트롤러 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        int[][] jobs = new int[n][2];

        for(int i=0; i<jobs.length; i++){
            int start = in.nextInt();
            int end = in.nextInt();
            jobs[i][0] = start;
            jobs[i][1] = end;

        }

        System.out.println(solution(jobs));
    }


    public static int solution(int[][] jobs) {

        PriorityQueue<Job> run = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

        PriorityQueue<Job> wait = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);





        for(int i=0; i< jobs.length; i++){
            run.offer(new Job(jobs[i][0], jobs[i][1]));
        }

        int idx =0;
        int sum =0;
        int time =0;
        while(idx < jobs.length){

            //요청작업들중 먼저 온 요청들을 대기열에 넣음
            while (!run.isEmpty() && time >= run.peek().start){
                wait.offer(run.poll());
            }

            //대기열이 비어있지 않을떄 빨리 끝나는 순으로 뽑음
            if(!wait.isEmpty()){
                Job waitJob = wait.poll();
                sum +=  time - waitJob.start + waitJob.end;
                time += waitJob.end;
                idx ++;
            }else{
                time++;
            }
        }
        return sum/idx;
    }

    public static class Job{
        int start;
        int end;

        public Job(int start, int end){
            this.start = start;
            this.end = end;
        }

    }

}
