package org.example.baekjoon.p2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 실패 후 성공 / 30분
// 아이디어는 금방 떠올렸으나 엣지 케이스를 잡지 못한 문제
// dp[0], dp[1], dp[2] 를 하드코딩했으면 해당 부분에서 예외가 일어날 거 예상했어야 함. 그런데 예상하지 못했음

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int CNT = Integer.parseInt(br.readLine());
        int[] values = new int[CNT + 1];
        int[] dp = new int[CNT + 1];

        for(int i=1; i<=CNT; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println(Arrays.toString(values));

        dp[0] = 0;
        dp[1] = values[1];
        if (CNT >= 2) {
            dp[2] = values[1] + values[2];
        }

        for(int i=3; i<=CNT; i++) {
            dp[i] = values[i] + Math.max(dp[i - 2], dp[i - 3] + values[i - 1]);
        }
        System.out.println(dp[CNT]);
    }
}