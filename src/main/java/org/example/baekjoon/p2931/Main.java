package org.example.baekjoon.p2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

// TODO 못품... 나중에 다시 풀기
public class Main {
    static int BOT = 0;
    static int RIGHT = 1;
    static int TOP = 2;
    static int LEFT = 3;

    static int[] dx = new int[] {1, 0, -1, 0}; // 남동북서
    static int[] dy = new int[] {0, 1, 0, -1};
    static int M;
    static int N;
    static char[][] graph;
    static int[][] visited;

    static char EMPTY = '.';
    static char DEPARTURE = 'M';
    static char ARRIVAL = 'Z';
    static char MINUS = '-';
    static char PLUS = '+';
    static char BAR = '|';
    static char first = '1';
    static char second = '2';
    static char third = '3';
    static char fourth = '4';
    static char[] pipes = new char[] {MINUS, PLUS, BAR, first, second, third, fourth};
    static Node[] starts = new Node[4];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        graph = new char[M][N];
        visited = new int[M][N];

        // 그래프를 초기화한다
        for(int i=0; i<M; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                graph[i][j] = line[j];
                if (graph[i][j] == DEPARTURE) {
                    // 출발점을 기준으로 네 방향을 넣는다
                    for(int k = 0; k<4; k++) {
                        starts[k] = new Node(i,j,k, null);
                    }
                } else if (graph[i][j] == PLUS) {
                    visited[i][j] -= 1;
                }
            }
        }
//        for(int i=0; i<M; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        System.out.println(Arrays.toString(starts));
        // 블럭을 한개씩 추가한다
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if (graph[i][j] != '.') {
                    continue;
                }
                for(int k=0; k< pipes.length; k++) {
//                    if (i == 1 && j == 3 && k ==6) {
//                        graph[i][j] = pipes[k];
//                        boolean isAnswer = findRemovedBlock();
//                        for(int z=0; z<M; z++) {
//                            System.out.println(Arrays.toString(graph[z]));
//                        }
//                        System.out.println(isAnswer);
//                    }
                    for(int z=0; z<M; z++) {
                        System.out.println(Arrays.toString(visited[z]));
                    }

                    graph[i][j] = pipes[k];
                    boolean isAnswer = findRemovedBlock();
                    if (isAnswer) {
                        System.out.println((i + 1) + " " + (j + 1) + " " + k);
                        break;
                    }
                    System.out.println("\n\n");
                    graph[i][j] = '.';
                }
            }
        }

    }

    static boolean findRemovedBlock() {
        for(Node start: starts) {
            List<Node> stack = new ArrayList<>();
            stack.add(start);
            visited[start.x][start.y]++;

            boolean isAnswer = true;
            while(true) {
                Node now = stack.get(stack.size()-1);
                if (!isAnswer) {
                    for(Node each: stack) {
                        visited[each.x][each.y]  -= 1;
                    }
                    break;
                }

                if (graph[now.x][now.y] == ARRIVAL) {
                    return true;
                }

                int nx = now.x + dx[now.direction];
                int ny = now.y + dy[now.direction];
                if (nx < 0 || ny < 0 || nx >= M || ny >= N) { // 범위 벗어난 경우
                    isAnswer = false;
                    continue;
                }
                if (visited[nx][ny] == 1) { // 방문한 경우 - 한번씩 방문, + 같은 경우 두 번 다 방문
                    isAnswer = false;
                    continue;
                }

                // 현재 가스 방향 i를 graph[nx][ny]에서 받을 수 있나?
                Node next = move(now, graph[nx][ny], nx, ny);
                if (next == null) { // 다음 칸이 연결되어 있지 않음.
                    isAnswer = false;
                    continue;
                }
                visited[now.x][now.y] += 1; // 방문처리함
                stack.add(next);
            }
        }
        return false;
    }

    static Node move(Node now, char next, int nx, int ny) {
        // next가 now의 direction을 처리할 수 있니?
        if (now.direction == BOT) { // 남쪽
            if (next == ARRIVAL) {
                return new Node(nx, ny, now.direction, now);
            }
            if (next == '|') {
                return new Node(nx, ny, now.direction, now);
            }
            if (next == '+') {
                return new Node(nx, ny, now.direction, now);
            }
            if (next == '2') {
                return new Node(nx, ny, RIGHT, now); // 우측으로 이동
            }
            if (next == '3') {
                return new Node(nx, ny, LEFT, now); // 좌측 이동
            }
            return null;
        }
        if (now.direction == RIGHT) { // ->
            if (next == ARRIVAL) {
                return new Node(nx, ny, now.direction, now);
            }
            if (next == '-') {
                return new Node(nx, ny, now.direction, now);
            }
            if (next == '+') {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '3') {
                return new Node(nx, ny, TOP,now); // 위방향으로 이동
            }
            if (next == '4') {
                return new Node(nx, ny, BOT,now);
            }
            return null;
        }
        if (now.direction == TOP) { // 탑
            if (next == ARRIVAL) {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '|') {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '+') {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '1') {
                return new Node(nx, ny, RIGHT,now); // 위방향으로 이동
            }
            if (next == '4') {
                return new Node(nx, ny, LEFT,now);
            }
            return null;
        }
        if (now.direction == LEFT) { // 레프트
            if (next == ARRIVAL) {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '-') {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '+') {
                return new Node(nx, ny, now.direction,now);
            }
            if (next == '1') {
                return new Node(nx, ny, BOT,now); // 위방향으로 이동
            }
            if (next == '2') {
                return new Node(nx, ny, TOP,now);
            }
            return null;
        }
        return null;
    }

    static class Node {
        int x;
        int y;
        int direction;
        Node prev;

        public Node(int x, int y, int direction, Node prev) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.prev = prev;
        }

        public String toString() {
            return "(" + x + ", " + y + ", " + direction + ")";
        }
    }
}

// 3 7
//.......
//.M---Z.
//.......