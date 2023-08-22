package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

// 성공/4분
class p26 {
    public int removeDuplicates(int[] nums) {
        List<Integer> answers = new ArrayList<>();
        int prev = -101;
        for(int num: nums) {
            if (prev == num) {
                continue;
            }
            answers.add(num);
            prev = num;
        }
        int i = 0;
        for(Integer answer: answers) {
            nums[i++] = answer;
        }

        return answers.size();
    }
}