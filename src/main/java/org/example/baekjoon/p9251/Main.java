package org.example.baekjoon.p9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    static int[][] dp;
    static char[] str1;
    static char[] str2;

    public static void main(String[] args) throws IOException {
        // 입력 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        dp = new int[str1.length][str2.length];
        for(int i = 0; i<str1.length; i++) {
            for (int j = 0; j<str2.length; j++) {
                dp[i][j] = -1;
            }
        }
        solve(str1.length - 1, str2.length - 1);
        System.out.println(dp[str1.length-1][str2.length - 1]);
    }

    static int solve(int col, int row) {
        if (col == -1 || row == -1) {
            return 0;
        }
        if (dp[col][row] != -1) {
            return dp[col][row];
        }
        if (str1[col] == str2[row]) {
            dp[col][row] = solve(col-1, row-1) + 1;
        } else {
            dp[col][row] = Math.max(solve(col-1, row), solve(col, row - 1));
        }
        return dp[col][row];
    }

}
