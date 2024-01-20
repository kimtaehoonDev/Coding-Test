package org.example.programmers.pccp.round1;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

// 2번 실패 후 성공 / 총 59분 / 구현
// 29분 -> 실패 / 30분 추가 고민 후 성공 ( 이 역시 실전이었다면 틀렸을 문제)

// 고려하지 못한 부분
// 1. 대기중인 프로그램 중에서는 우선순위가 높은 프로그램을 먼저 실행시켜야 했는데 순차적으로 실행시켜버려서 틀림
// 2. 해당 문제 인지하고 다시 풀었음에도 틀림. 모든 프로그램을 다 확인하지 않고 프로그램을 종료하는 경우를 고려하지 못함

// 결국은 풀기야 풀었지만 더 좋은 풀이가 있을거로 보임
public class Fourth {
    static final int PRIORITY = 0;
    static final int IMPORT = 1;
    static final int EXECUTION = 2;

    public long[] solution(int[][] programs) {
        // 빨리 IMPORT(IMPORT작은) 순서대로 정렬
        // 만약 동시에 IMPORT -> 우선순위 작은거부터 정렬
        Arrays.sort(programs, (a1, a2) -> {
            if (a1[IMPORT] != a2[IMPORT])
                return a1[IMPORT] - a2[IMPORT];
            return a1[PRIORITY] - a2[PRIORITY];
        });
        // for(int[] program : programs) {
        //     System.out.println(Arrays.toString(program));
        // }

        int idx = 0; // programs를 어디까지 넣었는지 기록
        Queue<int[]> queue = new PriorityQueue<>((a1, a2) -> {
            if (a1[PRIORITY] != a2[PRIORITY]) {
                return a1[PRIORITY] - a2[PRIORITY];
            }
            return a1[IMPORT] - a2[IMPORT];
        });
        queue.offer(programs[idx++]);

        long[] answer = new long[11];
        long time = 0L;
        while(!queue.isEmpty()) {
            int[] program = queue.poll();
            long importTime = (long) program[IMPORT];
            long start = Math.max(time, importTime);
            long waitingTime = start - importTime;
            answer[program[PRIORITY]] += waitingTime;
            time = start + (long) program[EXECUTION];
            // System.out.println("---");
            // System.out.println(Arrays.toString(program) + "실행");
            // System.out.println(start + "에 시작했고, " + waitingTime + "기다림");
            while(idx < programs.length && programs[idx][IMPORT] <= time) {
                queue.offer(programs[idx++]);
            }
            if (queue.isEmpty() && idx < programs.length) {
                queue.offer(programs[idx++]);
            }
        }
        answer[0] = time;
        return answer;
    }
}

