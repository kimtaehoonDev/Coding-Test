package org.example.baekjoon.p16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main2 {

    static int N;
    static int LOW;
    static int HIGH;
    static int[][] countries;
    static int[][] visited;

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        LOW = Integer.parseInt(st.nextToken());
        HIGH = Integer.parseInt(st.nextToken());
        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for (int[] line : countries) {
//            System.out.println(Arrays.toString(line));
//        }

        int day = 0;
        while (true) {
            day++;

            // 연합 찾기
            visited = new int[N][N];
            Map<Integer, Integer> peopleInGroup = new HashMap<>();
            Map<Integer, Integer> countriesInGroup = new HashMap<>();
            int groupIdx = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] != 0) { // 방문한적 O
                        continue;
                    }
                    dfs(i, j, peopleInGroup, countriesInGroup, groupIdx++);
                }
            }

            // 이동
            boolean moved = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 0) { // 방문한적 X
                        continue;
                    }
                    int nowGroup = visited[i][j];
                    if (countriesInGroup.get(nowGroup) <= 1) {
                        continue;
                    }
                    moved = true;
                    countries[i][j] = peopleInGroup.get(nowGroup) / countriesInGroup.get(nowGroup);
                }
            }
            if (!moved) {
                break;
            }
        }

        System.out.println(day - 1);
    }

    static void dfs(int x, int y, Map<Integer, Integer> peopleInGroup,
        Map<Integer, Integer> countriesInGroup, int groupIdx) {
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[]{x, y});
        visited[x][y] = groupIdx;
        int total = countries[x][y];
        int totalGroupCnt = 1;
        while (!stack.isEmpty()) {
            int[] removed = stack.remove(stack.size() - 1);
            for (int dir = 0; dir < 4; dir++) {
                int nx = removed[0] + dx[dir];
                int ny = removed[1] + dy[dir];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    // Out of range
                    continue;
                }
                if (visited[nx][ny] != 0) {
                    continue;
                }
                int prev = countries[removed[0]][removed[1]];
                int next = countries[nx][ny];
                int gap = Math.abs(prev - next);
                if (LOW <= gap && gap <= HIGH) {
                    stack.add(new int[]{nx, ny});
                    visited[nx][ny] = groupIdx;
                    total += countries[nx][ny];
                    totalGroupCnt++;
                }
            }
        }
        peopleInGroup.put(groupIdx, total);
        countriesInGroup.put(groupIdx, totalGroupCnt);
    }

}
