package org.example.baekjoon.p14501;

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];// dp[x] : x일까지 벌 수 있는 최대 이익
        int[] due = new int[N + 1];
        int[] cost = new int[N + 1];

        for (int i = 0; i < N; i++) {
            dp[i] = -1;
        }
        StringTokenizer st;
        for (int start = 1; start < N + 1; start++) {
            st = new StringTokenizer(br.readLine());
            due[start] = Integer.parseInt(st.nextToken());
            cost[start] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            if (i + due[i] - 1 > N) { //범위를 벗어났을때
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], dp[i + due[i]] + cost[i]);
            }
        }

        System.out.println(dp[1]);
    }

}
