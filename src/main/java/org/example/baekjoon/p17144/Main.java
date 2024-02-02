package org.example.baekjoon.p17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 성공 / 구현 / 50분
public class Main {

    static final int EMPTY = 0;
    static final int MACHINE = -1;

    static int height;
    static int width;
    static int[][] room;

    static int machineBotI;
    static int machineBotJ;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        room = new int[height][width];
        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    machineBotI = i;
                    machineBotJ = j;
                }
            }
        }
//        for (int[] each : room) {
//            System.out.println(Arrays.toString(each));
//        }

        List<int[]> topRoot = createMachineTopRoot();
        List<int[]> botRoot = createMachineBotRoot();

        for (int i = 0; i < T; i++) {
            blowUp();
            useMachine(topRoot, botRoot);
//            for (int[] each : room) {
//                System.out.println(Arrays.toString(each));
//            }
//            System.out.println("---");
        }

        int answer = 2; // 진공청소기 -1 2개 있으니까
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                answer += room[i][j];
            }
        }
        System.out.println(answer);
    }

    static void useMachine(List<int[]> topRoot, List<int[]> botRoot) {
        for(int i = topRoot.size() - 1; i>=1; i--) {
            int[] target = topRoot.get(i);
            int[] next = topRoot.get(i - 1);
            room[target[0]][target[1]] = room[next[0]][next[1]];
        }
        int[] xx = topRoot.get(0);
        room[xx[0]][xx[1]] = 0;

        for(int i = botRoot.size() - 1; i>=1; i--) {
            int[] target = botRoot.get(i);
            int[] next = botRoot.get(i - 1);
            room[target[0]][target[1]] = room[next[0]][next[1]];
        }
        xx = botRoot.get(0);
        room[xx[0]][xx[1]] = 0;
    }

    static List<int[]> createMachineBotRoot() {
        List<int[]> routes = new ArrayList<>();
        int tempI = machineBotI;
        int tempJ = machineBotJ;
        for (int dir = 0; dir < 4; dir++) {
            boolean isFirst = true;
            while (true) {
                if (isFirst) {
                    isFirst = false;
                    tempI += dx[dir];
                    tempJ += dy[dir];
                }
                if (tempI < 0 || tempJ < 0 || tempI >= height || tempJ >= width) {
                    tempI -= dx[dir];
                    tempJ -= dy[dir];
                    break;
                }
                if (room[tempI][tempJ] == MACHINE) {
                    break;
                }
                routes.add(new int[]{tempI, tempJ});
                tempI += dx[dir];
                tempJ += dy[dir];
            }
        }
//        for (int[] route : routes) {
//            System.out.println(Arrays.toString(route));
//        }
        return routes;
    }

    static List<int[]> createMachineTopRoot() {
        int[] dirs = new int[]{0, 3, 2, 1};
        List<int[]> routes = new ArrayList<>();
        int tempI = machineBotI - 1;
        int tempJ = machineBotJ;
        for(int dir : dirs) {
            boolean isFirst = true;
            while (true) {
                if (isFirst) {
                    isFirst = false;
                    tempI += dx[dir];
                    tempJ += dy[dir];
                }
                if (tempI < 0 || tempJ < 0 || tempI >= height || tempJ >= width) {
                    tempI -= dx[dir];
                    tempJ -= dy[dir];
                    break;
                }
                if (room[tempI][tempJ] == MACHINE) {
                    break;
                }
                routes.add(new int[]{tempI, tempJ});
                tempI += dx[dir];
                tempJ += dy[dir];
            }
        }
//        for (int[] route : routes) {
//            System.out.println(Arrays.toString(route));
//        }
        return routes;
    }


    // 확산
    static void blowUp() {
        int[][] added = new int[height][width];
        int[][] removed = new int[height][width];

        // 동시에 확산시키기 위해 add되는 양, removed되는 양을 기록한다.
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (room[i][j] == EMPTY) {
                    continue;
                }
                int value = room[i][j] / 5;
                for (int dir = 0; dir < 4; dir++) {
                    int ni = i + dx[dir];
                    int nj = j + dy[dir];
                    if (ni < 0 || nj < 0 || ni >= height || nj >= width) {
                        continue;
                    }
                    if (room[ni][nj] == MACHINE) {
                        continue;
                    }
                    added[ni][nj] += value;
                    removed[i][j] += value;
                }
            }
        }
        // 확산시킨다
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                room[i][j] = room[i][j] + added[i][j] - removed[i][j];
            }
        }

//        for (int[] each : room) {
//            System.out.println(Arrays.toString(each));
//        }

    }

}
