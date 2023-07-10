package org.example.baekjoon.p1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //정점개수
        int M = Integer.parseInt(st.nextToken()); //간선개수
        int start = Integer.parseInt(st.nextToken()); //시작점

        boolean[] isVisitedByDfs = new boolean[N+1];
        boolean[] isVisitedByBfs = new boolean[N+1];
        boolean[][] graph = new boolean[N+1][N+1];

        // 그래프 초기화
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n1][n2] = true;
            graph[n2][n1] = true;
        }
        List<Integer> dfsResult = new ArrayList<>(N);
        List<Integer> bfsResult = new ArrayList<>(N);
        dfs(start, graph, isVisitedByDfs, dfsResult);
        bfs(start, graph, isVisitedByBfs, bfsResult);

        StringJoiner sj = new StringJoiner(" ");
        for (Integer result : dfsResult) {
            sj.add(String.valueOf(result));
        }
        System.out.println(sj);

        sj = new StringJoiner(" ");
        for (Integer result : bfsResult) {
            sj.add(String.valueOf(result));
        }
        System.out.println(sj);
    }
    public static void dfs(int node, boolean[][] graph, boolean[] isVisited, List<Integer> result) {
        if (isVisited[node]) {
            return;
        }
        // 방문처리를 한다
        isVisited[node] = true;
        result.add(node);
        boolean[] isConnected = graph[node];
        for(int i=1; i<isConnected.length; i++) { //작은 번호를 먼저 방문한다
            if (isConnected[i] && !isVisited[i]) {
                    dfs(i, graph, isVisited, result);
            }
        }
    }

    public static void bfs(int node, boolean[][] graph, boolean[] isVisited, List<Integer> result) {
        if (isVisited[node]) {
            return;
        }
        // 큐를 사용한다
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        isVisited[node] = true;
        result.add(node);

        while(!queue.isEmpty()) {
            int target = queue.poll();

            boolean[] isConnected = graph[target];
            for(int i=1; i<isConnected.length;i++) {
                if (isConnected[i] && !isVisited[i]) {
                    queue.add(i);
                    isVisited[i] = true;
                    result.add(i);
                }
            }
        }
    }
}
