package Inflean.dynamic;

import java.util.Scanner;

/**
 * 설명
 *
 * 철수는 계단을 오를 때 한 번에 한 계단 또는 두 계단씩 올라간다. 만약 총 4계단을 오른다면 그 방법의 수는
 * 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2 로 5가지이다.
 *
 * 입력
 * 첫째 줄은 계단의 개수인 자연수 N(3≤N≤35)이 주어집니다.
 * 출력
 * 첫 번째 줄에 올라가는 방법의 수를 출력합니다.
 */
public class 돌다리건너기 {
    static int[] arr;
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        arr = new int[n+1];

        System.out.println(solution(n));
    }

    public static int solution(int n){
        arr[1] = 1;
        arr[2] = 2;

        for(int i=3; i<=n; i++){
            arr[i] = arr[i-2] + arr[i-1];
        }
        return arr[n];
    }
}
