package org.example.baekjoon.p11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 그래프 생성
        boolean[][] graph = new boolean[N+1][N+1];
        boolean[] isVisited = new boolean[N+1]; // 인덱스0 사용안함

        for(int i=0; i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start][end] = true;
            graph[end][start] = true;
        }

        int answer = 0;
        for(int i=1; i<N+1;i++) {
            if (isVisited[i]) {
                continue;
            }
            dfs(i, graph, isVisited);
            answer += 1;
        }

        System.out.println(answer);
    }

    public static void dfs(int start, boolean[][] graph, boolean[] isVisited) {
        isVisited[start] = true;
        // 그래프와 연결된 곳 다 방문한다
        boolean[] isConnected = graph[start];
        for(int i=1; i<isConnected.length; i++) {
            if (isConnected[i]) {
                if (!isVisited[i]) {
                    dfs(i, graph, isVisited);
                }
            }
        }
    }
}
