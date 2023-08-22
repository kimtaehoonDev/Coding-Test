package org.example.leetcode;

import java.util.Arrays;

// 성공/15분
class p169 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);

        // 짝수
        if (nums.length % 2 == 0) {
            int middle1Idx = nums.length / 2;
            int middle2Idx = middle1Idx + 1;
            if (nums[middle1Idx] == nums[nums.length - 1]) {
                return nums[middle1Idx];
            }

            if (nums[middle2Idx] == nums[0]) {
                return nums[0];
            }
        }

        // 홀수
        return nums[nums.length / 2];
    }
}