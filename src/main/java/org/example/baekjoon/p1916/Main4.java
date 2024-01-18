package org.example.baekjoon.p1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 성공 / 벨만포드 / 14분
public class Main4 {

    static final int INF = 1_000_000_000;

    static int N;
    static int M;
    static int[] distances;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        distances = new int[N + 1];
        Arrays.fill(distances, INF);

        for(int i = 0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, cost));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        distances[startNode] = 0;
        for(int i = 0; i<N; i++) {
            for(Edge edge : edges) {
                if (distances[edge.start] == INF)
                    continue;
                distances[edge.end] = Math.min(distances[edge.start] + edge.cost, distances[edge.end]);
            }
        }
//        System.out.println(Arrays.toString(distances));
        System.out.println(distances[endNode]);
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
