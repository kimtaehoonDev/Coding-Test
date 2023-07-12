package org.example.baekjoon.p1197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Edge[] graph = new Edge[E];

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[i] = new Edge(n1, n2, cost);
        }
        Arrays.sort(graph);

        // 부모를 확인할 수 있는 배열
        int[] ary = new int[V+1];
        for(int i=0; i<ary.length; i++) {
            ary[i] = i;
        }
        // edge.n1과 edge.n2 부모 비교한다
        int answer = 0;
        int connectingCnt = 0;
        for (Edge edge : graph) {
            if (connectingCnt == V - 1) {
                break;
            }
            int parent1 = find(edge.n1, ary);
            int parent2 = find(edge.n2, ary);
            if (parent1 == parent2) {
                continue;
            }
            union(parent1, parent2, ary);
            answer += edge.cost;
            connectingCnt += 1;
        }
        System.out.println(answer);
    }

    public static int find(int node, int[] ary) {
        if (ary[node] == node) {
            return node;
        }
        ary[node] = find(ary[node], ary);
        return ary[node];
    }

    public static void union(int node1, int node2, int[] ary) {
        int p1 = find(node1, ary);
        int p2 = find(node2, ary);
        int min = Math.min(p1, p2);
        ary[p1] = min;
        ary[p2] = min;
    }

    static class Edge implements Comparable<Edge> {
        int n1;
        int n2;
        int cost;

        public Edge(int n1, int n2, int cost) {
            this.n1 = n1;
            this.n2 = n2;
            this.cost = cost;
        }

        // 오름차순이라는 의미?
        @Override
        public int compareTo(Edge o) {
            if (this.cost > o.cost) {
                return 1;
            } else if (this.cost < o.cost) {
                return -1;
            }
            return 0;
        }

        @Override
        public String toString() {
            return n1 + " | " + n2 + " 사이 거리: " + cost;
        }

    }
}
