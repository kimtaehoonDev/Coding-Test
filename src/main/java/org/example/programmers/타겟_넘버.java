package org.example.programmers;

import java.util.*;

public class 타겟_넘버 {
    public static int PLUS = 0;
    public static int MINUS = 1;
    public static int MULTIPLY = 2;
    public static int DIVIDE = 3;

    static Map<Integer, Integer> dp;

    public int solution(int N, int num) {

        dp = new HashMap<>(); //0부터 최대크기까지 배열 생성

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, N, PLUS, 1));
        queue.add(new Node(0, N, MINUS, 1));
        queue.add(new Node(0, N, MULTIPLY, 1));

        dp.put(N, 1);

        while (!queue.isEmpty()) {
            Node target = queue.poll();
            for (int i = 0; i < 5; i++) {
                Node node;
                if (i == 4) {
                    node = new Node(target.first, target.second * 10 + N, target.operator,
                        target.cnt + 1);
                } else if (i == PLUS) {
                    node = new Node(target.value, N, PLUS, target.cnt + 1);
                } else if (i == MINUS) {
                    node = new Node(target.value, N, MINUS, target.cnt + 1);
                } else if (i == MULTIPLY) {
                    node = new Node(target.value, N, MULTIPLY, target.cnt + 1);
                } else {
                    node = new Node(target.value, N, DIVIDE, target.cnt + 1);
                }

                // 범위 벗어나거나, 이미 초기화가 된 애
                // if (node.value < 0) {
                // continue;
                // }
                if (dp.containsKey(node.value)) {
                    continue;
                }
                if (node.cnt > 8) {
                    continue;
                }

                dp.put(node.value, node.cnt);

                queue.add(node);
            }
        }

        if (!dp.containsKey(num)) {
            return -1;
        }
        return dp.get(num);
    }

    static class Node {
        int first;
        int second; //백만까지만 가능
        int operator; // 0123
        int value; //9999 * 99999 <= 10억
        int cnt;

        // int 0, 1, 2, 3 Object.equal 메서드가 맞나? equals?
        public Node(int first, int second, int operator, int cnt) {
            this.first = first;
            this.second = second;
            this.operator = operator;
            if (operator == PLUS) {
                this.value = first + second;
            } else if (operator == MINUS) {
                this.value = first - second;
            } else if (operator == MULTIPLY) {
                this.value = first * second;
            } else { //divide
                this.value = first / second;
            }

            this.cnt = cnt;
        }
    }
}
