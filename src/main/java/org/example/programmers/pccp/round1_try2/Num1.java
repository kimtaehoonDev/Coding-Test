package org.example.programmers.pccp.round1_try2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 성공 / 7분 / 문자열?
class Num1 {

    public String solution(String input_string) {
        char[] input = input_string.toCharArray();
        // System.out.println(Arrays.toString(input));

        // 압축
        List<Character> result = new ArrayList<>();
        char prev = 'A'; // 나올 수 X
        for (char each : input) {
            if (each != prev) {
                result.add(each);
                prev = each;
            }
        }
        // System.out.println(result);

        Map<Character, Integer> temp = new HashMap<>();
        for (char each : result) {
            temp.put(each, temp.getOrDefault(each, 0) + 1);
        }

        result = new ArrayList<>();
        for (char key : temp.keySet()) {
            if (temp.get(key) >= 2) {
                result.add(key);
            }
        }
        if (result.size() == 0) {
            return "N";
        }
        result.sort((c1, c2) -> c1 - c2);
        StringBuilder sb = new StringBuilder();
        for (char each : result) {
            sb.append(each);
        }

        return sb.toString();
    }
}
