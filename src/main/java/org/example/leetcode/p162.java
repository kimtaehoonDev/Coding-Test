package org.example.leetcode;

// 실패 후 성공
// https://velog.io/@iamtaehoon/Find-Peek-Element-%EB%A6%AC%ED%8A%B8%EC%BD%94%EB%93%9C
class p162 {
    public int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length;

        while(start < end) {
            int mid = (start + end)/2;
            // mid는 start, end와 다르겟지~~
            boolean goLeft = (mid != 0 && nums[mid - 1] > nums[mid]);
            boolean goRight = (mid !=nums.length - 1 && nums[mid] < nums[mid+1]);
            if (!goLeft && !goRight) {
                return mid;
            }
            if (goLeft) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }
}