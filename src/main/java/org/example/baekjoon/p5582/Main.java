package org.example.baekjoon.p5582;

import java.util.Scanner;

// 성공 / 11분 / dp
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] input1 = sc.nextLine().toCharArray();
        char[] input2 = sc.nextLine().toCharArray();

        int[][] dp = new int[input1.length][input2.length];
        int max = 0;
        for (int i = 0; i < input1.length; i++) {
            for (int j = 0; j < input2.length; j++) {
                if (input1[i] == input2[j]) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max);

    }
}
