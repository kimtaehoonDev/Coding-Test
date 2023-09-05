package org.example.programmers;

import java.util.*;

// 성공/31분
// 잘 생각했고 수도코드도 잘 써서 풀긴 함
// 수도코드를 만드는 능력이 매우 떨어져있음

// 다른 사람 풀이인데, 스택을 사용하는 게 훨씬 깔끔하고 이해하기도 좋음
// https://sasca37.tistory.com/306
class 뒤에_있는_큰_수_찾기 {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        Stack<Node> stack = new Stack<>();
        for(int i=0; i<numbers.length;i++) {
            while (!stack.isEmpty()) {
                Node top = stack.peek();
                if (top.value < numbers[i]) {
                    answer[top.idx] = numbers[i];
                    stack.pop();
                    continue;
                }
                break;
            }

            stack.push(new Node(i, numbers[i]));
        }

        return answer;
    }

    static class Node {
        int idx;
        int value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}