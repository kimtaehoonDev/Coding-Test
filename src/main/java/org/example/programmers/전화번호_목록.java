package org.example.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class 전화번호_목록 {
    public boolean solution(String[] phoneNumsAry) {
        boolean answer = true;
        Map<String, Object> hash = new HashMap<>();

        // 짧은게 앞으로
        List<String> phoneNums = new ArrayList<>();

        for(int i=0; i<phoneNumsAry.length; i++) {
            phoneNums.add(phoneNumsAry[i]);
        }

        Collections.sort(phoneNums, new Comparator<>() {
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });


        for(String each: phoneNums) {
            if (!answer) {
                break;
            }
            for(int i=0; i<each.length();i++) {
                String key = each.substring(0, i+1);
                if (hash.containsKey(key)) {
                    answer = false;
                    break;
                }
            }
            hash.put(each, null);
        }

        return answer;
    }
}