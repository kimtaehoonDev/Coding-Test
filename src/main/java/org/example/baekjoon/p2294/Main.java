package org.example.baekjoon.p2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 답지 보고 풀이 / DP
// 너무 어렵게 생각했다. 나중에 다시 풀어보기
public class Main {

    public static int INF = 200000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for(int i = 0; i<N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);

        int[] dp = new int[K+1]; // dp 최대값은 10000정도
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int coin : coins) {
            for(int i = coin; i<=K; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
//            System.out.println(Arrays.toString(dp));
        }
        if (dp[K] >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(dp[K]);
        }
    }
}
