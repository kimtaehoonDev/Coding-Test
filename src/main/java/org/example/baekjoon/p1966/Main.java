package org.example.baekjoon.p1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 성공 / 28분 / 구현
// 문제 잘못 해석해서 시간이 좀 소모됨
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken()); // target문서가 몇 번째로 인쇄되니?
            Queue<Integer> maxes = new PriorityQueue<>((n1, n2) -> n2 - n1);
            Queue<Node> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                queue.offer(new Node(input, j));
                maxes.offer(input);
            }
            int order = 0;
            while (true) {
                Node now = queue.poll();
                int max = maxes.poll();
                if (now.val != max) {
                    queue.offer(now);
                    maxes.offer(max);
                } else {
                    order++;
                    if (now.order == target) {
                        answers.add(order);
                        break;
                    }
                }
            }
        }

        StringJoiner sj = new StringJoiner("\n");
        for (int answer : answers) {
            sj.add(String.valueOf(answer));
        }
        System.out.println(sj);
    }

    static class Node {

        int val;
        int order;

        public Node(int val, int order) {
            this.val = val;
            this.order = order;
        }
    }

}
