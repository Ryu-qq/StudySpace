package Inflean.dynamic;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 냅색알고리즘
 *
 *
 *  * 설명
 *  * 다음과 같이 여러 단위의 동전들이 주어져 있을때 거스름돈을 가장 적은 수의 동전으로 교환해주려면 어떻게 주면 되는가?
 *  * 각 단위의 동전은 무한정 쓸 수 있다.
 *  *
 *  *
 *  * 입력
 *  * 첫 번째 줄에는 동전의 종류개수 N(1<=N<=12)이 주어진다. 두 번째 줄에는 N개의 동전의 종류가 주어지고,
 *  * 그 다음줄에 거슬러 줄 금액 M(1<=M<=500)이 주어진다.각 동전의 종류는 100원을 넘지 않는다.
 *  *
 *  * 출력
 *  * 첫 번째 줄에 거슬러 줄 동전의 최소개수를 출력한다.
 *
 */
public class 동전교환 {
    static int[] dy;
    static int n, m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        m = in.nextInt();
        dy = new int[m+1];

        System.out.println(solution(arr));

    }
    public static int solution(int[] arr){
        Arrays.fill(dy, Integer.MAX_VALUE);
        dy[0] = 0;
        for(int i=0; i<n; i++){
            for(int j=arr[i]; j<=m; j++){
                dy[j] = Math.min(dy[j], dy[j-arr[i]] +1);
            }
        }

        return dy[m];
    }

}
