package org.example.baekjoon.p11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        int[][] graph = new int[V + 1][V + 1];
        for (int i = 1; i < V+1; i++) {
            for (int j = 1; j < V+1; j++) {
                graph[i][j] = INF;
                if (i==j) {
                    graph[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start][end] = Math.min(graph[start][end], cost);
        }

        for (int k = 1; k < V + 1; k++) {
            for (int i = 1; i < V + 1; i++) {
                for (int j = 1; j < V + 1; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < V+1; i++) {
            StringJoiner sj = new StringJoiner(" ");
            for (int j = 1; j < V+1; j++) {
                if (graph[i][j] >= INF) {
                    sj.add("0");
                } else {
                    sj.add(String.valueOf(graph[i][j]));
                }
            }
            sb.append(sj + "\n");
        }
        System.out.println(sb);

    }
}
