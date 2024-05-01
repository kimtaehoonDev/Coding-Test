package org.example.programmers.pccp.round1_try2;

import java.util.*;

// 성공 / 28분 / 구현
// 정말 우연히도, 앞서 풀었던 프로그래머스 문제와 동일한 문제가 나옴. 그래서 쉽게 풀 수 있었던 거 같음
class Num4 {
    static int PRIORITY = 0;
    static int START = 1;
    static int WORKING_TIME = 2;

    public long[] solution(int[][] programs) {
        long[] answer = new long[11];

        Arrays.sort(programs, (p1, p2) -> {
            if (p1[START] != p2[START]) {
                return p1[START] - p2[START]; // 빨리 시작하는 순서로 정렬
            }
            return p1[PRIORITY] - p2[PRIORITY]; // 시작 시간 같으면 우선순위대로 정렬
        });

        Queue<int[]> waitings = new PriorityQueue<>((p1, p2) -> {
            if (p1[PRIORITY] != p2[PRIORITY]) {
                return p1[PRIORITY] - p2[PRIORITY];
            }
            return p1[START] - p2[START]; // 우선순위 같으면 먼저 호출된거부터
        });

        long time = 0L;
        int idx = 0; // 대기큐 들어가거나 실행되지 않은 첫 번째 프로그램
        while(true) {
            while(idx < programs.length && programs[idx][START] <= time) {
                waitings.add(programs[idx++]);
            }
            if (waitings.isEmpty()) {
                if (idx >= programs.length) {
                    break; // 종료조건
                }
                time = programs[idx][START];
                continue;
            }

            // 현재 프로그램을 실행한다.
            int[] now = waitings.poll();

            long waitingTime = Math.max(0L, time - now[START]);
            answer[now[PRIORITY]] += waitingTime;

            time = Math.max(time, now[START]) + now[WORKING_TIME];
        }
        answer[0] = time;
        return answer;
    }
}