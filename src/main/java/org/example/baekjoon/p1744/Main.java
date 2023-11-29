package org.example.baekjoon.p1744;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

// 성공 / 24분
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> plusNums = new PriorityQueue<>((n1, n2) -> n2 - n1);// 내림차순
        Queue<Integer> minusAndZeroNums = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plusNums.add(num);
            } else {
                minusAndZeroNums.add(num);
            }
        }

        int answer = 0;
        while(!plusNums.isEmpty()) {
            int first = plusNums.poll();
            if (plusNums.isEmpty()) {
                answer += first;
            } else {
                int second = plusNums.poll();
                answer += Math.max(first + second, first * second);
            }
        }
        while(!minusAndZeroNums.isEmpty()) {
            int first = minusAndZeroNums.poll();
            if (minusAndZeroNums.isEmpty()) {
                answer += first;
            } else {
                int second = minusAndZeroNums.poll();
                answer += Math.max(first + second, first * second);
            }
        }

        System.out.println(answer);
    }
}
