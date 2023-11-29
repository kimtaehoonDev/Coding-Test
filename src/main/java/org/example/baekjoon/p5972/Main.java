package org.example.baekjoon.p5972;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int INF = 200000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // key == 출발지 * N + 목적지
        // key / N == 출발지, key % N == 목적지
        // value == 비용
        Map<Long, Integer> hash = new HashMap();
        for(int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken()) - 1;
            int s2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            long key = (long) s1 * N + (long) s2;
            if (hash.containsKey(key)) {
                int minCost = Math.min(hash.get(key), cost);
                hash.put(key, minCost);
            } else {
                hash.put(key, cost);
            }
        }

        List<List<Edge>> graph = new ArrayList<>();
        for(int i = 0; i<N; i++) {
            graph.add(new ArrayList<>());
        }
        // 중복을 제거한 이후 graph를 초기화한다
        for(Long key : hash.keySet()) {
            int s1 = (int) (key / N);
            int s2 = (int) (key % N);
            int cost = hash.get(key);
            graph.get(s1).add(new Edge(s2, cost));
            graph.get(s2).add(new Edge(s1, cost));
        }
//        graph 초기화 검사 로그
//        int idx = 0;
//        for (List<Edge> edges : graph) {
//            System.out.println(idx++);
//            for (Edge edge : edges) {
//                System.out.println(edge);
//            }
//        }

        boolean[] visited = new boolean[N];
        int[] distances = new int[N];
        Arrays.fill(distances, INF);

        // 거리가 짧은 순서대로 정렬한다(오름차)
        Queue<Edge> heap = new PriorityQueue<>(new Comparator<>() {
            public int compare(Edge e1, Edge e2) {
                return e1.cost - e2.cost;
            }
        });

        // 초기값을 넣는다(0번 헛간에 도착할 때까지 비용 0)
        visited[0] = true;
        distances[0] = 0;
        heap.add(new Edge(0, 0));
        while(!heap.isEmpty()) {
            Edge now = heap.poll();
            if (now.dist == N - 1) {
                System.out.println(distances[now.dist]);
                break;
            }
            // TODO 농부 위치 검사
            List<Edge> edges = graph.get(now.dist); // edges 연결된 애들
            for (Edge edge : edges) {
                if (visited[edge.dist]) {
                    continue;
                }
                if (distances[edge.dist] > distances[now.dist] + edge.cost) {
                    distances[edge.dist] = distances[now.dist] + edge.cost;
                    heap.add(new Edge(edge.dist, distances[edge.dist]));
                }
            }
        }

    }

    // 목적지와 비용
    static class Edge {
        int dist;
        int cost;

        public Edge(int dist, int cost) {
            this.dist = dist;
            this.cost = cost;
        }

        public String toString() {
            return "(dist : " + dist + ", cost : " + cost + ")";
        }
    }
}
