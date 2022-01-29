package HASH;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
 * 마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
 * 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.
 *
 *
 * 제한사항
 * 마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
 * completion의 길이는 participant의 길이보다 1 작습니다.
 * 참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
 * 참가자 중에는 동명이인이 있을 수 있습니다.
 * **/

public class 완주하지못한선수 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> hm = new HashMap<>();
        for(String player : participant) hm.put(player, hm.getOrDefault(player, 0) +1);
        for(String player : completion) hm.put(player, hm.getOrDefault(player, 0) -1);
        for(String key : hm.keySet()){
            if(hm.get(key) !=0){
                answer = key;
                System.out.println(answer);
                break;
            }
        }

//sort해서 인덱스 순차별로 비교시 동일하지않은거
//        Arrays.sort(participant);
//        Arrays.sort(completion);
//        for(int i =0; i< completion.length; i++){
//            if(!completion[i].equals.(participant[i])){
//                answer =participant[i];
//            }
//
//        }



        return answer;
    }



}


//getOrDe기ault   key 값이 있으면 +1 없으면 디폴트(0) 넣기
//keySet() key값만 꺼낼때


