package org.example.programmers;

import java.util.*;

// 효율성 실패 -> 접근 방식 자체가 틀림.
// 나는 우선순위 큐를 사용해서 문제를 풀었고, 시간으 ㄹ단축하기 위해 HashMap을 사용함
// 그런데 애초에 이 문제는 DP 문제라는거
class 코딩_테스트_공부 {
    static final int ALP_REQ = 0;
    static final int COP_REQ = 1;
    static final int ALP_RWD = 2;
    static final int COP_RWD = 3;
    static final int COST = 4;

    static int MAX_ALP;
    static int MAX_COP;

    static Map<Integer, Integer> hashAlp = new HashMap<>(); // key : 시간, value : 해당 시간에 가질 수 있는 최대 알고력
    static Map<Integer, Integer> hashCop = new HashMap<>(); // 최대 코테력

    public int solution(int alp, int cop, int[][] problems) {
        // 시간이 빠른 순서로 정렬
        calculateMaxPower(problems);
        // System.out.println(MAX_ALP + " : " + MAX_COP);
        Queue<Status> heap = new PriorityQueue<>((x1, x2) -> x1.time - x2.time) ;

        Status first = new Status(0, alp, cop);
        heap.offer(first);

        while(!heap.isEmpty()) {
            Status now = heap.poll();
            // System.out.println(now);
            if (now.alpPower >= MAX_ALP && now.copPower >= MAX_COP) {
                return now.time; // 해당 시간에 도달했을 때 모든 문제를 풀 수 있음
            }
            Status next = renew(now, 1, 1, 0);
            if (next != null) {
                heap.offer(next);
            }
            next = renew(now, 1, 0, 1);
            if (next != null) {
                heap.offer(next);
            }
            // heap.offer(new Status(now.time + 1, now.alpPower + 1, now.copPower));
            // heap.offer(new Status(now.time + 1, now.alpPower, now.copPower + 1));

            for(int[] problem : problems) {
                if (canSolve(now, problem)) {
                    next = renew(now, problem[COST], problem[ALP_RWD], problem[COP_RWD]);
                    if (next != null) {
                        heap.offer(next);
                    }
                    // heap.offer(new Status(now.time + problem[COST], now.alpPower + problem[ALP_RWD], now.copPower + problem[COP_RWD]));
                }
            }
        }
        throw new IllegalArgumentException("도달할 수 없음");
    }

    public Status renew(Status status, int timeReward, int alpReward, int copReward) {
        boolean isBiggerThanPrevAlp = true;
        boolean isBiggerThanPrevCop = true;

        if (hashAlp.containsKey(status.time)) {
            int prevAlp = hashAlp.get(status.time); // 기존 최대 점수
            if (prevAlp >= status.alpPower + alpReward) {
                isBiggerThanPrevAlp = false;
            }
        }
        if (hashCop.containsKey(status.time)) {
            int prevCop = hashCop.get(status.time); // 기존 최대 점수
            if (prevCop >= status.copPower + copReward) {
                isBiggerThanPrevCop = false;
            }
        }

        // 만약 알고력, 코테력이 둘다 이전보다 작거나 같으면
        if (!isBiggerThanPrevAlp && !isBiggerThanPrevCop) {
            return null;
        }
        return new Status(status.time + timeReward, status.alpPower + alpReward, status.copPower + copReward);
    }

    boolean canSolve(Status status, int[] problem) {
        return status.alpPower >= problem[ALP_REQ] && status.copPower >= problem[COP_REQ];
    }

    void calculateMaxPower(int[][] problems) {
        for(int[] problem : problems) {
            this.MAX_ALP = Math.max(MAX_ALP, problem[0]);
            this.MAX_COP = Math.max(MAX_COP, problem[1]);
        }
    }


    // time 기준 알고리즘력, 코테력 기록
    static class Status {
        int time;
        int alpPower;
        int copPower;

        public Status(int time, int alpPower, int copPower) {
            this.time = time;
            this.alpPower = alpPower;
            this.copPower = copPower;
        }

        public String toString() {
            return "(time : " + time + ", 알고 : " + alpPower + ", 코테 : " + copPower + ")";
        }
    }
}