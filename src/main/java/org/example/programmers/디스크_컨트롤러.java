package org.example.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 성공 / 누적 시간 2시간 반? / 구현
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
        waitings.offer(jobs[0]);

        int idx = 1;
        int time = 0;
        int acc = 0;
        while (true) {
            if (waitings.isEmpty() && idx >= jobs.length) {
                break;
            }

            int[] now = null;
            if (!waitings.isEmpty()) {
                now = waitings.poll();

                // now를 실행한다. -> 시간이 흘러가고, acc가 누적되고
                time = Math.max(time, now[0]) + now[1];
                acc += (time - now[0]);
                // System.out.println(Arrays.toString(now) + " 작업 이후 : " + time);
                // System.out.println(acc+"\n");

                // 새롭게 큐에 들어오는 애들이 있는가?
                boolean isLast = true;
                for (int i = idx; i < jobs.length; i++) {
                    if (jobs[i][0] <= time) { // 웨이팅 큐에 들어가야지
                        idx = i + 1;
                        waitings.offer(jobs[i]);
                        // System.out.println(Arrays.toString(now) + "에서 " + idx);
                    } else { // i번째 원소는 시작 시간이 한참 뒤다
                        isLast = false;
                        break;
                    }
                }
            } else {
                // 큐에 어떻게든 넣어야 해
                if (idx >= jobs.length) {
                    break;
                }
                waitings.offer(jobs[idx++]);
            }

        }
        // System.out.println(acc);
        return acc / jobs.length;
    }

}
