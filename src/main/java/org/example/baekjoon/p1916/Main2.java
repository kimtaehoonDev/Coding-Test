package org.example.baekjoon.p1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// N : 도시 개수
// M : 버스 개수
public class Main2 {
    public static final int INF = 2_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] distances = new int[N+1];
        Arrays.fill(distances, INF);
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        distances[start] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.start] == INF) {
                    continue;
                }
                if (distances[edge.end] > distances[edge.start] + edge.cost) {
                    distances[edge.end] = distances[edge.start] + edge.cost;
                }
            }
        }
//        System.out.println(Arrays.toString(distances));
        System.out.println(distances[target]);
    }

    static class Edge {
        int start;
        int end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
