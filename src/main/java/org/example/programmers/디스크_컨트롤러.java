package org.example.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 성공(재풀이) / 첫시도 2시간 넘게, 두번째 15분 / 구현
// 포인터를 이리저리 움직여가며 하다보니 머리가 복잡하게 꼬임
// 확실히 포인터를 움직이는 문제에 약하다
public class 디스크_컨트롤러 {

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> {
            if (j1[0] != j2[0]) {
                return j1[0] - j2[0];
            }
            return j1[1] - j2[1];
        });

        Queue<int[]> waitings = new PriorityQueue<>((j1, j2) -> {
            if (j1[1] != j2[1]) {
                return j1[1] - j2[1]; // 작업 소요시간 짧은거부터
            }
            return j1[0] - j2[0]; // 작업 빨리 시작한 거부터
        });

        int time = 0;
        int idx = 0;
        int acc = 0;
        while (true) {
            while (idx < jobs.length && jobs[idx][0] <= time) {
                waitings.offer(jobs[idx++]);
            }
            if (waitings.isEmpty()) {
                if (idx >= jobs.length) {
                    break;
                }
                time = jobs[idx][0];
                continue;
            }

            int[] now = waitings.poll(); // 현재 처리할 작업
            time = Math.max(time, now[0]) + now[1];
            acc += (time - now[0]);
        }

        // System.out.println(acc);
        return acc / jobs.length;
    }

}
