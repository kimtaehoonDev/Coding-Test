package org.example.baekjoon.p15685;


import java.util.*;
import java.io.*;

// 성공 / 1회 실패 후 성공 / 2시간 30분
// 규칙을 애매하게 찾고 문제를 풀어서 실패함
// 확실하게 풀이 규칙 정하고 들어가기. 예를 들면 이 문제의 경우 드래곤커브가 어떤 규칙을 갖는지 수식화해두고 접근하기

public class Main {

    static int[] dx = new int[] { 0, -1, 0, 1 }; // 우상좌하
    static int[] dy = new int[] { 1, 0, -1, 0 }; // (0,1) (-1,0) (0,-1) (1,0)

    private static final int GENERATION = 3;
    private static final int DIR = 2;
    private static final int Y = 1;
    private static final int X = 0;

    public static void main(String[] args) throws IOException {
        boolean[][] graph = new boolean[102][102]; // 0<= <=101

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] curves = new int[N][4];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            curves[i][Y] = Integer.parseInt(input[0]);
            curves[i][X] = Integer.parseInt(input[1]);
            curves[i][DIR] = Integer.parseInt(input[2]);
            curves[i][GENERATION] = Integer.parseInt(input[3]);
        }
//      for(int[] curve : curves) {
//         System.out.println(Arrays.toString(curve));
//      }

        for (int[] each : curves) {
            curve(graph, each);
        }

//      for(boolean[] row : graph) {
//         System.out.println(Arrays.toString(row));
//      }
        int answer = 0;
        for(int i = 0; i<graph.length - 1; i++) {
            for(int j = 0; j<graph[0].length - 1; j++) {
                if (graph[i][j] && graph[i+1][j] && graph[i][j+1] && graph[i+1][j+1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);

    }

    private static void curve(boolean[][] graph, int[] input) {
        int startX = input[X];
        int startY = input[Y];
        List<Spot> spots = new ArrayList<>();
        // 시작 시 두 개의 점이 들어간다.
//      input[]
//      System.out.println(Spot.of(input[X], input[Y]));
//      System.out.println(Spot.of(input[X] + dx[input[DIR]], input[Y] + dy[input[DIR]]));
//      System.out.println("===");
        spots.add(Spot.of(startX, startY));

        int nx = startX + dx[input[DIR]];
        int ny = startY + dy[input[DIR]];
        spots.add(Spot.of(nx, ny));

        // 마지막 걸 기준으로 드래곤커브를 돌린다.
        for(int i = 0; i<input[GENERATION]; i++) {
            List<Spot> temp = new ArrayList<>();
            Spot standard = spots.get(spots.size() - 1);
            for(int j = spots.size() - 2; j>=0; j--) {
                Spot spot = spots.get(j);

                int xGap = spot.x - standard.x;
                int yGap = spot.y - standard.y;
                nx = standard.x + yGap;
                ny = standard.y - xGap;

                temp.add(Spot.of(nx, ny));
            }
            spots.addAll(temp);
        }
//      System.out.println(spots);

        // 그래프에 좌표를 찍는다.
        for(Spot spot : spots) {
            graph[spot.x][spot.y] = true;
        }
    }

    static class Spot {
        static Spot[][] caches = new Spot[102][102];

        static {
            for(int i = 0; i<102; i++) {
                for(int j = 0; j<102; j++) {
                    caches[i][j] = new Spot(i,j);
                }
            }
        }

        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static Spot of(int x, int y) {
            return caches[x][y];
        }

        public String toString() {
            return String.format("{x : %d, y : %d}", x, y);
        }
    }
}
