package org.example.baekjoon.p12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 정답 / 배낭문제 / 35분
public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int N = Integer.parseInt(input1[0]);
        int K = Integer.parseInt(input1[1]);
        int[][] products = new int[N][2];
        for(int i = 0; i<N; i++) {
            String[] input2 = br.readLine().split(" ");
            products[i][0] = Integer.parseInt(input2[0]); // 무게
            products[i][1] = Integer.parseInt(input2[1]); // 가치
        }

        int[][] dp = new int[K+1][N+1]; // 0row, 0col 비워두기
        for(int j = 1; j<=N; j++) {
            int[] product = products[j - 1];
            int weight = product[0];
            int value = product[1];
            for(int i = 1; i<=K; i++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);

                if (i - weight >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - weight][j-1] + value);
                }
            }
        }
//        for (int[] ints : dp) {
//            System.out.println(Arrays.toString(ints));
//        }
        System.out.println(dp[K][N]);
    }
}
