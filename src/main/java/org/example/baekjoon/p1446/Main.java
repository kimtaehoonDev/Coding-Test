package org.example.baekjoon.p1446;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 성공 / Dp / 35분
public class Main {
    public static final int START = 0;
    public static final int END = 1;
    public static final int COST = 2;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] dp = new int[D+1];
        for(int i = 1; i<=D; i++) {
            dp[i] = i;
        }
//        System.out.println(Arrays.toString(dp));

        int[][] inputs = new int[N][3];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            inputs[i][0] = Integer.parseInt(st.nextToken());
            inputs[i][1] = Integer.parseInt(st.nextToken());
            inputs[i][2] = Integer.parseInt(st.nextToken());
        }
        // 도착지(1번쨰)가 짧은 순서대로 정렬
        Arrays.sort(inputs, (ary1, ary2) -> {
            return ary1[1] - ary2[1];
        });
//        for (int[] input : inputs) {
//            System.out.println(Arrays.toString(input));
//        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i<N; i++) {
            int[] input = inputs[i];
            if (input[END] > D) {
                continue;
            }
            // START 위치 도착하기까지 최소값 계산
            for(Integer boongi : set) {
                if (boongi >= input[START]) {
                    continue;
                }
                dp[input[START]] = Math.min(dp[input[START]], dp[boongi] + input[START] - boongi);
            }

            dp[input[END]] = Math.min(dp[input[END]], dp[input[START]] + input[COST]);
            for(Integer boongi : set) {
                if (boongi >= input[START]) {
                    continue;
                }
                dp[input[END]] = Math.min(dp[input[END]], dp[boongi] + input[END] - boongi);
            }
//            System.out.println(input[END] + "에서 " + dp[input[END]]);
            set.add(input[END]);
        }

        for(Integer boongi : set) {
            dp[D] = Math.min(dp[D], dp[boongi] + D - boongi);
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[D]);
    }
}
