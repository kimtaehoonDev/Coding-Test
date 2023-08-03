package org.example.leetcode;

// TODO 나중에 한번 더 풀어보기
//https://leetcode.com/problems/median-of-two-sorted-arrays/description/

public class p4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] small;
        int[] big;
        if (nums1.length < nums2.length) {
            small = nums1;
            big = nums2;
        } else {
            small = nums2;
            big = nums1;
        }

        int m = small.length;
        int n = big.length;

        // 배열이 하나밖에 없는 경우(배열 한 개의 크기가 0)
        if (m == 0) {
            if (n % 2 == 0) {
                return (big[big.length / 2 - 1] + big[big.length / 2]) / 2.0;
            }
            return big[big.length /2];
        }

        int low = 0;
        int high = m;
        // while 문에 조건이 들어가지 않는 이유
        while(true) {
            int i = (low + high)/2; //i는 mid
            int j = (m+n)/2 - i;

            int smallLeft = -99999999;
            if (i-1 >= 0) {
                smallLeft = small[i-1];
            }

            int bigLeft = -99999999;
            if (j-1 >= 0) {
                bigLeft = big[j-1];
            }

            int smallRight = 99999999;
            if (i != small.length) {
                smallRight = small[i];
            }

            int bigRight = 99999999;
            if (j != big.length) {
                bigRight = big[j];
            }

            if (smallRight < bigLeft) {
                low = i + 1;
                continue;
            } else if (bigRight < smallLeft) {
                high = i;
                continue;
            }

            //정답이 나온다
            int left = Math.max(smallLeft, bigLeft);
            int right = Math.min(smallRight, bigRight);
            if ((small.length + big.length) % 2 == 0) {
                return (left + right) / 2.0;
            }
            return right;
        }
    }
}
