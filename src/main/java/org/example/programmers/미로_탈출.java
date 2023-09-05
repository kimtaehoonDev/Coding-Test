package org.example.programmers;

import java.util.LinkedList;
import java.util.Queue;

// 성공, 풀이시간 30분
// https://school.programmers.co.kr/learn/courses/30/lessons/159993

class 미로_탈출 {
    static char[][] maps;
    static Node start;
    static Node end;
    static Node lever;

    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    public int solution(String[] mapsInput) {
        maps = new char[mapsInput.length][mapsInput[0].length()];

        for(int i=0; i<mapsInput.length;i++) {
            maps[i] = mapsInput[i].toCharArray();
            for(int j=0; j<maps[0].length;j++) {
                if (maps[i][j] == 'S') {
                    start = new Node(i,j,0);
                } else if (maps[i][j] == 'E') {
                    end = new Node(i, j, 0);
                } else if (maps[i][j] == 'L') {
                    lever = new Node(i, j, 0);
                }
            }
        }

        // 지도를 출력
        // for(int i=0; i<maps.length;i++) {
        //     System.out.println(Arrays.toString(maps[i]));
        // }
        // System.out.println(start);
        // System.out.println(end);
        // System.out.println(lever);

        // start에서 Lever를 찾는 데 몇 초 걸리니?
        int times = bfs(start, 'L', new boolean[maps.length][maps[0].length]);
        if (times == -1) {
            return -1;
        }
        // lever에서 end 찾는데 얼마나 걸리나
        int tempTimes = bfs(lever, 'E', new boolean[maps.length][maps[0].length]);
        if (tempTimes == -1) {
            return -1;
        }
        times += tempTimes;
        return times;
    }

    public int bfs(Node node, char target, boolean[][] visited) {
        Queue<Node> queue = new LinkedList<>();

        visited[node.x][node.y] = true;
        queue.add(node);

        while(!queue.isEmpty()) {
            node = queue.poll();



            for(int d=0; d<4; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                if (nx < 0 || ny < 0 || visited.length <= nx || visited[0].length <= ny) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                // 벽이면 더 못감
                if (maps[nx][ny] == 'X') {
                    continue;
                }

                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, node.count + 1));

                // 타겟에 도착하면 타겟까지 걸린 시간을 반환한다
                if (maps[nx][ny] == target) {
                    return node.count + 1;
                }
            }
        }

        return -1;
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

        public String toString() {
            return "(" + x + "," + y+") - 거리 : " + count;
        }
    }
}