package org.example.programmers;

import java.util.HashMap;
import java.util.Map;

public class 완주하지_못한_선수 {

    class Solution {
        public String solution(String[] participant, String[] completion) {
            String answer = "";
            Map<String, Integer> store = new HashMap<>();
            for(String each: participant) {
                store.putIfAbsent(each, 0); // 새롭게 배운 부분
                store.put(each, store.get(each) + 1);
            }

            for(String each: completion) {
                store.put(each, store.get(each) - 1);
                if (store.get(each) == 0) { // contains 아닌가?
                    store.remove(each);
                }
            }

            for(String name: store.keySet()) {
                answer = name;
            }

            return answer;
        }
    }
}
