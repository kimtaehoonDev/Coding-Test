package org.example.baekjoon.p11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int INF = 1_00_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[E];
        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(start, end, cost);
        }

        long[] distance = new long[V+1]; // 음의 Loop가 생겼을 때 끝도없이 작아질 수 있음
        // https://www.acmicpc.net/board/view/55270 설명 들어있음
        Arrays.fill(distance, INF);
        distance[1] = 0;

        for(int i=0; i<V-1;i++) {
            for(Edge edge: edges) {
                // 엣지의 시작점이 아직 비용계산 덜된경우
                if (distance[edge.start] >= INF) {
                    continue;
                }
                distance[edge.end] = Math.min(distance[edge.end], distance[edge.start] + edge.cost);
            }
        }

        boolean isLoop = false;
        // 무한루프를 도는지 체크
        for(Edge edge: edges) {
            if (distance[edge.start] >= INF) {
                continue;
            }
            if (distance[edge.start] + edge.cost < distance[edge.end]) {
                isLoop = true;
                break;
            }
        }
        if (isLoop) {
            System.out.println("-1");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i < V + 1; i++) {
                if (distance[i] >= INF) {
                    sb.append("-1\n");
                    continue;
                }
                sb.append(distance[i]+"\n");
            }
            System.out.println(sb);
        }
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
