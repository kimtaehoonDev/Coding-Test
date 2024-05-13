package org.example.baekjoon.p1699;

import java.util.Arrays;
import java.util.Scanner;

// 실패 / 38분 고민 / 풀이 과정 틀림
// 실패 원인 : 반례가 있음

// 반례 확인한 뒤에 새로운 풀이 만들어서 제출 -> 성공 // 23분 소요
// 꽤나 어려운 문제였다. 규칙을 찾기 힘든 문제
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 최대 제곱수 찾기
        int maxSquare = 0;
        for(int i = 1; i <= N; i++) {
            if (i * i > N) {
                maxSquare = i - 1;
                break;
            }
            dp[i*i] = 1;
        }

//        System.out.println(Arrays.toString(dp));
        for(int i = 1; i<=N; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                continue;
            }
            for(int j = 1; j<=maxSquare; j++) { // i를 만들기 위해 제곱수 + dp[i-제곱수] 해보는중
                if (j * j > N) {
                    break;
                }
                if (i <= j * j) {
                    break;
                }
                if (dp[i - j*j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j*j] + dp[i - j*j]);
                }
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N]);

    }

}
