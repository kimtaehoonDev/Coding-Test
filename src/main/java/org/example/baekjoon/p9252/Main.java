package org.example.baekjoon.p9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 1시간 풀이 : 백트래킹 & DP로 풀었음. 더 최적화된 풀이가 있을 거 같은데 생각이 나지 않아 해당 방식으로 품 -> 시간초과..

public class Main {

    static char[] str1;
    static char[] str2;
    static int[][] dp;
    static int max = -1;
    static String maxStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        dp = new int[str1.length][str2.length];


        for (int i = 0; i < str1.length; i++) {
            solve(i, 0, 0, new ArrayList<>());
        }
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        if (max <= 0) {
            System.out.println(0);
            return;
        }
        System.out.println(max);
        System.out.println(maxStr);
    }

    public static void solve(int s1, int s2, int length, List<Character> characters) {
//        System.out.println(s1 + ", " + s2 + "-> " + length);
        for (int i = s1; i < str1.length; i++) {
//            System.out.println("->" + i + ", " + s2 + "-> " + length);
            boolean isChanged = false;
            int copyS2 = s2;
            while (i < str1.length && copyS2 < str2.length) {
                if (str1[i] != str2[copyS2]) {
                    copyS2++;
                    continue;
                }
                if (dp[i][copyS2] < length + 1) {
                    dp[i][copyS2] = length + 1;
                    if (max < dp[i][copyS2]) {
                        max = dp[i][copyS2];
                        characters.add(str1[i]);
                        maxStr = makeStr(characters);
                        isChanged = true;
                    }
                    solve(i + 1, copyS2 + 1, length + 1, characters);
                    if (isChanged) {
                        characters.remove(characters.size() - 1);
                    }
                }
                break;
            }
        }
    }

    private static String makeStr(List<Character> characters) {
        StringBuilder sb = new StringBuilder();
        for (Character character : characters) {
            sb.append(character);
        }
        return sb.toString();
    }
}
