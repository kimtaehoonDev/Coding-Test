package org.example.baekjoon.p11053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 성공/15분/dp
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (ary[i] <= ary[j]) {
                    continue;
                }
                max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
        }

        int answer = 0;
        for (int each : dp) {
            answer = Math.max(answer, each);
        }
        System.out.println(answer);
    }
}
