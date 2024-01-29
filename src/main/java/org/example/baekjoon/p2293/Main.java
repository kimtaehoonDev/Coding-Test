package org.example.baekjoon.p2293;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 36분 / dp
// 아이디어 떠올리는데 시간이 꽤나 걸림
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coins = new int[N];
        int[] dp = new int[K+1];
        for(int i = 0; i<N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins);
//        System.out.println(Arrays.toString(coins));

        for(int coin : coins) {
            if (coin > K) { // 만원 만들어야 하는데 십만원 들어온 경우 -> 볼 필요도 없음
                break;
            }
            dp[coin]++;
            int temp = coin + 1;
            while(temp <= K) {
                dp[temp] += dp[temp - coin];
                temp ++;
            }
//            System.out.println(coin + "사용했을 때 결과");
//            System.out.println(Arrays.toString(dp));
        }
        System.out.println(dp[K]);
    }
}
