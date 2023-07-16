package org.example.baekjoon.p10828;

import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        List<Integer> results = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            Integer num = null;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            } else {
                num = null;
            }
            //명령을 수행하고 답을 저장한다
            Integer result = execute(command, num, stack);
            if (result != null) {
                results.add(result);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<results.size(); i++) {
            sb.append(results.get(i) + "\n");
        }
        System.out.println(sb);
    }

    public static Integer execute(String command, Integer num, List<Integer> stack) {
        if (Objects.equals(command, "push")) {
            stack.add(num);
            return null;
        } else if (Objects.equals(command, "pop")) {
            if (stack.size() == 0) {
                return -1;
            }
            return stack.remove(stack.size()-1);
        } else if (Objects.equals(command, "size")) {
            return stack.size();
        } else if (Objects.equals(command, "empty")) {
            return stack.size() == 0 ? 1: 0;
        } else if (Objects.equals(command, "top")) {
            if (stack.size() == 0) {
                return -1;
            }
            return stack.get(stack.size()-1);
        } else {
            throw new RuntimeException("불가능");
        }

    }
}