package org.example.baekjoon.p2504;

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.next().split("");
        int solve = solve(str);

        System.out.println(solve);
    }

    private static int solve(String[] str) {
        Stack<Node> stack = new Stack<>();

        for (int i = 0; i < str.length; i++) {
            String each = str[i];

            if (each.equals("(") || each.equals("[")) {
                stack.push(new Node(false, each));
            } else if (each.equals(")")) {

                int temp = 1;
                while (true) {
                    if (stack.isEmpty()) {
                        return 0;
                    }
                    Node target = stack.pop();
                    if (target.isNum) {
                        temp *= (Integer) target.value;
                    } else {
                        if (target.value.equals("(")) {
                            temp *= 2;
                            stack.push(new Node(true, temp));
                        } else {
                            return 0; // 불가능
                        }
                        break;
                    }
                }
            } else if (each.equals("]")) {
                int temp = 1;
                while (true) {
                    if (stack.isEmpty()) {
                        return 0;
                    }
                    Node target = stack.pop();
                    if (target.isNum) {
                        temp *= (Integer) target.value;
                    } else {
                        if (target.value.equals("[")) {
                            temp *= 3;
                            stack.push(new Node(true, temp));
                        } else {
                            return 0;// 불가능
                        }
                        break;
                    }
                }
            }
            if (stack.size() > 1) {
                Node pop = stack.pop();
                Node peek = stack.peek();
                if (pop.isNum && peek.isNum) {
                    int value = (Integer) pop.value +
                        (Integer) stack.pop().value;

                    stack.add(new Node(true, value));
                } else {
                    stack.add(pop);
                }
            }
        }
        if (stack.size() == 1 && stack.get(0).isNum) {
            return (int) stack.get(0).value;
        }
        return 0;
    }

    static class Node {
        boolean isNum;
        Object value;

        public Node(boolean isNum, Object value) {
            this.isNum = isNum;
            this.value = value;
        }
    }
}
