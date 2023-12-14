package org.example.baekjoon.p2141;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 실패, 풀이 방법을 떠올리지 못함
// 아이디어 참고 후 풀기는 했는데 수학적인 부분이라 다시 풀면 풀 수 있을지 의문은 듬
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long total = 0;
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> ((int)(n1.idx - n2.idx)));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            queue.add(new Node(x, a));
            total += a;
        }
        long half = (total + 1) / 2;

        long acc = 0;
        while(true) {
            Node node = queue.poll();
            acc += node.val;
            if (acc >= half) {
                System.out.println(node.idx);
                return;
            }
        }
    }

    static class Node {
        long idx;
        long val;

        public Node(long idx, long val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
