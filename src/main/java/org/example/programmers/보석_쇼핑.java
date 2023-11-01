package org.example.programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 실패 : 시간 초과
class 보석_쇼핑 {
    public int[] solution(String[] gemsStr) {
        // init
        int start = 0;
        int end = gemsStr.length - 1;
        int length = end - start;

        Map<String, Integer> lastIdxStore = new HashMap<>();
        Set<String> gems = new HashSet<>(); // 어떤 보석들이 있었는지 기록
        for(String each: gemsStr) {
            gems.add(each);
        }
        for(String gem: gems) {
            lastIdxStore.put(gem, null);
        }

        // code
        for(int i=0; i<gemsStr.length; i++) {
            String now = gemsStr[i];
            Integer lastIdx = lastIdxStore.get(now);

            lastIdxStore.put(now, i);

            // 모든 gems가 다 lastIdxStore에 기록이 있는거지
            boolean canAnswer = true;
            for(String each: gems) {
                Integer idx = lastIdxStore.get(each);
                if (idx == null) {
                    canAnswer = false;
                    break;
                }
            }
            // 조건을 만족하면
            if (canAnswer) {
                // 중복있던거 중 가장 큰 값을 찾는다 (중복 한개도 없었으면 그놈이 답 아냐?)
                int min = 100000000;
                String gemHavingMin = "";
                for(String each: gems) {
                    Integer idx = lastIdxStore.get(each);
                    if (idx < min) {
                        min = idx;
                        gemHavingMin = each;
                    }
                }
                // System.out.println(i + " 인덱스에서 " + gemHavingMin + "는 " + min);
                // System.out.println(lastIdxStore);
                // System.out.println("===0");
                if (gemHavingMin.equals("")) {
                    start = 0;
                    end = i;
                    break;
                } else {
                    lastIdxStore.put(gemHavingMin, null);
                    if (i - min < length) {
                        // System.out.println("갱신" + i);
                        start = min;
                        end = i;
                        length = end - start;
                    }
                }
            }
        }


        int[] answer = new int[2];
        answer[0] = start + 1;
        answer[1] = end + 1;
        return answer;
    }

}