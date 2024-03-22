package org.example.baekjoon.p4811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

// 실패 / DP/ 2시간 -> 답안 참조 후 해결
// 원래 DP인거 알았고 나름대로 규칙도 찾았는데
// 찾아낸 규칙을 DP에 적용하기에는 너무 복잡했음. 다른 방법으로 해보려 했으나 실패
// https://cpp-dev.tistory.com/68 -> 해당 블로그를 통해 접근 방식을 이해함
public class Main {

    public static void main(String[] args) throws IOException {
        long[][] dp = new long[31][31];
        for (int i = 0; i < 31; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < 31; i++) {
            for (int j = 0; j < 31; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j + 1];
                    continue;
                }
                if (j == 30) {
                    dp[i][j] = dp[i][j - 1];
                    continue;
                }
                dp[i][j] = dp[i - 1][j + 1] + dp[i][j - 1];
            }
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input;
        List<Long> answers = new ArrayList<>();
        while ((input = Integer.parseInt(br.readLine())) != 0) {
            answers.add(dp[input - 1][1]);
        }
        StringJoiner sj = new StringJoiner("\n");
        for (Long answer : answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }
}
