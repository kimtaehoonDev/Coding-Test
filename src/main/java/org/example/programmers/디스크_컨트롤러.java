package org.example.programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 실패 / 1시간 반 / 구현 / 머리꼬임
public class 디스크_컨트롤러 {

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> {
            if (j1[0] != j2[0]) {
                return j1[0] - j2[0];
            }
            return j1[1] - j2[1];
        });
        // System.out.println("정렬 결과");
        // for(int[] job : jobs) {
        //     System.out.println(Arrays.toString(job));
        // }
        Queue<int[]> waitings = new PriorityQueue<>((j1, j2) -> {
            if (j1[1] != j2[1]) {
                return j1[1] - j2[1]; // 작업 소요시간 짧은거부터
            }
            return j1[0] - j2[0]; // 작업 빨리 시작한 거부터
        });

        int time = 0;
        int idx = -1;
        int totalTime = 0;
        while (true) {
            int[] now = null;
            if (!waitings.isEmpty()) {
                now = waitings.poll();
            } else { // 대기열에 아무것도 없으면 다음거 실행
                if (idx + 1 >= jobs.length) {
                    break; // 대기열도 없고, 전부 다 확인한 상태
                }
                now = jobs[idx + 1];
            }

            // now[0] 요청시각, now[1] 수행시간
            time = Math.max(time, now[0]); // 현재 시간이랑 요청 시간 중 더 큰거(now 작업을 몇 시에 실행할래)
            time += now[1]; // 일 끝
            totalTime += (time - now[0]);
            System.out.println(Arrays.toString(now) + "작업 수행 후 시간" + time);
            System.out.println("총 대기&작업 시간 : " + totalTime);
            // 현재 시간을 기준으로 작업 가능한 애들을 찾는다
            boolean isLast = true;
            for (int i = idx + 1; i < jobs.length; i++) {
                if (jobs[i][0] <= time) { // 현재 시간이 10초인데, 9초에 실행가능한 애가 있으면 waiting에 넣기
                    waitings.offer(jobs[i]);
                } else { // i번째 원소는 아직 시간이 도달하지 않음. 이후 여기부터 다시 볼거
                    isLast = false;
                    idx = i - 1;
                    break;
                }
            }
            if (isLast) {
                idx = jobs.length;
            }
        }

        return totalTime / jobs.length;
    }

}
