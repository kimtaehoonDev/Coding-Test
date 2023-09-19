package org.example.baekjoon.p9095;

import java.util.Arrays;
import java.util.Scanner;

// 성공/50분
// dp 연습이 필요할 거 같다
// 해당 문제 왜 이런 규칙이 나왔는지 고민했는데, 해당 블로그에 설명이 잘되어있다
// https://lotuslee.tistory.com/43
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[11];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<11; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
//        System.out.println(Arrays.toString(dp));
        for(int i=0; i<n; i++) {
            System.out.println(dp[sc.nextInt()]);
        }
    }
}