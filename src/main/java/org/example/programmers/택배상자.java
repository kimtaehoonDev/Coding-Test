package org.example.programmers;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class 택배상자 {
    public int solution(int[] order) {
        int answer = 0;

        // orders로 Queue 만듬
        Queue<Integer> inputs = new LinkedList<>();
        for(int each : order) {
            inputs.add(each);
        }

        Stack<Integer> subContainer = new Stack<>();
        int idx = 1; // 지금 확인하고 있는 상자의 번호
        while(!inputs.isEmpty()) {
            // System.out.println(inputs.peek() + "을 확인");
            if (idx == inputs.peek()) {
                // System.out.println("싣다0 " + inputs.peek() + "인덱스 : " + idx);
                idx++;
                inputs.poll();
                answer++;
            } else if (idx > inputs.peek()) {
                // sub.top 관련 작업
                if (subContainer.peek() == inputs.peek()) {
                    subContainer.pop();
                    // System.out.println("싣다1 " + inputs.peek());
                    inputs.poll();
                    answer++;
                } else {
                    break;
                }
            } else {
                // subContainer에 싣는다
                for(int i = idx; i<inputs.peek(); i++) {
                    subContainer.push(i);
                    // System.out.println("subContainer에 " + i + " 싣다");
                    idx++;
                }
                // System.out.println("싣다2 " + inputs.peek());
                inputs.poll();
                answer++;
            }
        }
        return answer;
    }
}