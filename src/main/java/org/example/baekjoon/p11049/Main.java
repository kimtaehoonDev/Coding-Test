package org.example.baekjoon.p11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 29분 / DP
// 사실 1회 실패함
// 실패 이유 -> 최대값 똑바로 안줘서 틀림. 값의 범위가 2^31 - 1인데, 임시로 써둔값을 그냥 그대로 써버림

// 풀기는 풀었는데 점화식 만들기가 너무 어려웠던 문제
public class Main {

    public static final int CNT = 0;
    public static final int X = 1;
    public static final int Y = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dp[i][i][X] = Integer.parseInt(st.nextToken());
            dp[i][i][Y] = Integer.parseInt(st.nextToken());
        }
//
//        for(int i = 0; i< N-1; i++) {
//            // i와 i+1까지 연산
//            dp[i][i+1][CNT] = dp[i][i][X] *  dp[i][i][Y] * dp[i+1][i+1][Y];
//            dp[i][i+1][X] = dp[i][i][X];
//            dp[i][i+1][Y] = dp[i+1][i+1][Y];
//        }

        for (int gap = 1; gap < N; gap++) {
            for (int i = 0; i < N - gap; i++) {
                // dp[i][i + gap] 초기화중
                int cnt = Integer.MAX_VALUE;
                int x = 0;
                int y = 0;
                // dp[i][i] 부터 dp[i][i+?]까지
                for(int start = i; start < i + gap; start ++) {
                    int temp = dp[i][start][X] * dp[i][start][Y] * dp[start + 1][i + gap][Y];
                    temp += dp[i][start][CNT] + dp[start + 1][i + gap][CNT];
                    if (temp < cnt) {
                        cnt = temp;
                        x = dp[i][start][X];
                        y = dp[start + 1][i + gap][Y];
                    }
//                    System.out.println(temp);
//                    System.out.println(x);
//                    System.out.println(y);
//                    System.out.println("-=-");
                }
                dp[i][i + gap][CNT] = cnt;
                dp[i][i + gap][X] = x;
                dp[i][i + gap][Y] = y;
            }
        }


//        for (int[][] ints : dp) {
//            for (int[] anInt : ints) {
//                System.out.println(Arrays.toString(anInt));
//            }
//        }
        System.out.println(dp[0][N-1][CNT]);
    }
}
