package org.example.leetcode;

// 2번 실패 후 성공
// 1번 실패까지 -> 14분
// 디버깅 성공까지 -> + 12분

import java.util.HashMap;
import java.util.Map;

// 문제 제약조건을 제대로 읽지 않아 틀림
// 문자열에 들어가는 건 알파벳만 가능하다고 생각함(공백, 특문 등 가능)
public class p3 {
    public int lengthOfLongestSubstring(String s) {
        // 특정 문자가 몇 번째 인덱스에서 나왔는지 기록합니다.
        // 예를 들어 abc라는 문자열이 있다면 c -> 2 라는 값이 기록됩니다.
        Map<Character, Integer> visited = new HashMap<>();

        int maxLength = 0;
        int idx = -1;

        // 정답 문자열의 시작을 기록한다
        int startIdx = 0;
        // 정답 문자열의 길이를 기록한다
        int length = 0;


        char[] chars = s.toCharArray();
        for(char each: chars) {
            idx++;
            // 정답문자열 안에 each 문자가 존재한다면
            if (visited.containsKey(each) && visited.get(each) != -1) {

                // 몇 번째 인덱스에 위치하는지 확인한다
                // 정답 문자열의 처음부터 해당 문자열까지 연결을 끊는다.
                int prev = visited.get(each);
                for(int i = startIdx; i <= prev; i++) {
                    visited.put(chars[i], -1); // 초기화
                }
                // 정답 문자열의 길이와 시작 지점의 위치를 갱신한다
                length -= (prev - startIdx + 1);
                startIdx = prev + 1;
            }

            // each가 추가된 정답 문자열의 길이를 갱신하고, 문자열에 each를 넣는다
            length++;
            visited.put(each, idx);

            // 만들 수 있는 가장 긴 길이를 찾는다
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
