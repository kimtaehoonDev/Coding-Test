package org.example.baekjoon.p2193;

import java.util.*;

public class Main {
    static long[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new long[N+1];
        long result = solve(N);
        System.out.println(result);
    }

    static long solve(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        dp[n] = solve(n-1) + solve(n-2);
        return dp[n];
    }
}
