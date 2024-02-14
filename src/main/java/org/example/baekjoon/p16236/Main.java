package org.example.baekjoon.p16236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공 / 1시간 / 구현
// 처음에 생각없이 bfs로 했다가 틀린 이유를 찾기 어려웠다.
// 타겟이 될 물고기는 [가까운 -> 가장 위 -> 가장 왼쪽] 순서로 우선순위를 갖는다.
// 이 때, bfs를 상좌하우 순서로 돌리면 위 제약조건에 알맞은 물고기를 찾을 수 있다고 착각했다.
// 첫 번째로 bfs가 방문한 물고기의 위치가 (0,1) 이고 그 이후에 (0,0) 물고기가 나올 수 있다 -> 이 때 (0,0)이 더 왼쪽이니 이놈 먹어야함

public class Main {

    static final int SHARK = 9;
    static final int EMPTY = 0;

    static int N;
    static int[][] graph;
    static int[] sharkLocation;
    static int sharkSize = 2;
    static int eatingCnt = 0;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == SHARK) {
                    sharkLocation = new int[]{i, j};
                    graph[i][j] = 0;
                }
            }
        }
//        for(int[] line: graph) {
//            System.out.println(Arrays.toString(line));
//        }
//        System.out.println(Arrays.toString(sharkLocation));

        // 상어 이동 시작
        int[] next = new int[]{sharkLocation[0], sharkLocation[1]};
        while (true) { // 물고기 없어질 때까지
            next = findTarget(next[0], next[1]);
//            System.out.println(
//                Arrays.toString(next) + "상어 현재 크기 " + sharkSize + ", 먹은 개수 " + eatingCnt + ", 이동거리 " + time);
            if (next[0] == -1 && next[1] == -1) {
                break;
            }
            time += next[2];
            eatingCnt++;
            graph[next[0]][next[1]] = EMPTY;
            if (sharkSize == eatingCnt) {
                eatingCnt = 0;
                sharkSize++;
            }
        }
        System.out.println(time);
    }

    // 샤크가 먹을 다음 물고기의 [x,y,이동거리] -> 만약 없다면 && ㅇㅇ 리턴

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[] findTarget(int firstX, int firstY) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{firstX, firstY, 0});
        visited[firstX][firstY] = true;
        Queue<int[]> heap = new PriorityQueue<>((a1, a2) -> {
            if (a1[2] != a2[2]) {
                return a1[2] - a2[2]; // 길이 짧은 순 정렬
            }
            if (a1[0] != a2[0]) {
                return a1[0] - a2[0];
            }
            return a1[1] - a2[1];
        });
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = next[0] + dx[i];
                int ny = next[1] + dy[i];
                // 범위 벗어날 수 없음
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                // 이미 왔던곳 또 올 수 없음
                if (visited[nx][ny]) {
                    continue;
                }
                // 지보다 큰 놈 지나갈 수 없음
                if (graph[nx][ny] > sharkSize) {
                    continue;
                }
                // 물고기면
                if (graph[nx][ny] > 0) {
                    //먹을 수 있으면
                    if (graph[nx][ny] < sharkSize) {
                        heap.add(new int[]{nx, ny, next[2] + 1});
                    }
                }
                // 나랑 똑같은 사이즈거나 빈 공간
                queue.add(new int[]{nx, ny, next[2] + 1});
                visited[nx][ny] = true;
            }
        }
        if (heap.isEmpty()) {
            return new int[]{-1, -1, -1};
        }
        return heap.poll();
    }

}
