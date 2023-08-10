package org.example.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

// https://leetcode.com/problems/kth-largest-element-in-an-array/
class p215 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for(int num: nums) {
            queue.add(num);
        }

        int answer = 0;
        for(int i=0; i<k;i++) {
            answer = queue.poll();
        }
        return answer;
    }
}