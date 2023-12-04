package org.example.baekjoon.p1904;

import java.util.Scanner;

// 첫 번째. N이 1일 때, 2일 때, ... 정답이 몇인지 확인하며 규칙을 찾으려 했는데 N이 4일때 개수를 잘못 세서 식을 못세움
// 그리고 dp[2]를 초기화하기 때문에 최소 크기는 2가 나와야 하는데 그걸 처리하지 않음.
// 이렇게 하드코딩 할 때 범위를 꼭 체크해야 한다
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[Math.max(N+1,3)];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i<=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 15746;
        }
        System.out.println(dp[N]);
    }
}
