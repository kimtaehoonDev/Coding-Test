package org.example.baekjoon.p1912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] dpAboutConnect = new int[N+1];
        dp[0] = -1000000;
        dpAboutConnect[0] = -1000000;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            int num = Integer.parseInt(st.nextToken());
            dpAboutConnect[i] = Math.max(dpAboutConnect[i-1] + num, num);
            dp[i] = Math.max(dp[i-1], dpAboutConnect[i]);
        }
        System.out.println(dp[N]);
    }
}
