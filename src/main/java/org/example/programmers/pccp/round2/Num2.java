package org.example.programmers.pccp.round2;

import java.util.*;

// 성공 / 7분 / 그리디
class Num2 {
    public int solution(int[] ability, int number) {
        Queue<Integer> queue = new PriorityQueue<>(); // 숫자 작은거 뽑기
        for(int each : ability) {
            queue.offer(each);
        }

        // number만큼 반복한다.
        for(int i = 0; i<number; i++) {
            int first = queue.poll();
            int second = queue.poll();
            int result = first + second;
            queue.offer(result);
            queue.offer(result);
            // System.out.println(queue);
        }

        int answer = 0;
        for(int each : queue) {
            answer += each;
        }
        return answer;
    }
}