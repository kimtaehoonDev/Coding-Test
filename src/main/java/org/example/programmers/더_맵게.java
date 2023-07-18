package org.example.programmers;

import java.util.PriorityQueue;

public class 더_맵게 {
    class Solution {

        public int solution(int[] scoville, int K) {
            boolean isHot = false;
            PriorityQueue<Integer> queue = new PriorityQueue<>();

            // 모든 스코빌 넣기
            for(int each: scoville) {
                queue.add(each);
            }

            int first = -1;
            int second = -1;
            int newFood = -1;
            int makingCnt = 0;
            while (2 <= queue.size()) {
                first = queue.poll();
                // 검증
                if (first >= K) {
                    isHot = true;
                    break;
                }
                second = queue.poll();

                makingCnt += 1;
                newFood = first + 2 * second;
                queue.add(newFood);
            }

            // 1개일때 검증
            first = queue.poll();
            if (first >= K) {
                isHot = true;
            }

            if (isHot) {
                return makingCnt;
            }
            return -1;
        }
    }
}
