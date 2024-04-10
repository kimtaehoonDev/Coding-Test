package org.example.baekjoon.p14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 1회 실패 후 성공 / 1시간 / 구현
// 회전이 가능하다는 문제 조건을 놓쳐서 1번 틀림
public class Main2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // row
        int M = Integer.parseInt(st.nextToken()); // col
        int[][] graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for(int[] line : graph) {
//            System.out.println(Arrays.toString(line));
//        }
        // N, M, graph 초기화

        // 테트로미노 4개 생성
        Tetromino t1 = new Tetromino(new int[][]{
            new int[]{0, 0},
            new int[]{0, 1},
            new int[]{0, 2},
            new int[]{0, 3},
        }, false); // ---- 테트로미노

        Tetromino t2 = new Tetromino(new int[][]{
            new int[]{0, 0},
            new int[]{0, 1},
            new int[]{1, 0},
            new int[]{1, 1}
        }, false); // ㅁ 테트로미노

        Tetromino t3 = new Tetromino(new int[][]{
            new int[]{0, 0},
            new int[]{1, 0},
            new int[]{2, 0},
            new int[]{2, 1}
        }, true); // ㄴ 테트로미노

        Tetromino t32 = new Tetromino(new int[][]{
            new int[]{2, 0},
            new int[]{2, 1},
            new int[]{1, 1},
            new int[]{0, 1}
        }, true); // ㄴ대칭 테트로미노

        Tetromino t4 = new Tetromino(new int[][]{
            new int[]{0, 0},
            new int[]{1, 0},
            new int[]{1, 1},
            new int[]{2, 1}
        }, false); // 번개 테트로미노

        Tetromino t42 = new Tetromino(new int[][]{
            new int[]{0, 1},
            new int[]{1, 0},
            new int[]{1, 1},
            new int[]{2, 0}
        }, false); // 번개 대칭 테트로미노

        Tetromino t5 = new Tetromino(new int[][]{
            new int[]{0, 0},
            new int[]{0, 1},
            new int[]{0, 2},
            new int[]{1, 1}
        }, true); // ㅜ 테트로미노

        int answer = 0;
        List<Tetromino> tetrominos = new ArrayList<>(List.of(t1, t2, t3, t32, t4, t42, t5));
        for (Tetromino tetromino : tetrominos) {
            for (int dir = 0; dir < 4; dir++) {
                if (dir >= 2 && !tetromino.meaningful) {
                    // 반복되는 모형이라 볼 필요 없음
                    continue;
                }
                if (dir != 0) {
                    tetromino.rotate();
                }
//                for(int[] e : tetromino.inners) {
//                    System.out.println(Arrays.toString(e));
//                }
//                System.out.println("==");
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        // i,j를 시작으로 테트로미노 놓을 수 있는지 확인한다.
                        // 테트로미노의 각 점...
                        int tetrominoValue = 0;
                        boolean canAnswer = true;
                        for (int[] inner : tetromino.inners) {
                            int nx = inner[0] + i;
                            int ny = inner[1] + j;
                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                canAnswer = false;
                                break;
                            }
                            tetrominoValue += graph[nx][ny];
                        }
                        if (canAnswer) {
                            answer = Math.max(answer, tetrominoValue);
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static class Tetromino {

        int[][] inners;
        boolean meaningful;

        // (0,0)부터 4개의 값의 상대적 위치와, 180도 회전이 의미있는지 입력
        public Tetromino(int[][] inners, boolean meaningful) {
            if (inners.length != 4) {
                throw new RuntimeException("잘못된 초기화. 4개의 블록이 아닙니다.");
            }
            this.inners = inners;
            this.meaningful = meaningful;
        }

        // 90도 회전한다.
        void rotate() {
            int[][] temp = new int[4][2];
            int minX = 1000;
            int minY = 1000;
            for (int i = 0; i < 4; i++) {
                int x = inners[i][0];
                int y = inners[i][1];
                temp[i][0] = 3 - y;
                temp[i][1] = x;
                minX = Math.min(temp[i][0], minX);
                minY = Math.min(temp[i][1], minY);
            }
            for (int[] each : temp) {
                each[0] -= minX;
                each[1] -= minY;
            }
            inners = temp;

            // 가장 왼쪽상단점 찾아 -> 0,0으로 만든다
        }

    }
}
