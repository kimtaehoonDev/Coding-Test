package org.example.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class 같은_숫자는_싫어 {
    public class Solution {
        public int[] solution(int []arr) {
            Stack<Integer> stack = new Stack<>();
            List<Integer> temp = new ArrayList<>(arr.length);
            for(int each: arr) {
                if (stack.isEmpty()) {
                    stack.add(each);
                    temp.add(each);
                    continue;
                }
                // 스택 != 0
                int top = stack.peek();
                if (top != each) {
                    stack.add(each);
                    temp.add(each);
                }
            }

            return temp.stream().mapToInt(i->i).toArray();
            // 새로운 기법: List<Integer> ->  int stream -> int[]
        }
    }
}
