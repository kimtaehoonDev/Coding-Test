package org.example.baekjoon.p14501;

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    static List<Consulting> consultings;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];// dp[x] : x일까지 벌 수 있는 최대 이익
        for(int i=0; i<N+1; i++) {
            dp[i] = -1;
        }
        StringTokenizer st;
        consultings = new ArrayList<>(N);
        for(int start=1; start<N+1; start++) {
            st = new StringTokenizer(br.readLine());

            int due = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            consultings.add(new Consulting(start, start + due - 1, cost));
        }
        dp[0] = 0;
        for(int day = 1; day < N+1; day++) { //1일부터 N일까지
            int max = 0;
            for(int i = 0; i < day; i++) {
                int cost = findMaxCost(i, day); // i+1일부터 day까지 벌 수 있는 단일 최대 금액
                max = Math.max(max, dp[i] + cost);
            }
            dp[day] = max;
        }

        System.out.println(dp[dp.length - 1]);
    }

    private static int findMaxCost(int start, int end) {
        int max = 0;
        for (Consulting consulting : consultings) {
            if (start < (consulting.start) && consulting.end <= end) {
                max = Math.max(max, consulting.cost);
            }
        }
        return max;
    }

    static class Consulting {
        int start;
        int end;
        int cost;

        public Consulting(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
