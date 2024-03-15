package org.example.baekjoon.p1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;

// 24분 / 성공 / bfs
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            int M = Integer.parseInt(input[0]);
            int N = Integer.parseInt(input[1]);
            int K = Integer.parseInt(input[2]);
            List<int[]> lottus = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                String[] input1 = br.readLine().split(" ");
                int x = Integer.parseInt(input1[0]);
                int y = Integer.parseInt(input1[1]);
                lottus.add(new int[]{x, y});
            }

            int answer = solve(M, N, lottus);
            answers.add(answer);
        }
        StringJoiner sb = new StringJoiner("\n");

        for (Integer answer : answers) {
            sb.add(String.valueOf(answer));
        }
        System.out.println(sb.toString());
    }

    static int solve(int M, int N, List<int[]> lottus) {
        boolean[][] graph = new boolean[M][N];
        for (int[] each : lottus) {
            graph[each[0]][each[1]] = true;
        }
        int[][] groups = new int[M][N];
        int groupNum = 1;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!graph[i][j]) {
                    // 배추x
                    continue;
                }
                if (groups[i][j] != 0) {
                    // visit
                    continue;
                }
                bfs(i, j, graph, groups, groupNum++);
            }
        }
//        for (int[] group : groups) {
//            System.out.println(Arrays.toString(group));
//        }
        return groupNum - 1;
    }

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static void bfs(int i, int j, boolean[][] graph, int[][] groups, int groupNum) {
        Queue<Node> heap = new LinkedList<>();
        heap.offer(new Node(i, j));
        groups[i][j] = groupNum;
        while (!heap.isEmpty()) {
            Node target = heap.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = target.x + dx[dir];
                int ny = target.y + dy[dir];
                if (nx < 0 | ny < 0 | nx >= graph.length | ny >= graph[0].length) {
                    // out of range
                    continue;
                }
                if (!graph[nx][ny]) {
                    // 배추x
                    continue;
                }
                if (groups[nx][ny] != 0) {
                    // visit
                    continue;
                }
                heap.offer(new Node(nx, ny));
                groups[nx][ny] = groupNum;
            }
        }
    }

    static class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
