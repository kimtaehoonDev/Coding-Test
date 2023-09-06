package org.example.leetcode;

import javax.swing.tree.TreeNode;

// 성공 / 20분
// idx와 count를 따로 관리해야 하는 문제
public class p230 {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
//    class Solution {
//        static int answer = -1;
//        public int kthSmallest(TreeNode root, int k) {
//            findValue(root, k, 0, 0);
//
//            return answer;
//        }
//
//        public int findValue(TreeNode root, int k, Integer now, Integer count) { //now가 0이라느ㄴ건 아직 모른다는거
//            if (root == null) {
//                return 0;
//            }
//            // System.out.println(root.val + "에서 현재 인덱스 " + now);
//
//            int leftCount = findValue(root.left, k, now, count);
//            count = leftCount + 1;
//            now += count; // 인덱스 번호
//
//            if (now == k) {
//                answer = root.val;
//            }
//
//            // n+1 < k면 더 볼필요 없음
//            int rightCnt = findValue(root.right, k, now, count);
//            return count + rightCnt;
//
//
//        }
//    }
}
