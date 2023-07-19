package org.example.programmers;

import java.util.*;

// 실패한 문제. 아이디어부터 틀림. 아이디어를 참고해서 품
// 원래 생각했던 아이디어: 가장 무거운 애 + 남은 공간에 태울 수 있는 제일 무거운 애 + 그다음 무거운 애 ....
// 이런 방식이라서 링크드리스트를 사용하면서 넣었다 뺐다 했는데, 그냥 틀리고 시간초과도 뜸

// 투포인터 알고리즘으로 해결
class 구명보트 {
    public static void main(String[] args) {
        구명보트 s = new 구명보트();
        System.out.println(s.solution(new int[] {80, 10, 5, 3}, 100));
    }
    public int solution(int[] peopleAry, int limit) {
        int answer = 0;
        List<Integer> people = new ArrayList<>();

        for(int i = 0; i < peopleAry.length; i++) {
            people.add(peopleAry[i]);
        }
        // 내림차순 정렬한다(3210)
        Collections.sort(people, new Comparator<>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        int startIdx = 0;
        int endIdx = people.size() - 1;
        while(startIdx < endIdx) {
            int remain = limit;
            remain -= people.get(startIdx); //무거운 애를 태운다
            if (people.get(endIdx) <=remain) {
                endIdx--;
            }
            startIdx++;
            answer += 1;
        }
        if (startIdx == endIdx) {
            answer += 1;
        }

        return answer;
    }
}