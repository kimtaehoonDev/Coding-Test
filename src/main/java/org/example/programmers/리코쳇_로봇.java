package org.example.programmers;

import java.util.LinkedList;
import java.util.Queue;

// 정말 이상하다.
// 내 체감상 기존 풀이와 전혀 다를 거 없는 풀이인데 정답 코드가 되어버렸다
// 기존 풀이가 어딘가에서 꼬인 거 같은데 찾기는 어려울 것 같다.
// 기존 풀이는 주석으로 남겨두겠다.


public class 리코쳇_로봇 {
    static char[][] board;
    static int[][] visited;

    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    public int solution(String[] boardInput) {
        board = new char[boardInput.length][boardInput[0].length()];
        visited = new int[boardInput.length][boardInput[0].length()];

        // visited, board 초기화 & start 찾기
        Node start = null;
        for(int i=0; i<boardInput.length; i++) {
            board[i] = boardInput[i].toCharArray();
            for(int j=0; j<board[i].length; j++) {
                visited[i][j] = -1;
                if (board[i][j] == 'R') {
                    start = new Node(i,j,0);
                }
            }
        }

        return bfs(start);
    }

    public int bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        visited[start.x][start.y] = start.count;

        while(!queue.isEmpty()) {
            start = queue.poll();
            for(int i=0; i<4; i++) {
                Node arrival = move(i, start);

                // 방문한 적 있으면 (이미 더 짧은 루트로 방문 가능하다면)
                if (visited[arrival.x][arrival.y] != -1) {
                    continue;
                }

                queue.add(arrival);
                visited[arrival.x][arrival.y] = arrival.count;

                if (board[arrival.x][arrival.y] == 'G') {
                    return arrival.count;
                }
            }
        }

        // 도착하지 못하는 경우
        return -1;
    }

    public Node move(int direction, Node start) {
        int originalX = start.x;
        int originalY = start.y;

        while(true) {
            int nx = originalX + dx[direction];
            int ny = originalY + dy[direction];

            if (nx < 0 || ny < 0 || board.length <= nx || board[0].length <= ny) {
                return new Node(originalX, originalY, start.count + 1);
            }
            if (board[nx][ny] == 'D') {
                return new Node(originalX, originalY, start.count + 1);
            }

            originalX = nx;
            originalY = ny;
        }
    }

    static class Node {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}


// 기존 풀이


//public class 리코쳇_로봇 {
//
//    class Solution {
//        static final char ROBOT = 'R';
//        static final char EMPTY = '.';
//        static final char WALL = 'D';
//        static final char GOAL = 'G';
//
////     static int[] dx = new int[] {1,0,-1,0};
////     static int[] dy = new int[] {0,1,0,-1};
//
//        static int[] dx = new int[] {0,0,1,-1};
//        static int[] dy = new int[] {1,-1,0,0};
//
//
//        static char[][] boardAry;
//        static int[][] visited;
//
//
//        public int solution(String[] board) {
//            boardAry = new char[board.length][board[0].length()];
//
//            // ROBOT 위치를 찾는다
//            int x = -1;
//            int y = -1;
//
//            // board 초기화 & 로봇 위치 찾기
//            for(int i=0; i<boardAry.length;i++) {
//                char[] line = board[i].toCharArray();
//                for(int j=0; j<boardAry[0].length;j++) {
//                    boardAry[i][j] = line[j];
//
//                    if (boardAry[i][j] == ROBOT) {
//                        x = i;
//                        y = j;
//                        break;
//                    }
//                }
//            }
//
//            // visited -1로 초기화
//            visited = new int[boardAry.length][boardAry[0].length];
//            for(int i=0; i<boardAry.length; i++) {
//                for(int j=0; j<boardAry[0].length; j++) {
//                    visited[i][j] = -1;
//                }
//            }
//
//
//            return bfs(new Node(x, y));
//        }
//
//        int bfs(Node start) {
//            Queue<Node> queue = new LinkedList<>();
//            queue.add(start);
//            visited[start.x][start.y] = 0; // 0칸이 소요됨
//
//            if (boardAry[start.x][start.y] == GOAL) {
//                return 0;
//            }
//
//            while(!queue.isEmpty()) {
////             for(int i=0; i<board.length; i++) {
////                 System.out.println(Arrays.toString(visited[i]));
////             }
////             System.out.println("==");
//
//                start = queue.poll();
//                // 4방향으로 이동
//                for(int i=0; i<4; i++) {
//                    // start -> arrive로 이동한다
//                    Node arrive = move(i, start);
//
//
//                    // 방문한 적 있다면 무시
//                    if (visited[arrive.x][arrive.y] != -1) {
//                        continue;
//                    }
//
//                    queue.add(arrive);
//                    visited[arrive.x][arrive.y] = visited[start.x][start.y] + 1;
//
//                    if (boardAry[arrive.x][arrive.y] == GOAL) {
//                        return visited[arrive.x][arrive.y];
//                    }
//
//                }
//            }
//
//            // 도착할 수 없서
//            return -1;
//        }
//
//        // direction 방향으로 depart를 이동시킨다
//        Node move(int direction, Node depart) {
//            int x = depart.x;
//            int y = depart.y;
//            while(true) {
//                int nx = x + dx[direction];
//                int ny = y + dy[direction];
//
//                if (nx < 0 || ny < 0 || boardAry.length <= nx || boardAry[0].length <=ny) {
//                    return new Node(x, y);
//                }
//                if (boardAry[nx][ny] == WALL) {
//                    return new Node(x, y);
//                }
//                x = nx;
//                y = ny;
//            }
//        }
//
//        static class Node {
//            int x;
//            int y;
//
//            public Node(int x, int y) {
//                this.x = x;
//                this.y = y;
//            }
//        }
//    }
//}