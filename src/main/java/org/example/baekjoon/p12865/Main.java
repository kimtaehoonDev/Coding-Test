package org.example.baekjoon.p12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 답안 참조 후 풀이 -> 물건을 쪼갤 수 없는 배낭 문제는 DP로 푼다.
public class Main {

    static int WEIGHT = 0;
    static int VALUE = 1;

    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        int[] weights = new int[N + 1];
        int[] values = new int[N + 1];
        int[][] dp = new int[N + 1][K + 1];

        for(int i =1; i<=N; i++) {
            input = br.readLine().split(" ");
            weights[i] = Integer.parseInt(input[0]);
            values[i] = Integer.parseInt(input[1]);
        }

        for(int i =1; i<=N; i++) {
            for(int j = 1; j<=K; j++) {
                if (weights[i] > j) {
                    // i번째 물건을 담을 수 없다
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // i번째 물건을 담을 수 있다.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }

}
