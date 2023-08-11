package org.example.baekjoon.p1328;

import java.util.Scanner;

// 어렵다. 시간 초과 날거 알았는데 다른 방법이 떠오르지 않음

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        long[][][] dp = new long[n+1][n+1][n+1];
        dp[1][1][1] = 1;
        for(int i=2;i<n+1;i++) {
            for(int j=1; j<l+1;j++) {
                for(int k=1; k<r+1;k++) {
                    dp[i][j][k] = (dp[i-1][j][k] * (i-2)) + dp[i-1][j-1][k] + dp[i-1][j][k-1];
                    dp[i][j][k] %= 1000000007;
                }
            }
        }
        System.out.println(dp[n][l][r]);
    }
}
