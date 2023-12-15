package org.example.baekjoon.p9461;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) throws IOException {
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for (int i = 6; i < 101; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < T; i++) {
            int x = Integer.parseInt(br.readLine());
            sj.add(String.valueOf(dp[x]));
        }
        System.out.println(sj.toString());
    }
}
