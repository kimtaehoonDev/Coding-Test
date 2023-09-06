package org.example.programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 성공, 15분
public class 숫자_변환하기 {
    class Solution {
        static int[] dp;
        public int solution(int x, int y, int n) {
            dp = new int[1_000_001];
            Arrays.fill(dp, -1);
            dp[x] = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(x);

            while(!queue.isEmpty()) {
                Integer target = queue.poll();
                if (target == y) {
                    return dp[target];
                }

                int next = target + n;
                if (canGo(next)) {
                    queue.add(next);
                    dp[next] = dp[target] + 1;
                }

                next = target * 2;
                if (canGo(next)) {
                    queue.add(next);
                    dp[next] = dp[target] + 1;
                }

                next = target * 3;
                if (canGo(next)) {
                    queue.add(next);
                    dp[next] = dp[target] + 1;
                }
            }

            return -1;
        }

        public boolean canGo(int num) {
            if (num < 0 || 1_000_000 < num) {
                return false;
            }
            return dp[num] == -1;
        }
    }
}
