package org.example.baekjoon.p1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공 / 다익스트라 / 29분
// -> 더 이상 줄이는 건 불가능할듯
public class Main3 {

    static final int INF = 1_000_000_000;

    static int N;
    static int M;

    static List<List<Node>> graph = new ArrayList<>();
    static int[] distances;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        distances = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(distances, INF);

        for(int i = 0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
//        for(List<Node> nodes : graph) {
//            System.out.println(nodes);
//        }

        Queue<Node> queue = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });
        distances[startNode] = 0;
        visited[startNode] = true;
        queue.add(new Node(startNode, 0));
        while(!queue.isEmpty()) {
            Node target = queue.poll();
            distances[target.idx] = target.cost; // 최소 기록 갱신
            visited[target.idx] = true;
            if (target.idx == endNode) {
                break;
            }
            for(Node connect : graph.get(target.idx)) {
                if (visited[connect.idx])
                    continue;
                int temp = distances[target.idx] + connect.cost;
                queue.add(new Node(connect.idx, temp));
            }
        }

        System.out.println(distances[endNode]);
    }

    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public String toString() {
            return "(" + idx + ", " + cost + ")";
        }
    }
}
