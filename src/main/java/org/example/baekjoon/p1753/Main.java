package org.example.baekjoon.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static int INF = 100_000_000;

    static int V, E, K;
    static int[] answer;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        answer = new int[V+1];
        Arrays.fill(answer, INF);

        graph = new LinkedList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new LinkedList<>();
        }

        // 그래프를 초기화한다
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
        }

        dijkstra(K);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < answer.length; i++) {
            if (answer[i] >= INF) {
                sb.append("INF\n");
            } else {
                sb.append(answer[i] + "\n");
            }
        }
        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        boolean[] visited = new boolean[V + 1];

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        answer[start] = 0;

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int current = currentNode.end;

            if (visited[current]) {
                continue;
            }
            visited[current] = true;
            for(Node node: graph[current]) {
                if (answer[node.end] > answer[current] + node.cost) {
                    answer[node.end] = answer[current] + node.cost;
                    queue.add(new Node(node.end, answer[node.end]));
                }
            }

        }
    }

    static class Node implements Comparable<Node> {
        private int end;
        private int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int getIdx() {
            return end;
        }

        public int getCost() {
            return cost;
        }


        @Override
        public int compareTo(Node o) {
            if (this.cost > o.cost) {
                return 1;
            } else if (this.cost < o.cost) {
                return -1;
            }
            return 0;
        }
    }
}
