package org.example.leetcode;

// 성공(한 번 제출 틀리긴 했는데, 어디서 틀렸는지 안봤습니다. 프로그래머스 제출한거마냥 생각하고 풀어서 그냥 성공했다고 적었습니다.)
// 풀이시간 : 16분

public class p530 {
    ///**
    // * Definition for a binary tree node.
    // * public class TreeNode {
    // *     int val;
    // *     TreeNode left;
    // *     TreeNode right;
    // *     TreeNode() {}
    // *     TreeNode(int val) { this.val = val; }
    // *     TreeNode(int val, TreeNode left, TreeNode right) {
    // *         this.val = val;
    // *         this.left = left;
    // *         this.right = right;
    // *     }
    // * }
    // */
    //class Solution {
    //    public int getMinimumDifference(TreeNode root) {
    //        return findMinDifference(root, null, null);
    //    }
    //
    //    int findMinDifference(TreeNode root, Integer min, Integer max) {
    //        // 미니멈 계산하기
    //        int answer = 10000000;
    //        if (min != null) {
    //            answer = Math.min(answer, Math.abs(root.val - min));
    //        }
    //        if (max != null) {
    //            answer = Math.min(answer, Math.abs(root.val - max));
    //        }
    //        if (root.left != null) {
    //            // 왼쪽이동
    //            answer = Math.min(answer, findMinDifference(root.left, min, root.val));
    //        }
    //
    //        if (root.right != null) {
    //            // 오른쪽이동
    //            answer = Math.min(answer, findMinDifference(root.right, root.val, max));
    //        }
    //
    //        return answer;
    //    }
    //}
}

