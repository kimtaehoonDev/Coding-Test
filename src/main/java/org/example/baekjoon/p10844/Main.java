package org.example.baekjoon.p10844;

import java.util.*;
import java.io.*;

public class Main {
    static long[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N == 1) {
            System.out.println("9");
            return;
        }
        dp = new long[N + 1][10];

        dp[N][0] = solve(N - 1, 1);
        for (int i = 1; i < 9; i++) {
            dp[N][i] = solve(N - 1, i - 1) + solve(N - 1, i + 1);
        }
        dp[N][9] = solve(N - 1, 8);

        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[N][i]) % 1_000_000_000;
        }
        System.out.println(sum);
    }

    public static long solve(int digit, int endNum) {
        if (endNum < 0 || endNum > 9) {
            return 0;
        }
        if (digit == 1) {
            if (endNum == 0) {
                return 0;
            }
            return 1;
        }

        //초기화가 된 적 있다면
        if (dp[digit][endNum] != 0) {
            return dp[digit][endNum];
        }

        dp[digit][endNum] =
            (solve(digit - 1, endNum - 1) +
                solve(digit - 1, endNum + 1)) % 1_000_000_000;
        return dp[digit][endNum];
    }
}
