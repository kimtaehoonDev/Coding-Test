package org.example.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 성공/ 13분
public class 귤_고르기 {
    class Solution {
        public int solution(int k, int[] tangerine) {
            Map<Integer, Node> hash = new HashMap<>();
            List<Node> nodes = new ArrayList<>();
            for(int each: tangerine) {
                if (hash.containsKey(each)) {
                    hash.get(each).add();
                } else {
                    Node node = new Node(each);
                    hash.put(each, node);
                    nodes.add(node);
                }
            }

            // System.out.println(hash.size());
            // System.out.println(nodes.size());

            // 개수를 기준으로 ---> 오름차순 정렬
            int answer = 0;
            Collections.sort(nodes, (n1, n2) -> n2.cnt - n1.cnt);
            for(Node node: nodes) {
                // System.out.println(node);
                if (k <= 0) {
                    break;
                }
                answer++;
                k -= node.cnt;
            }

            return answer;
        }

        static class Node {
            int val;
            int cnt;

            public Node(int val) {
                this.val = val;
                this.cnt = 1;
            }

            public void add() {
                cnt++;
            }

            public String toString() {
                return "(val : " + val + ", cnt : " + cnt + ")";
            }
        }
    }
}
