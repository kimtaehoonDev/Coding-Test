package org.example.baekjoon.p13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// 성공 / 1시간 32분 / 구현
// 변하지 않는 부분(#)과 움직이는 부분(공)을 분리해서 생각하면 되는 문제
// HOLE 역시 변하지 않는 부분이라 board에 기록해두고 풀어도 됨. 하지만 나는 초반에 방향을 살짝 잘못 잡아서 HOLE 역시 공과 함꼐 분리함
// -> 이래서 Status의 moveBoard가 더 지저분해지긴 했지만 아무튼 풀었다~~
public class Main {

    static char HOLE = 'O';
    static char EMPTY = '.';
    static char RED = 'R';
    static char BLUE = 'B';
    static char WALL = '#';

    static Spot holeLocation;

    static char[][] board;

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        int height = Integer.parseInt(input1[0]);
        int width = Integer.parseInt(input1[1]);
        Spot blueLocation = null;
        Spot redLocation = null;
        board = new char[height][width];
        for (int i = 0; i < height; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < width; j++) {
                if (board[i][j] == HOLE) {
                    board[i][j] = EMPTY;
                    holeLocation = Spot.of(i, j);
                } else if (board[i][j] == RED) {
                    board[i][j] = EMPTY;
                    redLocation = Spot.of(i, j);
                } else if (board[i][j] == BLUE) {
                    board[i][j] = EMPTY;
                    blueLocation = Spot.of(i, j);
                }
            }
        }
//        for (char[] chars : board) {
//            System.out.println(Arrays.toString(chars));
//        }

        // 네 방향으로 기울이기
        int answer = -1;
        Queue<Status> queue = new LinkedList<>();
        queue.add(new Status(redLocation, blueLocation));
        while (!queue.isEmpty()) {
            Status now = queue.poll();
            if (now.repeatCnt >= 10) {
                // 이미 열번 다 움직임
                answer = -1;
                break;
            }
            // 기울인다.
            for (int dir = 0; dir < 4; dir++) {
                Status next = now.moveBoard(dir);
                // 상태체크
                if (next.flag == 2) {
                    continue;
                }
                if (next.flag == 1) {
                    // 이놈이 정답
                    answer = next.repeatCnt;
                    queue.clear();
                    break;
                }
                queue.add(next);
            }
        }
        System.out.println(answer);
    }

    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};

    static class Status {

        Spot red;
        Spot blue;
        int repeatCnt;
        int flag; // 0 : 정상, 1 : 빨간공만 아웃, 2 : 파란공이 아웃

        public Status(Spot red, Spot blue) {
            this.red = red;
            this.blue = blue;
            this.repeatCnt = 0;
            this.flag = 0;
        }

        public Status(Spot red, Spot blue, int repeatCnt, int flag) {
            this.red = red;
            this.blue = blue;
            this.repeatCnt = repeatCnt;
            this.flag = flag;
        }

        public Status moveBoard(int dir) {
            Spot originalRed = red;
            Spot originalBlue = blue;

//            red와 blue중 누구를 먼저 이동할지 정한다. 둘 다 같으면 blue부터 움직인다.
            if (dir == 0) { // 오른쪽으로 기울임
                if (red.y > blue.y) {
                    // red를 먼저 움직인다
                    this.red = moveBall(red, dir);
                    this.blue = moveBall(blue, dir);
                } else {
                    this.blue = moveBall(blue, dir);
                    this.red = moveBall(red, dir);
                }
            } else if (dir == 1) { // 왼쪽 기울임
                //
                if (red.y < blue.y) {
                    // red를 먼저 움직인다
                    this.red = moveBall(red, dir);
                    this.blue = moveBall(blue, dir);
                } else {
                    this.blue = moveBall(blue, dir);
                    this.red = moveBall(red, dir);
                }
            } else if (dir == 2) { // 아래로 기울임
                if (red.x > blue.x) {
                    // red를 먼저 움직인다
                    this.red = moveBall(red, dir);
                    this.blue = moveBall(blue, dir);
                } else {
                    this.blue = moveBall(blue, dir);
                    this.red = moveBall(red, dir);
                }
            } else { // 위로 기울임
                if (red.x < blue.x) {
                    // red를 먼저 움직인다
                    this.red = moveBall(red, dir);
                    this.blue = moveBall(blue, dir);
                } else {
                    this.blue = moveBall(blue, dir);
                    this.red = moveBall(red, dir);
                }
            }

            // red와 blue로 새로운 상태를 반환~
            Status result = this.makeNew();
//            System.out.println(dir + "방향으로 움직였을 때");
//            System.out.println("빨강은 " + originalRed + "-> " + red);
//            System.out.println("파랑은 " + originalBlue + "-> " + blue);
//            System.out.println("상태는" + result.flag);

            this.red = originalRed;
            this.blue = originalBlue;
            return result;
        }

        private Status makeNew() {
            if (blue == Spot.empty) {
                // 실패
                return new Status(red, blue, repeatCnt + 1, 2);
            }
            if (red == Spot.empty) {
                // 성공
                return new Status(red, blue, repeatCnt + 1, 1);
            }
            return new Status(red, blue, repeatCnt + 1, 0);
        }

        public Spot moveBall(Spot ball, int dir) {
            // dir 방향으로 이동한다. 가장 먼저 나오는 벽 또는 다른 ball 앞까지.
            Spot temp = ball;
            while (true) {
                int nx = temp.x + dx[dir];
                int ny = temp.y + dy[dir];
                if (board[nx][ny] == WALL ||
                    (nx == this.red.x && ny == this.red.y) ||
                    (nx == this.blue.x && ny == this.blue.y)) {
                    break;
                }

                // 구멍이라면
                if (nx == Main.holeLocation.x && ny == Main.holeLocation.y) {
                    return Spot.out();
                }
                temp = Spot.of(nx, ny);
            }
            return temp;
        }
    }

    static class Spot {

        static Spot[][] store = new Spot[11][11];
        static Spot empty = new Spot(-1, -1);

        static {
            for (int i = 1; i <= 10; i++) {
                for (int j = 1; j <= 10; j++) {
                    store[i][j] = new Spot(i, j);
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
            return store[x][y];
        }

        public static Spot out() {
            return empty;
        }

        @Override
        public String toString() {
            return "Spot{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }

}
