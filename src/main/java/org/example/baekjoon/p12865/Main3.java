package org.example.baekjoon.p12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 12분 / 배낭문제
// 배낭 문제 개념 머리속에 확실하게 들어간듯
// dp[j] = Math.max(dp[j], dp[j - weight] + value);
public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] dp = new int[K+1];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            for (int j = K; j >= 0; j--) {
                if (j < weight) {
                    break;
                }
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
//                System.out.println(weight + "," + value + "-> " + Arrays.toString(dp));
            }
        }
        System.out.println(dp[K]);
    }
}
