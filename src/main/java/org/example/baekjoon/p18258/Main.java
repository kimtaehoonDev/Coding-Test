package org.example.baekjoon.p18258;

import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static List<Command> commands;
    static MyQueue queue;
    public static void main(String[] args) throws IOException {
        input();
        queue = new MyQueue(N);
//        List<Integer> answers = new ArrayList<>(N);
        StringBuilder sb = new StringBuilder();
        for (Command command : commands) {
            Integer result = execute(command);
            if (result == null) {
                continue;
            }
            sb.append(result + "\n");
        }

        System.out.println(sb);
    }

    private static Integer execute(Command command) {
        switch (command.value) {
            case "push":
                queue.push(command.param);
                return null;
            case "pop":
                return queue.pop();
            case "size":
                return queue.size();
            case "empty":
                return queue.isEmpty()?1:0;
            case "front":
                return queue.front();
            case "back":
                return queue.back();
        }
        return null;
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        commands = new ArrayList<>(N);
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String value = st.nextToken();
            Integer param = null;
            if (st.hasMoreTokens()) {
                param = Integer.parseInt(st.nextToken());
            }
            commands.add(new Command(value, param));
        }
    }

    static class MyQueue {
        int[] ary;
        int start = 0;
        int end = 0;

        public MyQueue(int size) {
            this.ary = new int[size];
        }

        public void push(int value) {
            // 가득찬 경우 예외처리하지 않는다
            ary[end++] = value;
        }

        public Integer pop() {
            if (isEmpty()) {
                return -1;
            }
            int target = ary[start++];
            return target;
        }

        public Integer size() {
            return end - start;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public Integer front() { // pop대상
            if (isEmpty()) {
                return -1;
            }
            return ary[start];
        }

        public Integer back() {
            if (isEmpty()) {
                return -1;
            }
            return ary[end - 1];
        }


    }

    static class Command {
        String value;
        Integer param;

        public Command(String value, Integer param) {
            this.value = value;
            this.param = param;
        }
    }
}
