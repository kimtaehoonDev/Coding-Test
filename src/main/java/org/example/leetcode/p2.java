package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/add-two-numbers/
// 실패 이유: leading zero 뜻을 몰라 틀림.
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class p2 {
//    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        int prev = 0;
//        List<Integer> answer = new ArrayList<>();
//        while (true) {
//            if (l1 == null && l2 == null) {
//                if (prev != 0) {
//                    answer.add(prev);
//                }
//                // 역순 돌기
//                ListNode x = null;
//                for(int i=answer.size() - 1;i>=0;i--) {
//                    if (x == null) {
//                        x = new ListNode(answer.get(i));
//                        continue;
//                    }
//                    x = new ListNode(answer.get(i), x);
//                }
//                return x;
//            } else if(l2 == null) { // l1만 남은 경우
//                int temp = prev + l1.val;
//                l1 = l1.next;
//                prev = temp / 10;
//                temp %= 10;
//                answer.add(temp);
//            } else if (l1 == null) {
//
//                int temp = prev + l2.val;
//                l2 = l2.next;
//                prev = temp / 10;
//                temp %= 10;
//                answer.add(temp);
//            } else {
//                int temp = prev + l1.val + l2.val;
//
//                l1 = l1.next;
//                l2 = l2.next;
//                prev = temp/10;
//                temp %= 10;
//                answer.add(temp);
//            }
//        }
//    }
}