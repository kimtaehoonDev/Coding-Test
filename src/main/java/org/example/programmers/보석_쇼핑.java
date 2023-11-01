package org.example.programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.util.*;

// 답안 참조. 아이디어는 같았으나 구현 방식이 달랐음
// 투포인터를 사용하면 쉽게 풀 수 있구나.
// 이전의 내 풀이에서는 여러 분기를 거치면서 복잡하게 정답을 찾고 있는데 여기에서는 투포인터 && HashMap의 원소 개수로 간단하게 문제를 풀어버린다.
// 참고한 풀이 : https://taehoung0102.tistory.com/206
class 보석_쇼핑 {
    public int[] solution(String[] gemsStr) {
        // init
        int length = 1000000000;
        int[] answer = new int[2];

        Map<String, Integer> store = new HashMap<>(); // key : 보석명, value : 보석개수
        Set<String> gems = new HashSet<>(); // 어떤 보석들이 있었는지 기록
        for(String each: gemsStr) {
            gems.add(each);
        }

        // code
        int start = 0;
        for(int end=0; end<gemsStr.length; end++) {
            String gem = gemsStr[end];
            store.put(gem, store.getOrDefault(gem, 0) + 1);

            String startGem = gemsStr[start];
            while(store.get(startGem) > 1) {
                store.put(startGem, store.get(startGem) - 1);
                start++;
                startGem = gemsStr[start];
            }

            if (store.size() == gems.size()) {
                if (end - start < length) {
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                    length = end - start;
                    // System.out.println(answer[0] + " " + answer[1]);
                }
            }
        }

        return answer;
    }

}