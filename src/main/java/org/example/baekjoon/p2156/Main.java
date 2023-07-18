package org.example.baekjoon.p2156;

import java.util.*;
import java.math.*;
import java.io.*;

public class Main {
    static int[] ary;
    static int[] dp;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ary = new int[N+1];
        dp = new int[N+1];
        for(int i=1; i<N+1; i++) {
            ary[i] = Integer.parseInt(br.readLine());
            dp[i] = -1;
        }
        System.out.println(solve(N));
    }

    public static int solve(int n) {
        if (n <= 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int max = -1;
        max = Math.max(max, solve(n-3) + ary[n-1] + ary[n]);
        max = Math.max(max, solve(n-2) + ary[n]);
        max = Math.max(max, solve(n-1));
        dp[n] = max;
        return dp[n];

    }
}
