package org.example.leetcode;

// https://leetcode.com/problems/remove-element/?envType=study-plan-v2&envId=top-interview-150
public class p27 {
    // Remove Element

    // 문제에 in-place라는 단어가 나오는데, 이 말은 nums 인스턴스! 사용해서 검사한다는 의미
    // 갈아끼면 의미 없다. 저 nums를 그대로 바꿔야 한다

    class Solution {
        public int removeElement(int[] nums, int val) {
            int[] temp = new int[nums.length];
            int idx = 0;
            for(int num: nums) {
                if (num != val) {
                    temp[idx++] = num;
                }
            }
            int cnt = idx;

            idx = 0;
            for(int num: nums) {
                nums[idx] = temp[idx];
                idx++;
            }

            return cnt;
        }
    }
}
