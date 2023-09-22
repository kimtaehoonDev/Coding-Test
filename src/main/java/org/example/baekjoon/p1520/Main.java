package org.example.baekjoon.p1520;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final long INIT = -1L;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int M;
    static int N;
    static long[][] dp;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new long[M][N];
        graph = new int[M][N];


        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                dp[i][j] = INIT;
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = 1L;

        System.out.println(solve(M - 1, N - 1, -1));
//        for(int i=0; i<M; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
    }

    static long solve(int x, int y, int prevHeight) {
        if (x < 0 || y < 0 || M <= x || N <= y) {
            return 0;
        }
        if (graph[x][y] <= prevHeight) {
            return 0L;
        }
        if (dp[x][y] != INIT) {
            return dp[x][y];
        }

        int temp = 0;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            temp += solve(nx, ny, graph[x][y]);
        }
        dp[x][y] = temp;
        return dp[x][y];
    }
}
