package org.example.baekjoon.p1874;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder bf = new StringBuilder();
        int N = sc.nextInt();
        MyStack stack = new MyStack(N);
        int now = 1; // 스택에 들어가려고 대기중인 숫자
        boolean canAnswer = true;
        for(int i=1; i< N+1;i++) {
            if (!canAnswer) {
                break;
            }
            int input = sc.nextInt();

            if (now <= input) {
                for(int j=now;j<input+1;j++) {
                    stack.push(j);
                    bf.append("+\n");
                }
                stack.pop();
                bf.append("-\n");
                now = input + 1;
            } else { // 기존보다 작은 숫자가 들어옴
                if (stack.peek() == input) {
                    stack.pop();
                    bf.append("-\n");
                } else {
                    canAnswer = false;
                }
            }
        }

        if (canAnswer) {
            System.out.println(bf);
        } else {
            System.out.println("NO");
        }

    }
}

class MyStack {
    private Integer[] store;
    private int next;

    public MyStack(int size) {
        store = new Integer[size];
        next = 0;
    }

    public int peek() {
        return store[next-1];
    }

    public void push(int value) {
        if (store.length <= next) {
            throw new RuntimeException();
        }
        store[next] = value;
        next++;
    }

    public int pop() {
        if (next <= 0) {
            throw new RuntimeException();
        }
        next--;
        int target = store[next];
        store[next] = null;
        return target;
    }
}