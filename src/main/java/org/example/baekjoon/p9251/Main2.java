package org.example.baekjoon.p9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {

    static int[][] dp;
    static char[] str1;
    static char[] str2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        dp = new int[str1.length][str2.length];
        for(int i = 0; i< str1.length; i++) {
            for(int j = 0; j<str2.length; j++) {
                if (str1[i] == str2[j]) {
                    dp[i][j] = get(i - 1, j - 1) + 1;
                } else {
                    dp[i][j] = Math.max(get(i, j - 1), get(i - 1, j));
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println(dp[str1.length - 1][str2.length - 1]);
    }

    static int get(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return dp[i][j];
    }
}
