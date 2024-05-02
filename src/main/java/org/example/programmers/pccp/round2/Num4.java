package org.example.programmers.pccp.round2;

import java.util.PriorityQueue;
import java.util.Queue;

// 성공 / 답지 참조 후 성공 / 48분
// 아런 류의 문제를 처음 접해본듯. 상황을 나눠서 Visited 배열에 관리하면 되는구나
class Num4 {

    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    public int solution(int N, int M, int[][] hole) {
        int answer = -1;

        boolean[][] graph = new boolean[N][M];
        for(int[] eachHole : hole) {
            graph[eachHole[0] - 1][eachHole[1] - 1] = true; // 장애물 O
        }
        // for(boolean[] line : graph) {
        //     System.out.println(Arrays.toString(line));
        // }

        boolean[][][] visited = new boolean[N][M][2];
        Queue<Node> heap = new PriorityQueue<>((n1, n2) -> {
            if (n1.moving != n2.moving) {
                return n1.moving - n2.moving; // 무빙이 적은게 우선순위
            }

            // n1.used, n2.used -> 0인게(작은게) 우선순위
            return n1.used - n2.used;
        });

        heap.offer(new Node(0,0,0,0)); // 0,0, 거리이동 0, 사용여부 0(False)
        while(!heap.isEmpty()) {
            Node now = heap.poll();
            if (now.x == N-1 && now.y == M-1) {
                answer = now.moving;
                break; // 종료조건
            }
            for(int d = 0; d<4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (visited[nx][ny][now.used]) {
                    continue;
                }

                if (!graph[nx][ny]) { // 벽이 아니라면 한칸 이동시킨다
                    heap.offer(new Node(nx, ny, now.moving + 1, now.used));
                    visited[nx][ny][now.used] = true;
                }

                if (now.used == 0) { // 아직 신발 안씀
                    nx += dx[d];
                    ny += dy[d];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                        continue;
                    }
                    if (visited[nx][ny][1]) {
                        continue;
                    }
                    if (graph[nx][ny]) { // 신발로 점프뛴 곳에 함정이 있으면 안됨
                        continue;
                    }
                    heap.offer(new Node(nx, ny, now.moving + 1, 1));
                    visited[nx][ny][1] = true;
                }

            }
        }

        return answer;
    }

    static class Node {
        int x;
        int y;
        int moving;
        int used; // 0 or 1

        public Node(int x, int y, int moving, int used) {
            this.x = x;
            this.y = y;
            this.moving = moving;
            this.used = used;
        }
    }

 }