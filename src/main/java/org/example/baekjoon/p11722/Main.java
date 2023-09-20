package org.example.baekjoon.p11722;

import java.util.Scanner;

// 1회 실패 후 성공/1시간20분

// 정답은 dp에서 가장 큰 값인데, dp[N]을 반환해서 틀림
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] A = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
//        System.out.println(Arrays.toString(A));

        for(int i=0; i<N; i++) {
            int max = 0;
            // 나보다 큰 값 중에서 가장 수열이 긴 녀석을 찾는다
            for(int j=0; j<i;j++) {
                if (A[i] < A[j]) {
                    if (dp[j] > max) {
                        max = dp[j];
                    }
                }
            }
            // 내 수열의 길이 == 해당 수열의 길이 + 1
            dp[i] = max + 1;
        }
//        System.out.println(Arrays.toString(dp));

        int max = 0;
        for(int each: dp) {
            max = Math.max(each, max);
        }
        System.out.println(max);
    }
}
