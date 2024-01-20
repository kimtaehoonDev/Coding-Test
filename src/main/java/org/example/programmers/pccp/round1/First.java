package org.example.programmers.pccp.round1;

import java.util.*;

// 12분 / 성공 / 구현
class First {
    public String solution(String input_string) {
        int[] dp = new int[('z' - 'a') + 1];

        char[] inputs = input_string.toCharArray();
        int start = 0;
        int end = 1;
        while(end < inputs.length) { // end??
            if (inputs[start] == inputs[end]) {
                end++;
            } else {
                dp[inputs[start] - 'a'] += 1;
                start = end;
                end++;
            }
        }
        dp[inputs[start] - 'a'] += 1;
        // System.out.println(Arrays.toString(dp));

        List<Character> answers = new ArrayList<>();
        for(int i = 0; i<dp.length; i++) {
            if (dp[i] >= 2) {
                answers.add((char)('a' + i));
            }
        }
        Collections.sort(answers);
        // System.out.println(answers);
        if (answers.size() == 0) {
            return "N";
        }
        StringBuilder sb = new StringBuilder();
        for(char each : answers) {
            sb.append(each);
        }
        return sb.toString();
    }
}