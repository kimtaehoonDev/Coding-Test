package org.example.baekjoon.p1943;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

// 1시간 고민 후 포기 -> 유형 확인(Dp, 배낭문제)
// 1시간 더 풀고 포기 -> 이차원으로는 해결할 수 없는 부분
// 1회 실패 후 성공
//
// 설명)
// 만들어야 하는 숫자 j를 큰 값부터 돌려야 함.
// 10원 3개, 50원 1개 있다고 가정하겠다.
// 만약 작은 숫자부터 돌리면 dp[30]이 true가 되고, 여기에서 dp[40]을 확인하면 dp[40 - 10]이 true라서 된다고 결과가 잘못 나옴
public class Main {
    static final int VAL = 0;
    static final int CNT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] results = new int[3];
        for(int T = 0; T<3; T++) {
            int N = Integer.parseInt(br.readLine());
            int[][] coins = new int[N][2];
            int total = 0;
            for(int i = 0; i<N; i++) {
                String[] input = br.readLine().split(" ");
                coins[i][VAL] = Integer.parseInt(input[0]);
                coins[i][CNT] = Integer.parseInt(input[1]);
                total += coins[i][VAL] * coins[i][CNT];
            }
            Arrays.sort(coins, (c1, c2) -> c1[0] - c2[0]); // 동전가치 오름차순
            results[T] = solve(coins, total);
        }
        StringJoiner sj = new StringJoiner("\n");
        for (int result : results) {
            sj.add(String.valueOf(result));
        }
        System.out.println(sj);
    }

    static int solve(int[][] coins, int total) {
        if (total % 2 == 1) {
            return 0; // 불가능
        }
        int HALF = total / 2;
        boolean[] dp = new boolean[HALF+1];
        dp[0] = true;

        for(int i = 0; i<coins.length; i++) { // i번째 동전을 선택한다.
            for(int j = HALF; j>=coins[i][VAL]; j--) { // j를 만들 수 있냐?
                if (dp[j]) {
                    continue;
                }
                for(int k = 1; k <=coins[i][CNT]; k++) { // k개 선택한다.
                    if (j - k * coins[i][VAL] < 0) {
                        break;
                    }
                    dp[j] = dp[j - k * coins[i][VAL]];
                    if (dp[j]) {
                        break;
                    }
                }
            }
        }

//        int idx =0;
//        for (boolean b : dp) {
//            System.out.println(idx++ + ":"+b);
//        }
        if (dp[HALF]) {
            return 1;
        } else {
            return 0;
        }
    }
}
