package org.example.baekjoon.p2240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[][] dp = new int[T+1][W+1];

        for (int i = 1; i <= T; i++) {
            int input = Integer.parseInt(br.readLine()) - 1;

            for (int j = 0; j <= W; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                }
                if (input == j % 2) {
                    dp[i][j] += 1;
                }
            }
        }
        int[] ints = dp[T];
        int max = 0;
        for (int each : ints) {
            max = Math.max(each, max);
        }
        System.out.println(max);
    }
}
