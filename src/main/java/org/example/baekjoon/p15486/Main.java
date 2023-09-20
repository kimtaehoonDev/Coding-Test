package org.example.baekjoon.p15486;

import java.util.PriorityQueue;
import java.util.Scanner;

// 실패 - 시간 초과를 해결할 방법이 떠오르지 안흥ㅁ
// 잘못 점화식을 세운거 같다 판단해 문제 그만 품

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Schedule> heap = new PriorityQueue<>((s1, s2) -> {
            if (s1.end == s2.end) {
                return s2.cost - s1.cost;
            }
            return s1.end - s2.end;
        });
        int[] dp = new int[N+1];

        for(int start=1; start<=N; start++) {
            int work = sc.nextInt();
            int cost = sc.nextInt();
            if (start + work - 1 > N) {
                continue;
            }
            heap.offer(new Schedule(start, start + work - 1, cost));
        }

        while (!heap.isEmpty()) {
            Schedule schedule = heap.poll();
//            System.out.println(schedule);
            if (dp[schedule.end] < schedule.cost + dp[schedule.start - 1]) {
                for(int i=schedule.end; i<=N; i++) {
                    dp[i] = schedule.cost + dp[schedule.start - 1];
                }
            }
        }
        System.out.println(dp[N]);

    }

    static class Schedule {
        int start;
        int end;
        int cost;

        public Schedule(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public String toString() {
            return "(" + start + ", " + end + ") - cost : " + cost;
        }
    }
}
