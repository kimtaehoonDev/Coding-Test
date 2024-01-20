package org.example.baekjoon.p2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 다시 풀이 -> 성공 / 10분
// -> DP를 풀 때 범위를 여유있게 잡는게 편하다. 이 문제 같은 경우 입력값이 300까지니까 그냥 301 크기로 dp 만들면 예외케이스 고민할 필요가 없음
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int CNT = Integer.parseInt(br.readLine());
        int[] values = new int[301];
        int[] dp = new int[301];

        for(int i=1; i<=CNT; i++) {
            values[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println(Arrays.toString(values));

        dp[0] = 0;
        dp[1] = values[1];
        dp[2] = values[1] + values[2];

        for(int i=3; i<=CNT; i++) {
            dp[i] = values[i] + Math.max(dp[i - 2], dp[i - 3] + values[i - 1]);
        }
        System.out.println(dp[CNT]);
    }
}