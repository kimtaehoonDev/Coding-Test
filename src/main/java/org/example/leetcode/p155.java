package org.example.leetcode;

import java.util.LinkedList;
import java.util.List;

// 3회 실패 후 성공
// 풀이시간 : 1시간 10분
public class p155 {
    class MinStack {

        List<Node> store;
        int topIdx; // push시 들어가야 할 인덱스
        Integer min;

        public MinStack() {
            this.store = new LinkedList<>();
            this.topIdx = 0;
        }

        public void push(int val) {
            if (topIdx == 0) {
                min = val;
            } else {
                min = Math.min(val, min);
            }
            store.add(new Node(val, min));
            topIdx++;
        }

        public void pop() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            Node removed = store.remove(--topIdx);
            if (removed.min == min) {

                Node top = topHelper();
                if (top == null) {
                    this.min = null;
                } else {
                    this.min = top.min;
                }
            }
        }

        private Node topHelper() {
            if (topIdx == 0) {
                return null;
            }
            return store.get(topIdx - 1);
        }

        public int top() {
            return topHelper().val;
        }

        public int getMin() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            return min;
        }

        private boolean isEmpty() {
            return store.size() == 0;
        }

        class Node {
            int val;
            int min;

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

}
