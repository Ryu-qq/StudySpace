package TEST;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Two {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        int[][] arr = new int[m][3];

        for (int i = 0; i < m; i++) {
            for(int j=0; j<3; j++){
                arr[i][j] = in.nextInt();
            }
        }

        System.out.println(solution(n, arr));



    }

    public static int solution(int N, int[][] facility){
        int answer = -1;
        int[][] board  = new int[N+1][N+1];
        int result = Integer.MAX_VALUE;

        Queue<Facility> q = new LinkedList<>();

        for(int i=0; i< facility.length; i++){
            int x = facility[i][0];
            int y = facility[i][1];
            int important = facility[i][2];

            if(x ==1 && y ==1) return 0;
            q.offer(new Facility(x, y, important));
        }

        while(!q.isEmpty()){
            Facility poll = q.poll();


            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(poll.x == i && poll.y == j) continue;
                    int dis = distance(i, poll.x, j, poll.y, poll.important);
                    if(dis > board[i][j]) board[i][j] = dis;


                }
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++) {
                result =Math.min(result, board[i][j]);
            }
        }

        answer = result;

        return answer;
    }


    public static int distance(int x1, int x2, int y1, int y2, int imp){
        int dx = x1 - x2;
        int dy = y1 - y2;

        if(dx <0) dx = dx*(-1);
        if(dy <0) dy=  dy*(-1);

        return (dx + dy) * imp;
    }



    public static class Facility{
        int x;
        int y;
        int important;

        public Facility(int x, int y, int important){
            this.x = x;
            this.y =y;
            this.important =important;
        }
    }
}
