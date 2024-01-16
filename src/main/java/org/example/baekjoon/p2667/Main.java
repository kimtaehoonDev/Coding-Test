package org.example.baekjoon.p2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Main {

    public static final int EMPTY = 0;
    public static final int INIT = 1;

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};

    static int[][] graph;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for(int i = 0; i<N; i++) {
            char[] input = br.readLine().toCharArray();
            for(int j = 0; j<N; j++) {
                graph[i][j] = input[j] - '0';
            }
        }

        // BFS로 색 변하게 만든다
        int color = 2;
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if (graph[i][j] == INIT) {
                    bfs(color++, Spot.getInstance(i, j));
                }
            }
        }

//        // 확인한다
//        for(int[] input : graph) {
//            System.out.println(Arrays.toString(input));
//        }

        // 색깔 -> 개수 반환
        Map<Integer, Integer> counts = new HashMap<>();
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<N; j++) {
                if (graph[i][j] == 0) {
                    continue;
                }
                if (!counts.containsKey(graph[i][j])) {
                    counts.put(graph[i][j], 0);
                }
                counts.put(graph[i][j], counts.get(graph[i][j]) + 1);
            }
        }

        StringJoiner sj = new StringJoiner("\n");
        sj.add(String.valueOf(counts.keySet().size()));
        List<Integer> list = counts.values().stream().collect(Collectors.toList());
        Collections.sort(list);
        for (Integer each : list) {
            sj.add(String.valueOf(each));
        }
        System.out.println(sj.toString());
    }

    public static void bfs(int color, Spot target) {
        Queue<Spot> queue = new LinkedList<>();
        queue.add(target);
        graph[target.x][target.y] = color;
        while (!queue.isEmpty()) {
            target = queue.poll();
            for(int dir = 0; dir < 4; dir++) {
                int nx = target.x + dx[dir];
                int ny = target.y + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (graph[nx][ny] == INIT) {
                    queue.add(Spot.getInstance(nx, ny));
                    graph[nx][ny] = color;
                }
            }
        }
    }

    static class Spot {
        static Spot[][] store = new Spot[N][N];

        static {
            for(int i = 0; i<N; i++) {
                for(int j = 0; j<N; j++) {
                    store[i][j] = new Spot(i, j);
                }
            }
        }

        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Spot getInstance(int x, int y) {
            return store[x][y];
        }

    }
}
