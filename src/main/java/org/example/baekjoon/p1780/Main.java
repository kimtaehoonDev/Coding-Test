package org.example.baekjoon.p1780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// 성공 / 19분 / 재귀
public class Main {

    static int[] answers = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        for(int[] line : graph) {
//            System.out.println(Arrays.toString(line));
//        }
        splitPaper(graph, N, 0, 0);
        System.out.println(answers[0]);
        System.out.println(answers[1]);
        System.out.println(answers[2]);
    }

    static void splitPaper(int[][] graph, int len, int startX, int startY) {
        Set<Integer> status = new HashSet<>();
        boolean allSame = true;
        for (int i = startX; i < startX + len; i++) {
            for (int j = startY; j < startY + len; j++) {
                status.add(graph[i][j]);
                if (status.size() > 1) {
                    allSame = false;
                    break;
                }
            }
        }
        if (allSame) {
            // 반복 종료, status.get(0) 종이 한 장
            if (status.contains(-1)) {
                answers[0]++;
            } else if (status.contains(0)) {
                answers[1]++;
            } else {
                answers[2]++;
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            for(int j = 0; j<3; j++) {
                splitPaper(graph, len/3, startX + len/3 * i, startY + len/3 * j);
            }
        }
    }
}
