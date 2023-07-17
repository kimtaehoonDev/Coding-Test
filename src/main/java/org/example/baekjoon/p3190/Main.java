package org.example.baekjoon.p3190;

import java.util.*;
import java.io.*;

public class Main {
    static int EMPTY = 0;
    static int WALL = 1;
    static int SNAKE = 9;
    static int APPLE = 7;

    static int[][] board;
    static int[] dx = new int[4];
    static int[] dy = new int[4];
    static List<Command> commands;

    /**
     * N: 보드크기
     * K: 사과개수
     */
    public static void main(String[] args) throws IOException {
        init();
        initDxDy();

        int endingTime = solve();
        System.out.println(endingTime);
    }

    private static int solve() {
        boolean isEnd = false;
        int direction = 0;
        Spot spot = new Spot(1,1); // 시작위치 1,1
        int time = 0;
        Queue<Spot> snake = new LinkedList<>();
        snake.add(new Spot(1,1));
        for(Command nextCommand: commands) {
            if (isEnd) {
                break;
            }
            for(int i = time + 1; i<=nextCommand.time; i++) {
                time++; //시간을 1초 늘린다
                spot.move(direction);
                if (spot.is(WALL) || spot.is(SNAKE)) {
                    isEnd = true;
                    break;
                }
                if (spot.is(APPLE)) {
                    board[spot.x][spot.y] = SNAKE;
                    snake.add(new Spot(spot.x, spot.y));
                    continue;
                }
                // EMPTY로 이동한 경우
                board[spot.x][spot.y] = SNAKE;
                snake.add(new Spot(spot.x, spot.y));
                Spot tailSpot = snake.poll();
                board[tailSpot.x][tailSpot.y] = EMPTY;
            }

            // 고개 돌리기
            if (nextCommand.movingRule.equals("L")) {
                // 왼쪽으로 고개 돌리기
                direction = (direction + 3) % 4;
            } else {
                direction = (direction + 1) % 4;
            }
        }
        return time;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 보드를 초기화한다(벽 + 공백)
        board = new int[N + 2][N + 2];
        for (int i = 0; i < N + 2; i++) {
            for (int j = 0; j < N + 2; j++) {
                if (i == 0 || i == N + 1 || j == 0 || j == N + 1) {
                    board[i][j] = WALL;
                    continue;
                }
                board[i][j] = EMPTY;
            }
        }

        // 사과를 넣는다
        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = APPLE;
        }

        // 뱀 위치시킨다
        board[1][1] = SNAKE;

        // 움직임을 저장한다
        int L = Integer.parseInt(br.readLine());
        commands = new ArrayList<>(L);
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String movingRule = st.nextToken();
            commands.add(new Command(time, movingRule));
        }
        commands.add(new Command(210000000, "L"));
    }

    private static void initDxDy() {
        dx[0] = 0;
        dx[1] = 1;
        dx[2] = 0;
        dx[3] = -1;

        dy[0] = 1;
        dy[1] = 0;
        dy[2] = -1;
        dy[3] = 0;
    }

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int direction) {
            this.x = this.x + dx[direction];
            this.y = this.y + dy[direction];
        }

        public boolean is(int type) {
            return board[this.x][this.y] == type;
        }
    }

    static class Command {
        int time;
        String movingRule;

        public Command(int time, String movingRule) {
            this.time = time;
            this.movingRule = movingRule;
        }
    }
}
