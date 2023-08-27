package org.example.leetcode;

// 성공 / 7분
class p150 {
    public int evalRPN(String[] tokens) {

        Stack stack = new Stack(tokens.length);
        for(String token: tokens) {
            if (token.equals("+")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a + b);
            } else if (token.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (token.equals("*")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a * b);
            }else if (token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    static class Stack {
        int[] stack;
        int now = 0;

        public Stack(int size) {
            stack = new int[size];
        }

        public int push(int value) {
            stack[now++] = value;
            return now - 1;
        }

        public int pop() {
            return stack[--now];
        }
    }
}