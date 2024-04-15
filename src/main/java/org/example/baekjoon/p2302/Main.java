package org.example.baekjoon.p2302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 성공 / 27분 / dp
// dp라는 걸 알아서 그런가 어려움 없이 풀었음
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<Integer> vips = new ArrayList<>();
        for(int i = 0; i<M; i++) {
            vips.add(Integer.parseInt(br.readLine()));
        }

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i<41; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
//        System.out.println(Arrays.toString(dp));

        int answer = 1;
        int start = 1;
        for(int vip : vips) {
            answer *= dp[vip - start];
//            System.out.println(vip - start);
            start = vip + 1;
        }
        answer *= dp[N+1-start];
//        System.out.println(N+1 - start);
        System.out.println(answer);
    }
}
