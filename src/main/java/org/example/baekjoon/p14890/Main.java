package org.example.baekjoon.p14890;

import java.util.*;
import java.io.*;

// 성공/풀이시간: 46분
// 중간에 solve -> solveGaro를 복사 붙여넣기 하는 과정에서 i,j 를 제대로 매핑하지 못함
// 해당 부분의 오류를 디버깅하는데 많은 시간 소요.

public class Main {
    public static int[][] graph;
    public static int N;
    public static int L;
    public static void main(String []args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // --->
        for(int i=0; i<N; i++) {
            boolean canCross = solveGaro(i);
            if (canCross) {
                answer ++;
            }
        }
        //^
        //|
        for(int i=0; i<N; i++) {
            boolean canCross = solve(i);
            if (canCross) {
                answer ++;
            }
        }
        System.out.println(answer);
    }

    public static boolean solve(int j) {
        int prev = 0;
        int same = 0;
        for(int i=0; i<N; i++) {
            if(i==0) {
                prev = graph[i][j];
                same++;
                continue;
            }
            // same height
            if(graph[i][j] == prev) {
                same++;
                continue;
            }
            // gap 1 up
            if (Math.abs(graph[i][j] - prev) > 1) {
                return false;
            }

            // prev is small
            if (graph[i][j] > prev) {
                if (same < L) {
                    return false;
                }
                prev = graph[i][j];
                same = 1;
                continue;
            }

            if (graph[i][j] < prev) {
                if (i + L > N) {
                    return false;
                }
                for(int newI = i; newI<i + L;newI++) {
                    if (graph[newI][j] != graph[i][j]) {
                        return false;
                    }
                }
                same = 0;
                prev = graph[i+ L - 1][j];
                i += (L-1);
            }
        }

        return true;
    }

    public static boolean solveGaro(int i) {
        int prev = 0;
        int same = 0;
        for(int j=0; j<N; j++) {
            if(j==0) {
                prev = graph[i][j];
                same++;
                continue;
            }
            // same height
            if(graph[i][j] == prev) {
                same++;
                continue;
            }
            // gap 1 up
            if (Math.abs(graph[i][j] - prev) > 1) {
                return false;
            }

            // prev is small
            if (graph[i][j] > prev) {
                if (same < L) {
                    return false;
                }
                prev = graph[i][j];
                same = 1;
                continue;
            }

            if (graph[i][j] < prev) {
                if (j + L > N) {
                    return false;
                }
                for(int newJ = j; newJ<j + L;newJ++) {
                    if (graph[i][newJ] != graph[i][j]) {
                        return false;
                    }
                }
                same = 0;
                prev = graph[i][j + L - 1];
                j += (L-1);
            }
        }

        return true;
    }
}