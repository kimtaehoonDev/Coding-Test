package org.example.programmers;

import java.util.PriorityQueue;

// 성공/16분

public class 디펜스_게임 {
    class Solution {
        public int solution(int n, int k, int[] enemy) {
            int round = 0;
            PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            for(int enemyInStage: enemy) {
                // 적 물리칠 수 있으면
                if (n >= enemyInStage) {
                    n-= enemyInStage;
                    queue.add(enemyInStage);
                    round++;
                } else { // 못이김
                    if (k > 0) {// 무적권이 있으면
                        n -= enemyInStage;
                        queue.add(enemyInStage);

                        // 무적권을 그동안 가장 병사들이 많았던 라운드에 적용한다
                        Integer max = queue.poll();
                        n += max;
                        k--;

                        round++;
                    } else {
                        break;
                    }
                }
            }

            return round;
        }
    }
}
