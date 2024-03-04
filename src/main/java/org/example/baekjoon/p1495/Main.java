package org.example.baekjoon.p1495;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 25분 / 성공 / DP
public class Main {

    // N : 곡 개수
    // S : 시작 볼륨
    // M : 맥스값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] sounds = new int[N];
        for (int i = 0; i < N; i++) {
            sounds[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] : dp[i]일 때, 볼륨 i에서 시작한다.
        // ex. dp[0] = 5 라는 건, 0번째 순서에서 볼륨 5로 시작할 수 있다.
        int[] dp = new int[M + 1];
        Arrays.fill(dp, -1);
        dp[S] = 0;
        for (int i = 0; i < N; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= M; j++) {
                if (i == dp[j]) {
                    temp.add(j);
                }
            }

//            System.out.println("temp " + temp);
            for (Integer each : temp) { // i번째 순서에서 볼륨 each로 시작할 수 있다.
                if (each - sounds[i] >= 0) {
                    dp[each - sounds[i]] = i + 1;
                }
                if (each + sounds[i] <= M) {
                    dp[each + sounds[i]] = i + 1;
                }
            }
//            System.out.println(Arrays.toString(dp));

        }
        for (int i = M; i >= 0; i--) {
            if (dp[i] == N) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
//        System.out.println(Arrays.toString(dp));
    }}
