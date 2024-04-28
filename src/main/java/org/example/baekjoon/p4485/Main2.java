package org.example.baekjoon.p4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 다익스트라로 풀어봄
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> answers = new ArrayList<>();
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            int[][] graph = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
//            for(int[] line : graph) {
//                System.out.println(Arrays.toString(line));
//            }
            answers.add(solve(graph, N));
        }

        StringJoiner sj = new StringJoiner("\n");
        int idx = 1;
        for (int answer : answers) {
            sj.add(String.format("Problem %d: %d", idx++, answer));
        }
        System.out.println(sj.toString());
    }

    static int INF = 1000000000;
    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    static int solve(int[][] graph, int N) {
        int[][] distances = new int[N][N];

        for(int[] line : distances) {
            Arrays.fill(line, INF);
        }
        Queue<Node> q = new PriorityQueue<>((n1, n2) -> n1.distance - n2.distance); // 오름차순
        q.offer(new Node(0,0,graph[0][0])); // (0,0) 거리 0
        distances[0][0] = graph[0][0];

        while(!q.isEmpty()) {
            Node now = q.poll();
            if (now.x == N - 1 && now.y == N - 1) {
                break;
            }
            if (distances[now.x][now.y] == INF) {
                continue;
            }
            for(int d = 0; d<4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (distances[nx][ny] != INF) {
                    continue;
                }
                q.offer(new Node(nx, ny, now.distance + graph[nx][ny]));
                distances[nx][ny] = Math.min(distances[nx][ny], now.distance + graph[nx][ny]);
            }
        }

        return distances[N-1][N-1];
    }

    static class Node {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
