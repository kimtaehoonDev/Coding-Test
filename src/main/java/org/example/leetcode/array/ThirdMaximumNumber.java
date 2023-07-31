package org.example.leetcode.array;

import java.util.Arrays;

class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int top = 0;
        int max = 0;
        for(int i= nums.length- 1; i >=0; i--) {
            if (i == nums.length - 1) {
                top++;
                max = nums[i];
                continue;
            }
            if (nums[i] < max) {
                top++;
                max = nums[i];
                if (top == 3) {
                    break;
                }
            }
        }

        if (top < 3) {
            return nums[nums.length - 1];
        }
        return max;
    }
}