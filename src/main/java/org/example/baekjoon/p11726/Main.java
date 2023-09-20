package org.example.baekjoon.p11726;

import java.util.Scanner;

// 성공/35분
public class Main {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] dp = new int[1001];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }

}
