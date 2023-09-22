package org.example.baekjoon.p15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
//        System.out.println(Arrays.toString(T));
//        System.out.println(Arrays.toString(P));
        for (int i = 1; i <= N; i++) {
            int end = i + T[i] - 1;
            if (end <= N) {
                //이전값
                dp[end] = Math.max(dp[end], P[i] + dp[i - 1]);
            }
            dp[i] = Math.max(dp[i - 1], dp[i]);
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);
    }
}