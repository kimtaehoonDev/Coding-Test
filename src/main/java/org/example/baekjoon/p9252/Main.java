package org.example.baekjoon.p9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 답안 보고 풀이 / LCS를 DP로 어떻게 푸는지, 어떻게 다시 역추적을 하는지가 중요한 문제
// https://st-lab.tistory.com/139
public class Main {

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
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        System.out.println(dp[str1.length - 1][str2.length - 1]);
        if (dp[str1.length - 1][str2.length - 1] != 0) {
            System.out.println(makeLcs());
        }
    }

    private static String makeLcs() {
        int length = dp[str1.length - 1][str2.length - 1];
        char[] inputs = new char[length];

        int i = str1.length - 1;
        int j = str2.length - 1;
        int idx = inputs.length - 1;
        while(idx >= 0) {
            int top = get(i - 1, j);
            int left = get(i, j - 1);
            int now = get(i, j);
            if (now == top) {
                i--;
            } else if (now == left) {
                j--;
            } else {
                inputs[idx--] = str1[i];
//                System.out.println(i + ", " + j);
                i--;
                j--;
            }

        }
        return new String(inputs);
    }

    static int get(int i, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }
        return dp[i][j];
    }
}
