package HEAP;
/*
매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을
아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때,
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

*/

/*
내 생각  -> 오름차순 정렬
          위에 작은거 두개 뽑아서 K보다 큰지 반복 그 사이에 count +
*/
import java.util.PriorityQueue;


public class MoreSpicy {
    public int moreSpicy(int[] scoville, int K){

        int answer =0;

        //우선순위가 낮은 숫자순
        //높은 순은 new PriorityQueue<>(Collections.reverseOrder()) 추가
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =0; i<=scoville.length; i++){
            pq.add(scoville[i]);
        }

        while (pq.peek()<K){
            if(pq.size() <2) return -1;

            int num1 = pq.poll();
            int num2 = pq.poll();

            int mixnum = num1 + (num2 *2);
            pq.add(mixnum);
            answer++;
        }
        return answer;
    }


}
