package TEST;

import java.util.*;

public class One {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int n = in.nextInt();
        String[] arr = new String[n];

        for(int i=0; i<n; i++){
            arr[i] = in.next();
        }
        solution(s, arr);
    }

    public static String[] solution(String order, String[] words){
        String[] answer = new String[words.length];

        HashMap<Character, Integer> map = new HashMap();
        ArrayList<priority> list = new ArrayList<>();
        if(words.length ==1) return words;


        for(int i=0; i<order.length(); i++){
          map.put(order.charAt(i), i);
        }

        for(int i=0; i< words.length; i++){
                char c = words[i].charAt(0);
                Integer integer = map.get(c);
                list.add(new priority(integer, words[i]));
        }

        Collections.sort(list);

        for(priority p : list){
            System.out.println(p.s);
        }


        return answer;
    }


    public static class priority implements Comparable<priority>{
        int num;
        String s;

        public priority( int num, String s){
            this.num = num;
            this.s = s;
        }

        @Override
        public int compareTo(priority o) {
            return this.num - o.num;
        }
    }



}
