package org.example.baekjoon.p1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공 / 47분
// 실1 생각보다 어려운데..?
// dp를 쓰지 않으면 시간복잡도가 걸림. 고민했는데 2^63 - 1까지 정답이 나올 수 있다는 걸 보고
// 모든 경우를 보면 시간복잡도 걸리겠다 판단함

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        long[][] dp = new long[N][N];
        dp[0][0] = 1L;

        for(int i = 0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // queue는 priority ASC -> i+j ASC
        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            if (n1.priority != n2.priority) {
                return n1.priority - n2.priority;
            }
            return (n1.x + n1.y) - (n2.x + n2.y);
        });

        queue.add(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node selected = queue.poll();
//            System.out.println(selected);
//            System.out.println(dp[selected.x][selected.y]);

            if (graph[selected.x][selected.y] == 0) {
                // TODO 정답 그런데 dp에 갱신되지 않나?
                continue;
            }
            if (selected.y + graph[selected.x][selected.y] < N) {
                dp[selected.x][selected.y + graph[selected.x][selected.y]] += dp[selected.x][selected.y];
                if (dp[selected.x][selected.y + graph[selected.x][selected.y]] == dp[selected.x][selected.y]) {
                    // 최초
                    queue.add(new Node(selected.x, selected.y + graph[selected.x][selected.y]));
                }
            }
            if (selected.x + graph[selected.x][selected.y] < N) {
                dp[selected.x + graph[selected.x][selected.y]][selected.y] += dp[selected.x][selected.y];
                if (dp[selected.x + graph[selected.x][selected.y]][selected.y] == dp[selected.x][selected.y]) {
                    // 최초
                    queue.add(new Node(selected.x + graph[selected.x][selected.y], selected.y));
                }
            }
//            System.out.println(queue);
        }
        System.out.println(dp[N - 1][N - 1]);

    }

    static class Node {
        int x;
        int y;
        int priority;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.priority = Math.min(x, y);
        }

        @Override
        public String toString() {
            return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
