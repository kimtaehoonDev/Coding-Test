package org.example.leetcode;

// 성공/16분
class p167 {
    public int[] twoSum(int[] numbers, int target) {
        for(int selected = 0; selected<numbers.length; selected++) {
            int start = selected + 1;
            int end = numbers.length; // 끝선
            while(start < end) {
                int mid = (start + end) / 2; // int범위
                int result = numbers[mid] + numbers[selected];
                if(result > target) {
                    end = mid;
                } else if (result < target) {
                    start = mid + 1;
                } else {
                    return new int[] {selected + 1, mid + 1};
                }
            }
        }
        return new int[2];
    }
}
