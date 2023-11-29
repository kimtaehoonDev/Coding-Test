package org.example.baekjoon.p21610;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N; // 지도의 가로세로
    static int M;
    static int[][] myMap;
    static Set<Cloud> clouds;
    static int[] dx = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        myMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                myMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new HashSet<>();
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            execute(d, s);
        }

        int total = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j<N; j++) {
                total += myMap[i][j];
            }
        }
        System.out.println(total);
    }

    public static void execute(int d, int s) {
        d--;
        // 전체 구름을 이동하며 물을 1 추가한다
        Set<Cloud> moved = new HashSet<>();
        for (Cloud each : clouds) {
            int nx = (50 * N + each.x + dx[d] * s) % N;
            int ny = (50 * N + each.y + dy[d] * s) % N;
            moved.add(Cloud.get(nx, ny));
            myMap[nx][ny] += 1;
        }

        // 물이 있는 대각선만큼 물 추가한다
        for (Cloud each : moved) {
            for (int i = 1; i < 8; i += 2) {
                int nx = each.x + dx[i];
                int ny = each.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }
                if (myMap[nx][ny] > 0) {
                    myMap[each.x][each.y]++;
                }
            }
        }

        // 바구니에 저장된 물의 양이 2 이상, 구름이 없던 위치에 구름을 생성한다
        Set<Cloud> temp = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Cloud each = Cloud.get(i, j);
//                System.out.println("위치 : " + each);
//                System.out.println("물의 양 : " + myMap[each.x][each.y]);
//                System.out.println(moved);
//                System.out.println("기존 구름 포함 여부 " + moved.contains(each)); // 현재 잘 동작하지 않음...
//                System.out.println("===");
                if (myMap[each.x][each.y] >= 2 && !moved.contains(each)) {
                    myMap[each.x][each.y] -= 2;
                    temp.add(each);
                }
            }
        }
        clouds = temp;
//        System.out.println("새로운 구름의 위치");
//        for (Cloud cloud : temp) {
//            System.out.println(cloud);
//        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(myMap[i]));
//        }

    }

    static class Cloud {
        static Cloud[][] cloudMap = new Cloud[N][N];

        static {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    cloudMap[i][j] = new Cloud(i, j);
                }
            }
        }

        int x;
        int y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public boolean equals(Cloud cloud) {
            return this.x == cloud.x && this.y == cloud.y;
        }

        // TODO 암기하기
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        static Cloud get(int x, int y) {
            return cloudMap[x][y];
        }
    }
}
