package org.example.programmers;

import java.util.Arrays;

// 9분 / 성공 / 아이디어
public class 최고의_집합 {

    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }

        // 오름차순으로 정렬
        int[] answer = new int[n];
        int x = s / n;
        int y = s % n;
        for (int i = 0; i < n; i++) {
            if (i >= n - y) {
                answer[i] = x + 1;
            } else {
                answer[i] = x;
            }
        }
        return answer;
    }
}
