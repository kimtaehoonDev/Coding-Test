package org.example.baekjoon.p1149;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 15분 / dp
public class Main {

    public static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N+1][3];
        for(int i = 1 ; i <= N ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for (int[] ints : graph) {
//            System.out.println(Arrays.toString(ints));
//        }

        int[][] dp = new int[N+1][3];
        dp[1][0] = graph[1][0];
        dp[1][1] = graph[1][1];
        dp[1][2] = graph[1][2];

        for (int i = 2; i<=N; i++) {
            for(int j = 0; j<3; j++) {
                int min = INF;
                for (int k = 0; k < 3; k++) {
                    if (j == k) {
                        continue;
                    }
                    min = Math.min(min, dp[i - 1][k]);
                }
                dp[i][j] = min + graph[i][j];
            }
        }
        int[] results = dp[N];
        int min = Arrays.stream(results).min().getAsInt();
//        int min = INF;
//        for (int result : results) {
//            min = Math.min(min, result);
//        }
        System.out.println(min);
    }
}
