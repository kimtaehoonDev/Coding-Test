package org.example.leetcode;

// 아이디어는 잘 떠올렸으나 엣지 케이스를 잘 처리하지 못해 두 번이나 틀림
// https://velog.io/@iamtaehoon/Search-In-Rotated-Sorted-Array-%EB%A6%AC%ED%8A%B8%EC%BD%94%EB%93%9C
class p33 {
    public int search(int[] nums, int target) {
        int movingCnt = findMovingCnt(nums);
        // System.out.println(movingCnt);

        int l = 0;
        int h = nums.length;
        while(l<h) {
            int mid = (l+h)/2;
            // 논리위치 l, m, h
            // 실제위치는 movingCnt를 더해주고 nums.length로 나머지연산을 해야 함
            int findValue = nums[(mid + movingCnt) % nums.length];
            if (target == findValue) {
                return (mid + movingCnt) % nums.length;
            } else if (target > findValue) {
                l = mid + 1;
            } else if (target < findValue) {
                h = mid;
            }
        }

        // 답이 없는 경우
        return -1;
    }

    public int findMovingCnt(int[] nums) {
        int l = 0;
        int h = nums.length;
        if (nums.length == 1) {
            return 0;
        }
        if (nums.length == 2) {
            if (nums[0] < nums[1]) {
                return 0;
            }
            return 1;
        }

        while(l < h) {
            int mid = (l + h)/2;
            // System.out.println(l + " " + h +" "+ mid);
            if (mid == nums.length - 1) {
                if (nums[mid - 1] >= nums[mid]) {
                    return mid;
                }
                break;
            }
            if (mid == 0) {
                if (nums[mid] >= nums[mid + 1]) {
                    return mid + 1;
                }
                break;
            }

            if (nums[mid] >= nums[mid + 1]) {
                return mid + 1;
            }
            if (nums[mid - 1] >= nums[mid]) {
                return mid;
            }

            // 정렬된값에서는 있을 수 없는 경우 -> mid, h 사이에 어딘가에 시작점이 있음
            if (nums[mid] > nums[h-1]) {
                l = mid + 1;
            } else {
                h = mid;
            }
        }
        return 0;
    }
}
