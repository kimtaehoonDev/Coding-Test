package org.example.leetcode;

//**
//    * Definition for singly-linked list.
//    * public class ListNode {
// *     int val;
// *     ListNode next;
// *     ListNode() {}
// *     ListNode(int val) { this.val = val; }
// *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// * }
// */

// 성공. 그러나 공간복잡도 O(1) 만족시키지못함
// 하지만 다른 분들의 풀이를 살펴봐도 공간복잡도 만족시키는 풀이는 하나도 없었음
class p148 {
//    public ListNode sortList(ListNode head) {
//        ListNode next = head;
//
//        // 총 원소 개수를 센다
//        int totalCnt = 0;
//        while(next != null) {
//            next = next.next;
//            totalCnt++;
//        }
//        // System.out.println(totalCnt);
//
//        head = sort(head, totalCnt);
//        return head;
//    }
//
//    // 정렬된 노드의 시작점을 반환한다
//    public ListNode sort(ListNode head, int totalCnt) {
//        if (totalCnt == 0) {
//            return head;
//        }
//        if (totalCnt == 1) {
//            head.next = null;
//            return head;
//        }
//
//        // 가운데 값을 찾는다
//        int idx = 0;
//        ListNode middle = head;
//        ListNode prev = null;
//        while(idx < totalCnt/2) {
//            prev = middle;
//            middle = middle.next;
//            idx++;
//        }
//        prev.next = null;
//
//        ListNode second = sort(head, totalCnt/2);
//        ListNode first = sort(middle,totalCnt - totalCnt/2);
//
//        ListNode answer = new ListNode(-1000000);
//        ListNode start = answer;
//        while(true) {
//            if (first == null) {
//                answer.next = second;
//                break;
//            }
//            if (second == null) {
//                answer.next = first;
//                break;
//            }
//
//            if (first.val < second.val) {
//                answer.next = new ListNode(first.val);
//                answer = answer.next;
//                first = first.next;
//            } else {
//                answer.next = new ListNode(second.val);
//                answer = answer.next;
//                second = second.next;
//            }
//        }
//
//        return start.next;
//    }
}