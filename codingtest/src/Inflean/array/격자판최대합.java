package Inflean.array;

import java.util.Scanner;

/***
 * N*N의 격자판이 주어지면 각 행의 합, 각 열의 합, 두 대각선의 합 중 가 장 큰 합을 출력합니다.
 *
 * 입력
 * 첫 줄에 자연수 N이 주어진다.(2<=N<=50)
 * 두 번째 줄부터 N줄에 걸쳐 각 줄에 N개의 자연수가 주어진다. 각 자연수는 100을 넘지 않는다.
 *
 * 출력
 * 최대합을 출렵합니다
 */


public class 격자판최대합 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] arr = new int[n][n];

        for (int i=0; i <n; i++) {
            for(int j=0; j<n; j++){
                arr[i][j] = in.nextInt();
            }

        }
        System.out.println(solution(n, arr));
    }
    public static int solution(int n, int[][] arr){
        int answer=0;
        int maxR, maxC;

        for(int i=0; i<n; i++){
            maxR = 0;
            maxC = 0;
            for(int j=0; j<n; j++){
                maxR += arr[i][j];
                maxC += arr[j][i];
            }
            answer = Math.max(answer,maxR);
            answer = Math.max(answer,maxC);

        }

        maxR =0;
        maxC =0;
        for(int i=0; i<n; i++){
            maxR += arr[i][i];
            maxC += arr[i][n-1-i];
        }
        answer = Math.max(answer,maxR);
        answer = Math.max(answer,maxC);

        return answer;

    }
}
