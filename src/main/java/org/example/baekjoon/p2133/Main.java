package org.example.baekjoon.p2133;

import java.util.*;

// 아이디어는 생각을 잘 했으나, 6번째 경우의 수를 잘 생각하지 않았음(그림그리지 않음)
// => 제대로 된 규칙을 찾아내지 못함
public class Main {
    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        dp = new int[N+1];
        long result = solve(N);
        System.out.println(result);
    }

    static int solve(int n) {
        if (n % 2 == 1) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            return 3;
        }
        int temp = solve(n-2) * solve(2);
        for(int i = n-4; i>=0; i = i - 2) {
            temp += solve(i) * 2;
        }
        dp[n] = temp;
        return dp[n];
    }
}
