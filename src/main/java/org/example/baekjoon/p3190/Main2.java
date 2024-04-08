package org.example.baekjoon.p3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 성공 / 54분 / 구현
public class Main2 {

    static int[] dx = new int[]{-1, 0, 1, 0}; // 상 우 하 좌
    static int[] dy = new int[]{0, 1, 0, -1};

    static int N;
    static char EMPTY = 'O';
    static char APPLE = 'A';
    static char BODY = 'S';

    static String LEFT = "L";
    static String RIGHT = "D";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        char[][] board = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], EMPTY);
        }
        int K = Integer.parseInt(br.readLine()); // 사과 개수
        for (int i = 0; i < K; i++) {
            String[] input1 = br.readLine().split(" ");
            int x = Integer.parseInt(input1[0]) - 1;
            int y = Integer.parseInt(input1[1]) - 1;
            board[x][y] = APPLE;
        }
//        for(int i = 0; i<N; i++) {
//            System.out.println(Arrays.toString(board[i]));
//        }

        // moves를 초기화한다. moves {초, 회전할 방향}이 저장된다.
        Map<Integer, String> moves = new HashMap<>();
        int L = Integer.parseInt(br.readLine()); // 방향 전환 개수
        for (int i = 0; i < L; i++) {
            String[] input1 = br.readLine().split(" ");
            int time = Integer.parseInt(input1[0]);
            moves.put(time, input1[1]);
        }
//        System.out.println(moves);

        Deque<Spot> snakes = new LinkedList<>();
        int dir = 1;
        snakes.addLast(Spot.of(0, 0));
        board[0][0] = BODY;
        int time = 0;
        while (true) {
            time++;

            //머리를 빼꼼한다.
            Spot head = snakes.peekLast();
            Spot next = head.move(dir);
//            System.out.println("빼꼼한 위치는 " + next);
            if (next.outOfRange()) {
                break;
            }

            // 빼꼼한 곳에 있는 물건에 따라 다른 행동을 한다.
            if (board[next.x][next.y] == APPLE) {
                snakes.addLast(next);
                board[next.x][next.y] = BODY;
            } else if (board[next.x][next.y] == EMPTY) {
                Spot removed = snakes.pollFirst();
                board[removed.x][removed.y] = EMPTY;
                snakes.addLast(next);
                board[next.x][next.y] = BODY;
            } else { // 바디나 벽
                break;
            }

            // 방향을 틀어야 하면 방향을 튼다
            if (moves.containsKey(time)) {
                if (moves.get(time).equals(RIGHT)) {
                    dir = (dir + 1) % 4;
                } else {
                    dir = (dir + 3) % 4;
                }
            }
        }
        System.out.println(time);

    }


    static class Spot {

        static Spot[][] stores;
        static Spot empty;

        static {
            empty = new Spot(-1,-1);
            stores = new Spot[101][101];
            for (int i = 0; i <= 100; i++) {
                for (int j = 0; j <= 100; j++) {
                    stores[i][j] = new Spot(i, j);
                }
            }
        }

        final int x;
        final int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        static Spot of(int x, int y) {
            if (x < 0 || y < 0 || N <= x || N <= y) {
                return empty;
            }
            return stores[x][y];
        }

        boolean outOfRange() {
            if (this.x < 0 || this.y < 0 || N <= this.x || N <= this.y) {
                return true;
            }
            return false;
        }

        public Spot move(int dir) {
            return Spot.of(this.x + dx[dir], this.y + dy[dir]);
        }

        public String toString() {
            return "{x : " + x + ", y : " + y + "}";
        }
    }
}
