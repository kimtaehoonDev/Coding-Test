package org.example.programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 실패 / 40분
// 바로 이어서 정답 코드와 왜 정답인지 서술하겠음
public class 점프와_순간이동 {
    public class Solution {
        static Map<Integer, Integer> dp = new HashMap<>();

        public int solution(int n) {

            Queue<Node> heap = new LinkedList<>();
            heap.offer(new Node(n, 0));
            // dp.put(n, 0);

            while(!heap.isEmpty()) {
                Node target = heap.poll();
                dp.put(target.location, target.battery); // 방문처리

                // 순간이동
                if (target.location % 2 == 0) {
                    if (dp.containsKey(target.location/2)) {
                        continue;
                    }
                    heap.offer(new Node(target.location/2, target.battery));
                }

                // 배터리 사용
                if (dp.containsKey(target.location - 1)) {
                    continue;
                }
                heap.offer(new Node(target.location - 1, target.battery + 1));
                if (target.location - 1 == 0) {
                    return target.battery + 1;
                }
            }

            return -1; // CANT ANSWER(도달불가능)
        }

        static class Node {
            int location;
            int battery;

            public Node(int location, int battery) {
                this.location = location;
                this.battery = battery;
            }
        }
    }

}
