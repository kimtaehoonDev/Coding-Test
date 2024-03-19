package org.example.baekjoon.p11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static char[][] game;
    static int[][] visited;
    static int groupCnt;
    static Map<Integer, Integer> puyoCntInGroup;

    public static void main(String[] args) throws IOException {
        // 입력받는다.
        game = new char[12][6];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            char[] input = br.readLine().toCharArray();
            game[i] = input;
        }
//        for (char[] chars : game) {
//            System.out.println(Arrays.toString(chars));
//        }

        // while
        boolean result = true;
        int cnt = 0;
        while (result) {
            result = execute();
            cnt++;
        }
        System.out.println(cnt - 1);
        // while end

    }

    static boolean execute() {
        visited = new int[12][6];
        groupCnt = 1;
        puyoCntInGroup = new HashMap<>();

        // mark
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[0].length; j++) {
                if (visited[i][j] != 0) {
                    continue;
                }
                if (game[i][j] == '.') {
                    // empty place
                    continue;
                }
                // 방문한적 없으면
                dfs(i, j);
                groupCnt++;
            }
        }
//        System.out.println(puyoCntInGroup);
//        for (int[] ints : visited) {
//            System.out.println(Arrays.toString(ints));
//        }

        // sweep
        boolean popped = false;
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[0].length; j++) {
                if (game[i][j] == '.') {
                    // empty place
                    continue;
                }
                int puyoCnt = puyoCntInGroup.get(visited[i][j]);
                if (puyoCnt < 4) {
                    continue;
                }
                popped = true;
                game[i][j] = '.';
            }
        }
//        for (char[] row : game) {
//            System.out.println(Arrays.toString(row));
//        }
        if (!popped) {
            return false;
        }

        // rearrange
        for (int col = 0; col < game[0].length; col++) {
            List<Character> chars = new ArrayList<>();
            for (int row = game.length - 1; row >= 0; row--) {
                if (game[row][col] == '.') {
                    continue;
                }
                chars.add(game[row][col]);
            }
//            System.out.println(chars);
            int idx = game.length - 1;
            for (Character each : chars) {
                game[idx--][col] = each;
            }
            for (int i = idx; i >= 0; i--) {
                game[i][col] = '.';
            }
        }
//        for (char[] row : game) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("-");
        return true;
    }

    static void dfs(int x, int y) {
        List<int[]> stack = new ArrayList<>();
        stack.add(new int[]{x, y});
        visited[x][y] = groupCnt;
        int cnt = 1;
        while (!stack.isEmpty()) {
            int[] removed = stack.remove(stack.size() - 1);
            for (int dir = 0; dir < 4; dir++) {
                int nx = removed[0] + dx[dir];
                int ny = removed[1] + dy[dir];
                if (nx < 0 || ny < 0 || nx >= game.length || ny >= game[0].length) {
                    // out of range
                    continue;
                }
                if (visited[nx][ny] != 0) {
                    // already visited
                    continue;
                }
                if (game[x][y] != game[nx][ny]) {
                    // another group
                    continue;
                }
                stack.add(new int[]{nx, ny});
                visited[nx][ny] = groupCnt;
                cnt++;
            }
        }
        puyoCntInGroup.put(groupCnt, cnt);
    }

}
