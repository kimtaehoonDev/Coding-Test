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

// 40분
// 다익스트라, 플로이드 등 그래프 문제는 항상 풀이 짜느라 오래걸림
// 그냥 템플릿을 외우자. 어차피 풀이 동일한거 템플릿 외우면 풀이 작성하는 시간이 훨씬 줄어들듯
public class Main {
    static final int INF = 2_100_000_000;
    static List<List<Node>> graph;
    static int[] distances;

    // N : 도시 개수
    // M : 버스 개수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for(int i = 0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        distances = new int[N+1];
        Arrays.fill(distances, INF);
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int departure = Integer.parseInt(st.nextToken());
            int arrival = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(departure).add(new Node(arrival, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        dijkstra(start);
//        System.out.println(Arrays.toString(distances));
        System.out.println(distances[target]);
    }

    public static void dijkstra(int startIdx) {
        // cost가 저렴한거부터 heap에서 빠진다.
        Queue<Node> heap = new PriorityQueue<>((n1, n2) -> {
            return n1.cost - n2.cost;
        });
        Node start = new Node(startIdx, 0);
        heap.add(start);

        while (!heap.isEmpty()) {
            Node now = heap.poll();
            if (distances[now.arrival] != INF) {
                continue;
            }
            distances[now.arrival] = now.cost;
//            System.out.println(now.arrival + "의 중간과정 -> " + Arrays.toString(distances));
            // now.start에서 연결된 모든 놈들
            List<Node> connects = graph.get(now.arrival);
            for (Node connect : connects) {
                if (distances[connect.arrival] == INF) { // 아직 방문하지 않았다면
                    int renewCost = Math.min(distances[connect.arrival], connect.cost + distances[now.arrival]);
                    heap.add(new Node(connect.arrival, renewCost));
                }
            }
        }
    }

    static class Node {
        int arrival;
        int cost;

        public Node(int arrival, int cost) {
            this.arrival = arrival;
            this.cost = cost;
        }
    }
}
