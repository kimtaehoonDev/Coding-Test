package org.example.baekjoon.p20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1시간 55분 / 성공 / 오타 하나 못찾아서 50분 더 걸림...
// spread에서 6번째 움직임을 잘못 입력함. ([]를 잘못 닫음)
// 틀린 코드        : int ny = y + dy[(dir + 1) % 4 + dy[(dir + 2) % 4]];
// 올바르게 고친 코드 : int ny = y + dy[(dir + 1) % 4] + dy[(dir + 2) % 4];

public class Main {

    static int[] dx = new int[]{0, 1, 0, -1}; // 좌하우상 (0,-1) (1,0) (0,1) (-1,0)
    static int[] dy = new int[]{-1, 0, 1, 0};

    public static void main(String[] args) throws IOException { //
        // N, graph 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for(int[] each : graph) {
//            System.out.println(Arrays.toString(each));
//        }

        int x = N / 2;
        int y = x;

        // 방향을 틀고, 몇 칸 직진할지 값들을 저장한다.
        Queue<Integer> lines = new LinkedList<>();
        for (int i = 1; i < N; i++) {
            lines.add(i);
            lines.add(i);
        }
        lines.add(N - 1);
//        System.out.println(lines);

        // 방향은 좌하우상 반복한다.
        int answer = 0;
        int dir = -1;
        while (!lines.isEmpty()) {
            dir = (dir + 1) % 4;
            // 현재 방향으로 line만큼 이동한다.
            int line = lines.poll();
            for (int i = 0; i < line; i++) {
                x += dx[dir];
                y += dy[dir];
//                System.out.println(x + ", " + y + " 방문");
                answer += spread(x, y, dir, graph);
//                System.out.println(answer);
            }
        }

        System.out.println(answer);
    } // end

    //    graph[x][y]에 있는 모래를 비율만큼 뿌린다.
    // 왼쪽으로 바람이 불었을 때 기준으로 예시를 적어두면
//    0 (x + 2 * dx[i],y + 2 * dy[i]) 에는 5%를 뿌린다. / y의 2왼쪽
//    1 (x + dx[(i+3)%4]], y + dy[(i+3)%4]) 에는 7% / y의 상단
//    2 (x + dx[(i+1)%4]], y + dy[(i+1)%4]) 에는 7% / y의 하단
//    3 (x + dx[dir] + dx[(dir+3)%4], y + dy[dir] + dy[(dir+3)%4]) 에는 10% / y의 좌상단
//    4 (x + dx[dir] + dx[(dir+1)%4], y + dy[dir] + dy[(dir+1)%4]) 에는 10% / y의 좌하단
//    5 (x + dx[(dir+3)%4] + dx[(dir+2)%4], y + dy[(dir+3)%4] + dy[(dir+2)%4]) 에는 1% / y의 우상단
//    6 (x + dx[(dir+1)%4] + dx[(dir+2)%4], y + dy[(dir+1)%4 + dy[(dir+2)%4]]) 에는 1% / y의 우하단
//    7 (x + 2 * dx[(dir+3)%4]], y + 2 * dy[(dir+3)%4]) 에는 2% / y의 2상단
//    8 (x + 2 * dx[(dir+1)%4]], y + 2 * dy[(dir+1)%4]) 에는 2% / y의 2하단
//    나머지 a로
    public static int spread(int x, int y, int dir, int[][] graph) { // (x,y)가 y
        int sand = graph[x][y];
        graph[x][y] = 0;
        int result = 0;
        int acc = 0;

        // 0
        int moves = sand * 5 / 100; // 5퍼센트
        if (moves != 0) {
            int nx = x + 2 * dx[dir];
            int ny = y + 2 * dy[dir];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves; // 모래를 이동시킨다.
            }
            acc += moves; // 누적에 더해준다.
        }

        // 1
        moves = sand * 7 / 100;
        if (moves != 0) {
            int nx = x + dx[(dir + 3) % 4];
            int ny = y + dy[(dir + 3) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

        // 2
        moves = sand * 7 / 100;
        if (moves != 0) {
            int nx = x + dx[(dir + 1) % 4];
            int ny = y + dy[(dir + 1) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

        // 3
        moves = sand * 10 / 100;
        if (moves != 0) {
            int nx = x + dx[dir] + dx[(dir + 3) % 4]; // 변경
            int ny = y + dy[dir] + dy[(dir + 3) % 4]; // 변경
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

//    4 (x + dx[dir] + dx[(dir+1)%4], y + dy[dir] + dy[(dir+1)%4]) 에는 10% / y의 좌하단
        // 4
        moves = sand * 10 / 100;
        if (moves != 0) {
            int nx = x + dx[dir] + dx[(dir + 1) % 4];
            int ny = y + dy[dir] + dy[(dir + 1) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

//    5 (x + dx[(dir+3)%4] + dx[(dir+2)%4], y + dy[(dir+3)%4] + dy[(dir+2)%4]) 에는 1% / y의 우상단
        // 5
        moves = sand * 1 / 100;
        if (moves != 0) {
            int nx = x + dx[(dir + 3) % 4] + dx[(dir + 2) % 4];
            int ny = y + dy[(dir + 3) % 4] + dy[(dir + 2) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

//    6 (x + dx[(dir+1)%4] + dx[(dir+2)%4], y + dy[(dir+1)%4 + dy[(dir+2)%4]]) 에는 1% / y의 우하단
        // 6
        moves = sand * 1 / 100;
        if (moves != 0) {
            int nx = x + dx[(dir + 1) % 4] + dx[(dir + 2) % 4];
            int ny = y + dy[(dir + 1) % 4] + dy[(dir + 2) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

//    7 (x + 2 * dx[(dir+3)%4], y + 2 * dy[(dir+3)%4]) 에는 2% / y의 2상단
        // 7
        moves = sand * 2 / 100;
        if (moves != 0) {
            int nx = x + 2 * dx[(dir + 3) % 4];
            int ny = y + 2 * dy[(dir + 3) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

//    8 (x + 2 * dx[(dir+1)%4], y + 2 * dy[(dir+1)%4]) 에는 2% / y의 2하단
        // 8
        moves = sand * 2 / 100;
        if (moves != 0) {
            int nx = x + 2 * dx[(dir + 1) % 4];
            int ny = y + 2 * dy[(dir + 1) % 4];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
            acc += moves;
        }

        moves = sand - acc;
        if (moves != 0) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (outOfRange(nx, ny, graph)) {
                result += moves;
            } else {
                graph[nx][ny] += moves;
            }
        }

        return result; // graph바깥으로 나간 양을 반환한다.
    }
    //        // template
//        moves = sand * 퍼센티지 / 100;
//        if(moves != 0) {
//            int nx = x + dx[(dir+3)%4]; // 변경
//            int ny = y + dy[(dir+3)%4]; // 변경
//            if (outOfRange(nx, ny, graph)) {
//                result += moves;
//            } else {
//                graph[nx][ny] += moves;
//            }
//            acc += moves;
//        }

    public static boolean outOfRange(int nx, int ny, int[][] graph) {
        return nx < 0 || ny < 0 || nx >= graph.length || ny >= graph.length;
    }
}