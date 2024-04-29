package org.example.baekjoon.p2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공 / 23분 / BFS
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ground = new int[N][N];
        int maxHeight = -1;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ground[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(ground[i][j], maxHeight);
            }
        }
//        for(int[] line : ground) {
//            System.out.println(Arrays.toString(line));
//        }
//        System.out.println(maxHeight);

        int maxSafeAreaCnt = 1; // (H가 0이면 1개)
        for (int h = 1; h < maxHeight; h++) {
            int safeAreaCnt = findSafeAreaCnt(h, ground);
//            System.out.println(String.format("비가 %d만큼 오면, 안전지대 개수는 %d", h, safeAreaCnt));
            maxSafeAreaCnt = Math.max(maxSafeAreaCnt, safeAreaCnt);
        }
        System.out.println(maxSafeAreaCnt);
    }

    static int INIT = 0;
    static int findSafeAreaCnt(int height, int[][] ground) {
        int groupNum = 1;
        int[][] groups = new int[ground.length][ground[0].length];
        for (int i = 0; i < ground.length; i++) {
            for (int j = 0; j < ground[0].length; j++) {
                if (ground[i][j] <= height) {
                    continue; // 물에 잠김
                }
                if (groups[i][j] != INIT) {
                    continue; // 이미 그룹에 포함됨
                }
                bfs(i,j,groupNum++, groups, ground, height);
            }
        }
//        for(int[] line : groups) {
//            System.out.println(Arrays.toString(line));
//        }
        return groupNum - 1;
    }

    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    static void bfs(int x, int y, int groupNum, int[][] groups, int[][] ground, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        groups[x][y] = groupNum; // 방문
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int d = 0; d<4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];
                if (nx < 0 || ny < 0 || nx >= ground.length || ny >= ground[0].length) {
                    continue; // out of range
                }
                if (ground[nx][ny] <= height) {
                    continue; // 물에 잠긴 곳
                }
                if (groups[nx][ny] != 0) {
                    continue; //이미 방문한 곳
                }
                queue.add(new int[] {nx, ny});
                groups[nx][ny] = groupNum;
            }
        }
    }
}
