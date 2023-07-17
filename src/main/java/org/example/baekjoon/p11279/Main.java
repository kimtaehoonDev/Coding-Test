package org.example.baekjoon.p11279;

import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> nums = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num != 0) {
                nums.add(num);
            } else {
                if (nums.isEmpty()) {
                    sb.append("0\n");
                } else {
                    Integer poll = nums.poll();
                    sb.append(poll + "\n");
                }
            }
        }
        System.out.println(sb);
    }
}
