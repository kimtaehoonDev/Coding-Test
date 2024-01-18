package org.example.baekjoon.p1562;

import java.util.Scanner;

// 답안 참조 후 풀이 / DP
// https://lotuslee.tistory.com/103
// DP인건 알겠는데 풀이를 떠올리지 못함
// 핵심은 비트마스킹. 비트마스킹을 사용해서 어떤 원소들을 골랐는지 저장해둔채로 사용이 가능.
// Ex. dp[1][2][(1110)] -> 1번째 자리가 2로 끝나는 원소 && 1,2,3번째 비트 선택된 경우가 몇 회인지 보여줌
public class Main {

    public static final int MOD = 1_000_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[][][] dp = new long[N+1][10][1<<10];
        for(int i = 1; i<=9; i++) {
            dp[1][i][1<<i] = 1;
        }
        for(int n = 2; n <= N; n++) {
            for(int i = 0; i<=9; i++) {
                for(int visit = 0; visit < (1<<10); visit++) {
                    int newVisit = visit | (1 << i);
                    if (i == 0) {
                        dp[n][i][newVisit] += dp[n - 1][i + 1][visit];
                    } else if (i == 9) {
                        dp[n][i][newVisit] += dp[n - 1][i - 1][visit];
                    } else {
                        dp[n][i][newVisit] += (dp[n - 1][i - 1][visit] + dp[n - 1][i + 1][visit]) % MOD;
                    }
                    dp[n][i][newVisit] %= MOD;
                }
            }
        }
        dp[1][0][1<<0] = 1;

        long answer = 0L;
        for(int num = 0; num <=9; num++) {
            answer += dp[N][num][(1<<10) - 1] % MOD;
            answer %= MOD;
        }
        System.out.println(answer);
    }
}
