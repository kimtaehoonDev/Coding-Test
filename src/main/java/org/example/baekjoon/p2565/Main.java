package org.example.baekjoon.p2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 실패
// 아이디어 자체가 틀림(가장 교차점이 많은 순서대로 제거)
// https://www.acmicpc.net/board/view/84972 해당 부분에 반례가 있음. 다른 아이디어를 떠올려서 풀어보기

// 아이디어 참조 후 성공 / 1시간 고민
// (3개월 뒤) 또 실패... 위와 똑같은 아이디어로 접근하려 함.
// 또 언젠가 다시 풀어봐야 할 문제..
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] x = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int n1 = Integer.parseInt(input[0]);
            int n2 = Integer.parseInt(input[1]);
            x[i][0] = n1;
            x[i][1] = n2;
        }

        Arrays.sort(x, (n1, n2) -> {
            return n1[0] - n2[0];
        });
//        for (int[] ints : x) {
//            System.out.println(Arrays.toString(ints));
//        }

        int[] dp = new int[N];
        for(int i = 0; i<N; i++) {
//            int[] now = x[i];
            dp[i] = 1;

            for(int j = 0; j< i; j++) {
                if (x[i][1] > x[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
//            System.out.println(Arrays.toString(dp));
        }

        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        System.out.println(N - max);
    }
}
