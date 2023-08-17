package org.example.baekjoon.p16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공/37분
public class Main {
    public static int N;
    public static int L;
    public static int R;
    public static int date;
    public static int[][] graph;
    public static int[] dx = new int[] {1, 0, -1, 0};
    public static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        date = 0;
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            int[][] visited = new int[N][N];
            int idx = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] != 0) {
                        continue;
                    }
                    bfs(visited, new Spot(i, j), idx++);
                }
            }

            if (idx == N * N + 1) {
                // 국가경계선을 공유하는 애들이 없으면
                break;
            }
            // 날짜 더한다
            date++;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                }
            }

            Map<Integer, Integer> peopleCnt = new HashMap<>();
            Map<Integer, Integer> nationCnt = new HashMap<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!peopleCnt.containsKey(visited[i][j])) {
                        peopleCnt.put(visited[i][j],0);
                        nationCnt.put(visited[i][j], 0);
                    }
                    peopleCnt.put(visited[i][j], peopleCnt.get(visited[i][j]) + graph[i][j]); // 조합에다가 사람 수를 더해줌
                    nationCnt.put(visited[i][j], nationCnt.get(visited[i][j]) + 1); // 하나의 연합에 몇 개의 국가가 속해있나
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = peopleCnt.get(visited[i][j]) / nationCnt.get(visited[i][j]);
                }
            }
        }

        System.out.println(date);
    }


    public static void bfs(int[][] visited, Spot start, int idx) {
        Queue<Spot> queue = new LinkedList<>();
        visited[start.x][start.y] = idx;
        queue.add(start);

        Spot now;
        while(!queue.isEmpty()) {
            now = queue.poll();

            // 이동할 수 있는 위치를 구한다
            for(int direction = 0; direction < 4; direction++) {
                int nx = now.x + dx[direction];
                int ny = now.y + dy[direction];

                if (nx < 0 || ny < 0 || N <= nx || N <= ny) {
                    continue;
                }
                // 이미 방문했다면
                if (visited[nx][ny] != 0) {
                    continue;
                }

                int gap = Math.abs(graph[nx][ny] - graph[now.x][now.y]);
                // 조건을 만족하지 못하면
                if (gap < L || gap > R) {
                    continue;
                }
                visited[nx][ny] = idx;
                queue.add(new Spot(nx, ny));
            }
        }


    }

    public static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

//        for(int i=0; i<N;i++) {
//            for(int j=0; j<N; j++) {
//
//            }
//        }

