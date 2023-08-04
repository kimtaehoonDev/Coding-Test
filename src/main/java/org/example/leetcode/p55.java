package org.example.leetcode;

// https://leetcode.com/problems/jump-game/description/?envType=study-plan-v2&envId=top-100-liked

public class p55 {
    public boolean canJump(int[] nums) {
        int[] moves = new int[nums.length];
        for(int i=0; i<nums.length;i++) {
            moves[i] = i;
        }

        int max = 0;
        for(int i=0; i<nums.length; i++) {
            if (i > max) {
                return false;
            }
            moves[i] = Math.max(nums[i] + i, moves[i]);
            max = Math.max(max, moves[i]);
        }
        return true;
    }
}
