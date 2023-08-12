package org.example.baekjoon.p14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// 구현/26분
public class Main {
    public static final int DIRTY = 0;
    public static final int WALL = 1;
    public static final int CLEAN = 2;

    public static final int[] dx = new int[] {-1, 0, 1, 0}; // 북동남서 반시계 할라면 음수로 돌기
    public static final int[] dy = new int[] {0, 1, 0, - 1};

    public static int totalCnt = 0;
    public static int[][] graph;

    public static int robotX;
    public static int robotY;
    public static int robotDirection;

    public static int N;
    public static int M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        st = new StringTokenizer(br.readLine());
        robotX = Integer.parseInt(st.nextToken());
        robotY = Integer.parseInt(st.nextToken());
        robotDirection = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(totalCnt);
    }

    public static void solve() {
        while (true) {
            if (graph[robotX][robotY] == DIRTY) {
                totalCnt++;
                graph[robotX][robotY] = CLEAN;
            }

            boolean clean = true;
            for(int i=0; i<4; i++) {
                int nx = robotX + dx[i];
                int ny = robotY + dy[i];

                if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                    continue;
                }
                if (graph[nx][ny] == DIRTY) {
                    clean = false;
                    break;
                }
            }

            if (clean) {
                // 한칸 후진
                int back = (robotDirection + 2) % 4;
                int nx = robotX + dx[back];
                int ny = robotY + dy[back];
                if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                    return; //종료
                }
                if (graph[nx][ny] == WALL) {
                    return;
                }
                robotX = nx;
                robotY = ny;
            } else {
                robotDirection = (robotDirection + 3) % 4;
                int nx = robotX + dx[robotDirection];
                int ny = robotY + dy[robotDirection];
                if (nx < 0 || ny < 0 || N <= nx || M <= ny) {
                    continue;
                }
                if (graph[nx][ny] == DIRTY) {
                    robotX = nx;
                    robotY = ny;
                }
            }
        }
    }
}
