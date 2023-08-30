package org.example.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 1회 실패 후 성공
// 실패 이유 : 수도코드 써놓고 누락
// 풀이시간 : 11분
class p1 {
    public int[] twoSum(int[] nums, int target) {
        int idx = 0;
        List<Node> nodes = new ArrayList<>(nums.length);
        for(int num: nums) {
            nodes.add(new Node(num, idx++));
        }

        // 오름차순 Sort
        nodes.sort(new Comparator<>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n1.val - n2.val;
            }
        });


        // 투포인터로 target을 만들 수 있는 두 Node를 찾는다
        int left = 0;
        int right = nodes.size() - 1;

        while(true) { // 어차피 정답 있음
            int sum = nodes.get(left).val + nodes.get(right).val;
            if (sum == target) {
                return new int[] { nodes.get(left).idx, nodes.get(right).idx };
            } else if (target < sum) {
                right -= 1;
            } else {
                left += 1;
            }
        }
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