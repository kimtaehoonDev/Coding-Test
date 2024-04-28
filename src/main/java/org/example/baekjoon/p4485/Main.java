package org.example.baekjoon.p4485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 성공 / 28분 / bfs(약간변형)
public class Main {

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

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static int solve(int[][] graph, int N) {
        boolean[][] visited = new boolean[N][N];
        Queue<Node> heap = new PriorityQueue<>((n1, n2) -> { // acc 오름차순
            return n1.acc - n2.acc;
        });
        heap.offer(new Node(0, 0, graph[0][0]));
        while (!heap.isEmpty()) {
            Node target = heap.poll();
//            System.out.println(target.x + "," + target.y + ": " + target.acc);
            if (visited[target.x][target.y]) {
                continue;
            }
            visited[target.x][target.y] = true; // 여기서 처리
            if (target.x == N - 1 && target.y == N - 1) {
                return target.acc;
            }

            for (int d = 0; d < 4; d++) {
                int nx = target.x + dx[d];
                int ny = target.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                heap.add(new Node(nx, ny, target.acc + graph[nx][ny]));
            }
        }

        return -1;
    }

    static class Node {

        int x;
        int y;
        int acc;

        public Node(int x, int y, int acc) {
            this.x = x;
            this.y = y;
            this.acc = acc;
        }
    }
}
