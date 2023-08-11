package org.example.leetcode;

import javax.swing.tree.TreeNode;

// https://leetcode.com/problems/validate-binary-search-tree
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
class p98 {

//    public boolean isValidBST(TreeNode root) {
//        return isValidHelper(root, null, null);
//    }
//
//    public boolean isValidHelper(TreeNode root, Integer min, Integer max) {
//        if (root == null) {
//            return true;
//        }
//        // 최소값보다 더 작거나 같은 값이 나올 수 없다(오른쪽 Sub Tree으로 이동하면 최소값이 정해짐)
//        if (min != null && root.val <= min) {
//            return false;
//        }
//        if (max != null && max <= root.val) {
//            return false;
//        }
//
//        // 왼쪽 서브트리가 적절하다 == 모든 값이 root.val보다 작다 && 오른쪽 서브트리가 적절하다 == 모든 값이 root.val보다 크다
//        return isValidHelper(root.left, min, root.val) && isValidHelper(root.right, root.val, max);
//    }
//
}