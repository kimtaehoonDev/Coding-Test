package org.example.baekjoon.p1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 1회 실패 -> 성공
// 풀이시간: 1회: 32분,
//         2회: + 3분
// 실패 사유: 모든 종료 조건에서 최대 길이를 계산하지 않고 누락함.
//          그래프의 범위를 벗어나거나, 이미 방문한 위치에 도달했을 때에도 해당 경우를 종료하고 동시에 연결된 최대 길이를 계산해야 했는데 생각하지 못함

public class Main {
    static int answer = 0;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};
    static int R, C;
    static char[][] graph;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                graph[i][j] = temp[j];
            }
        }


        // 첫 방문처리
        visited[0][0] = true;
        Set<Character> routes = new HashSet<>();
        routes.add(graph[0][0]);
        dfs(new Node(0, 0), routes);

        System.out.println(answer);
    }

    public static void dfs(Node now, Set<Character> routes) {
        for (int d = 0; d < 4; d++) {
            int nx = now.x + dx[d];
            int ny = now.y + dy[d];
            if (nx < 0 || ny < 0 || R <= nx || C <= ny) {
                answer = Math.max(answer, routes.size());
                continue;
            }
            if (visited[nx][ny]) {
                answer = Math.max(answer, routes.size());
                continue;
            }

            // 같은 문자열이 나와서 해당 방향으로 더 진행 불가능
            if (routes.contains(graph[nx][ny])) {
                answer = Math.max(answer, routes.size());
                continue;
            }

            Node next = new Node(nx, ny);
            routes.add(graph[nx][ny]);
            visited[nx][ny] = true;
            dfs(next, routes);
            routes.remove(graph[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
