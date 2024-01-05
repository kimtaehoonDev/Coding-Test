package org.example.baekjoon.p15989;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실패 -> 답지 참조해서 풀었음
// 점화식을 도저히 만들지 못해서 풀이를 참조
public class Main {
    public static void main(String[] args) throws IOException {
        // 계산
        int[][] dp = new int[10_001][4];
        dp[1][1] = 1; // 1
        dp[2][1] = 1; // 1+1
        dp[2][2] = 1; // 2
        dp[3][1] = 1; // 1+1+1
        dp[3][2] = 1; // 1+2
        dp[3][3] = 1; // 3

        for(int i = 4; i<10_001;i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] answer = new int[T];
        for(int i = 0; i<T; i++) {
            int idx = Integer.parseInt(br.readLine());
            answer[i] = dp[idx][1] + dp[idx][2] + dp[idx][3];
        }
        StringBuilder sb = new StringBuilder();
        for (int each : answer) {
            sb.append(each);
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
