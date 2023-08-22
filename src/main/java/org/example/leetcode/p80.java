package org.example.leetcode;

// 성공/6분
public class p80 {
    public int removeDuplicates(int[] nums) {
        int prev = -1000000; // 불가능
        int cnt = 0;
        int nowIdx = 0;
        for(int num: nums) {
            if (num == prev) {
                cnt++;
                // 불가능 case
                if (cnt > 2) {
                    prev = num;
                    continue;
                }

                // 가능
                nums[nowIdx] = num;
            } else { // 숫자가 달라지면 값들 갱신
                prev = num;
                cnt = 1;
                nums[nowIdx] = num;
            }
            nowIdx++;
        }

        return nowIdx;
    }
}
