package org.example.baekjoon.p11724;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int E = sc.nextInt();

        // 그래프 생성
        boolean[][] graph = new boolean[N+1][N+1];

        for(int i=0; i<E;i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            graph[start][end] = true;
            graph[end][start] = true;
        }

        boolean[] isVisited = new boolean[N+1]; // 인덱스0 사용안함
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
