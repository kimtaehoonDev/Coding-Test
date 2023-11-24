package org.example.programmers;

import java.util.*;

// 다른 분들의 풀이를 참고해서 품
// 이 문제는 DP. 왜냐, 알고력과 코딩력마다 일정 시간이 걸림
// 1분동안 알고리즘 공부, 코딩 공부, 또는 문제 풀기 전략을 선택해서 알고력 코딩력 높일 수 있음
// 이 과정에서 과거의 알고력, 코딩력을 기반으로 새로운 알고력, 코딩력이 만들어짐. 그러니까 DP라고 볼 수 있음
// 주의해야 하는 건 초기값 설정, 그리고 범위를 벗어났을 경우를 신경써줘야함

class 코딩_테스트_공부 {
    static final int ALGO_POWER = 0;
    static final int CODING_POWER = 1;
    static final int ALGO_BONUS = 2;
    static final int CODING_BONUS = 3;
    static final int COST = 4;

    static final int INF = 1000000000;

    public int solution(int alpInit, int copInit, int[][] problems) {
        int maxAlgoPower = alpInit;
        int maxCodingPower = copInit;
        for(int[] problem: problems) {
            maxAlgoPower = Math.max(maxAlgoPower, problem[ALGO_POWER]);
            maxCodingPower = Math.max(maxCodingPower, problem[CODING_POWER]);
        }
        // System.out.println(maxAlgoPower + " "+ maxCodingPower);
        int[][] dp = new int[maxAlgoPower+1][maxCodingPower+1];
        for(int[] line : dp) {
            Arrays.fill(line, INF);
        }
        // System.out.println(Arrays.toString(dp[0]));
        alpInit = Math.min(alpInit, maxAlgoPower);
        copInit = Math.min(copInit, maxCodingPower);
        dp[alpInit][copInit] = 0;

        for(int i = alpInit; i<= maxAlgoPower; i++) {
            for(int j = copInit; j<= maxCodingPower; j++) {
                if (i + 1 <= maxAlgoPower) {
                    dp[i+1][j] = Math.min(dp[i][j] + 1, dp[i+1][j]);
                }
                if (j + 1 <= maxCodingPower) {
                    dp[i][j+1] = Math.min(dp[i][j] + 1, dp[i][j+1]);
                }

                for(int[] problem : problems) {
                    // TODO 각 문제들에 따라 값을 계산한다
                    if (problem[ALGO_POWER] <= i && problem[CODING_POWER] <= j) {
                        // if (i + problem[ALGO_BONUS] <= maxAlgoPower && j + problem[CODING_BONUS] <= maxCodingPower) {
                        int nextAlgo = Math.min(maxAlgoPower, i + problem[ALGO_BONUS]);
                        int nextCoding = Math.min(maxCodingPower, j + problem[CODING_BONUS]);
                        dp[nextAlgo][nextCoding] = Math.min(dp[i][j] + problem[COST], dp[nextAlgo][nextCoding]);

                    }
                }
            }
        }

//         for(int[] e : dp) {
//             System.out.println(Arrays.toString(e));
//         }

        return dp[maxAlgoPower][maxCodingPower];
    }
}