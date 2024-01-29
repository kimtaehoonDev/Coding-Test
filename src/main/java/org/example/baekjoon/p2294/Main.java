package org.example.baekjoon.p2294;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실패 / DP
public class Main {

    public static int INF = 20000;

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
            if (coin > K) {
                break;
            }
            dp[coin] = 1;
            int temp = coin;
            while(temp <= K) {
                int i = 1;
                while (i * coin <= temp) {
                    int usedCnt = temp / (coin * i);
                    if (dp[temp - coin * usedCnt] == INF) {
                        i++;
                    } else {
                        break;
                    }
                }
                int usedCnt = temp / (coin * i);
                dp[temp] = Math.min(dp[temp], usedCnt + dp[temp - coin * usedCnt]);
                temp++;
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
