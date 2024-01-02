package org.example.baekjoon.p3085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 성공 / 구현
class Main {
    static int answer = 0;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            int j = 0;
            for (char each : br.readLine().toCharArray()) {
                board[i][j++] = each;
            }
        }

        // 교환전 최대 길이를 찾아라
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                findMaxLength(board, i, j, N);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char target = board[i][j];
                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if (ni < 0 || ni >= N || nj < 0 || nj >= N) {
                        continue;
                    }
                    board[i][j] = board[ni][nj];
                    board[ni][nj] = target;
                    findMaxLength(board, i, j, N);
                    board[ni][nj] = board[i][j];
                    board[i][j] = target;
                }
            }
        }

        System.out.println(answer);
    }

    private static void findMaxLength(char[][] board, int i, int j, int N) {
        char target = board[i][j];
        int height = 1;
        int di = i - 1;
        while (di >= 0) {
            if (board[di][j] == target) {
                height++;
                di--;
                continue;
            }
            break;
        }
        di = i + 1;
        while (di < N) {
            if (board[di][j] == target) {
                height++;
                di++;
                continue;
            }
            break;
        }

        int width = 1;
        int dj = j - 1;
        while (dj >= 0) {
            if (board[i][dj] == target) {
                width++;
                dj--;
                continue;
            }
            break;
        }
        dj = j + 1;
        while (dj < N) {
            if (board[i][dj] == target) {
                width++;
                dj++;
                continue;
            }
            break;
        }
//                System.out.println(i+"/"+j);
//                System.out.println(width);
//                System.out.println(height);
//                System.out.println("");
        int max = Math.max(width, height);
        answer = Math.max(answer, max);
    }
}
