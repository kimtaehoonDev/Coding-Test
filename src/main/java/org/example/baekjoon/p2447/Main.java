package org.example.baekjoon.p2447;

import java.util.Scanner;

public class Main {
    // ary: false -> 공백, true면 *을 의미한다
    static boolean[][] ary;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ary = new boolean[N][N];
        star(N, 0, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (ary[i][j]) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void star(int N, int startX, int startY) {
        // 재귀 종료조건
        if (N == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        ary[startX+1][startY+1] = false;
                        continue;
                    }
                    ary[startX + i][startY + j] = true;
                }
            }
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                star(N/3, startX + i * (N/3), startY + j * (N/3));
            }
        }
    }
}
