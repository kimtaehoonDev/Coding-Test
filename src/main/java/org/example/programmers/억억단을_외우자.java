package org.example.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 실패 후 답안 참조 / 고민 시간 : 2시간

// 아이디어는 제대로 생각해냈으나 시간복잡도가 된다는 확신이 생기지 않아 중간에 포기
// 아이디어가 맞다는 걸 확인 -> 다시 풀어 성공했다.

// 핵심 아이디어는 구구단이 나온 횟수 == 약수의 갯수라는 점..!(설명은 생략한다)
// 약수의 개수를 구하는 최적의 경우의 수를 오랜 시간 고민했다.
// 왜 고민했냐, end는 500만이라는 범위를 갖는다.
// 각 숫자는 약수를 구하기 위해 1부터 자기자신까지 나눠보는 과정이 필요하다
// 이러면 n^2이라는 시간복잡도를 갖기 떄문에 뾰족한 방법을 떠올리지 못했는데, 어라라 그냥 시간복잡도가 통과한다

public class 억억단을_외우자 {
    class Solution {
        static int[] dp;
        public int[] solution(int end, int[] starts) {
            int[] answer = new int[starts.length];
            int idx = 0;

            dp = new int[end+1];

            // 여기 시간복잡도 터지지 않는다는 확신이 어디서 생기는거야 도대체
            for(int i=1; i<=end;i++) {
                for(int j=1; j<=end; j++) {
                    if (i * j > end) {
                        break;
                    }
                    dp[i*j]++;
                }
            }

            // starts를 내림차순 정리한다
            List<Node> nodes = new ArrayList<>();
            idx = 0;
            for(int start: starts) {
                nodes.add(new Node(start, idx++));
            }

            Collections.sort(nodes, (n1, n2) -> {
                if (n1.val == n2.val) {
                    // 값 같으면 인덱스 빠른거 먼저
                    return n1.idx - n2.idx;
                }
                // 값을 기준으로 내림차순 정렬
                return n2.val - n1.val;
            });


            // dp 값이 큰 순서대로(등장횟수가 많다는 의미) 정렬
            // 등장횟수가 동일하면 가장 작은 숫자를 반환
            Queue<Integer> queue = new PriorityQueue<>((i1, i2) -> {
                if (dp[i1] == dp[i2]) {
                    return i1 - i2;
                }
                return dp[i2] - dp[i1];

            });
            for(Node node: nodes) {
                // 먼저 8 넣기 그 다음에 7 넣기
                for(int i = end; i>=node.val;i--) {
                    queue.add(i);
                }
                // 지금까지 있는 숫자들 중에서 가장 등장횟수가 많은 애는 target이다
                Integer target = queue.poll();
                answer[node.idx] = target;
                queue.add(target);
                end = node.val - 1;
            }

            return answer;
        }

        static class Node {
            int val;
            int idx;

            public Node(int val, int idx) {
                this.val = val;
                this.idx = idx;
            }
        }


    }
}
