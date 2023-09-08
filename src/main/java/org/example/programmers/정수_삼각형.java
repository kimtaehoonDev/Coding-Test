package org.example.programmers;

// 성공/20분
public class 정수_삼각형 {
    class Solution {
        public int solution(int[][] triangle) {
            // dp의 값을 -1로 초기화
            int[][] dp = new int[triangle.length][triangle[triangle.length-1].length];
            for(int i = 0; i<dp.length;i++) {
                for(int j=0; j<dp.length;j++) {
                    dp[i][j] = -1;
                }
            }

            int max = 0;
            // dp[0][0] 에 triangle[0][0] 넣기
            dp[0][0] = triangle[0][0];
            for(int i=0; i<triangle.length; i++) {
                for(int j=0; j<i+1; j++) {
                    // 마지막 라인일 때(Edge Case)
                    if (i == triangle.length - 1) {
                        // 최대값 갱신
                        max = Math.max(max, dp[i][j]);
                        continue;
                    }

                    // 왼쪽으로 이동했을 때
                    if (dp[i+1][j] != -1) { // 방문한적 O
                        dp[i+1][j] = Math.max(dp[i+1][j], dp[i][j] + triangle[i+1][j]);
                    } else { // 방문한 적 X
                        dp[i+1][j] = dp[i][j] + triangle[i+1][j];
                    }

                    // 오른쪽 이동
                    if (dp[i+1][j+1] != -1) { // 방문한적 O
                        dp[i+1][j+1] = Math.max(dp[i+1][j+1], dp[i][j] + triangle[i+1][j+1]);
                    } else { // 방문한 적 X
                        dp[i+1][j+1] = dp[i][j] + triangle[i+1][j+1];
                    }
                }
            }


            return max;
        }
    }
}
