package org.example.leetcode;

// https://leetcode.com/problems/merge-sorted-array/?envType=study-plan-v2&envId=top-interview-150
public class p88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] newAry = new int[m];
        for(int i=0; i<m; i++) {
            newAry[i] = nums1[i];
        }

        // newAry, nums2 비교
        int p1 = 0;
        int p2 = 0;

        int idx = 0;
        while(idx < m + n) {
            // 엣지케이스
            if (p1 == m) {
                nums1[idx++] = nums2[p2++];
                continue;
            }
            if (p2 == n) {
                nums1[idx++] = newAry[p1++];
                continue;
            }

            if (newAry[p1] > nums2[p2]) {
                nums1[idx++] = nums2[p2++];
            } else {
                nums1[idx++] = newAry[p1++];
            }
        }

    }
}

// 두 개의 배열을 하나로 바꾸는 문제
// 투포인터 사용
