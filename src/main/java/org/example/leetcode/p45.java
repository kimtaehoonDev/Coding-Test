package org.example.leetcode;

// https://leetcode.com/problems/jump-game-ii/?envType=study-plan-v2&envId=top-100-liked
public class p45 {
    public static int INF = 100000000;
    public int jump(int[] nums) {
        int[] jumps = new int[nums.length];
        for(int i=0; i< jumps.length; i++) {
            jumps[i] = INF;
        }
        jumps[0] = 0; // 첫 번째 원소는 0번의 점프로 도착가능

        for(int i=0; i< nums.length; i++) {
            for(int j=1; j<=nums[i];j++) {
                if (i + j >= jumps.length) {
                    break;
                }
                jumps[i+j] = Math.min(jumps[i + j], jumps[i] + 1);
            }
        }

        return jumps[jumps.length-1];
    }
}
