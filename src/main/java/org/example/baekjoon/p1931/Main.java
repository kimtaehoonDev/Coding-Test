package org.example.baekjoon.p1931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 성공/10분
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.end == o2.end) {
                // edge
                return o1.start - o2.start;
            } else {
                return o1.end - o2.end; // 더 빨리 끝나는 애가 우선순위를 가짐
            }
        });
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            queue.add(new Node(start, end));
        }

        int cnt = 0;
        int alreadyUsed = -1;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.start < alreadyUsed) {
                continue;
            }
            alreadyUsed = now.end;
            cnt++;
        }

        System.out.println(cnt);
    }

    static class Node {
        int start;
        int end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
