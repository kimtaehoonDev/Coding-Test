package org.example.baekjoon.p10971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] graph;
    static boolean[] visited;
    static int first;
    static int cost;
    static int answer = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        graph = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            first = i;
            visited[i] = true;
            solve(0, first); // TODO 도시 2개일때 잘 되나 확인하기
            visited[i] = false;
        }

        System.out.println(answer);
    }

    static void solve(int visitCnt, int start) {
//        System.out.println(start+ "방문하는데까지 비용: " + cost);
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            if (graph[start][i] == 0) {
                continue;
            }
            visited[i] = true;
            cost += graph[start][i];
            solve(visitCnt + 1, i);
            visited[i] = false;
            cost -= graph[start][i];
        }

        if (visitCnt == N - 1) {
            if (graph[start][first] == 0) {
                return;
            }
            cost += graph[start][first];
//            System.out.println("비용: " + cost + "start = " + start);
            answer = Math.min(answer, cost);
            cost -= graph[start][first];
        }
//        System.out.println("끝 ===== ");
    }
}
