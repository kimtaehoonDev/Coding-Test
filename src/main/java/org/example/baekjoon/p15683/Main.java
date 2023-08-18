package org.example.baekjoon.p15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 성공/41분
// 이런 구현 문제는 풀 때 하나씩 놓치는 부분이 생겨 시간을 잡아먹는거같음.
// 해당 부분을 줄여야 시간단축이 가능할것으로 보임
// 특히 복사 붙여넣기 하다가 실수를 많이 하는거 같음.
// 이 문제에서는 정말 별 거 아니지만 int dy = x + dx[i] 로 적어 문제 해결에 오래걸림
public class Main {
    static final int EMPTY = 0;
    static final int WALL = 6;

    static final int[] dx = new int[] {1, 0, -1, 0}; // 남동북서
    static final int[] dy = new int[] {0, 1, 0, -1};

    static int answer = 100000000;
    static int N, M;
    static int[][] graph;
    static List<Node> cctvs = new ArrayList<>();
    static int[] now; // 각 cctv의 idx에 cctv가 바라보는 방향을 기록한다
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] >= 1 && graph[i][j] <= 5) {
                    cctvs.add(new Node(i, j));
                }
            }
        }
        now = new int[cctvs.size()];


//        // log
//        System.out.println("CCTV 개수: " +cctvs.size());
//        for(int i=0; i<N; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }

        // 각 CCTV마다 4개의 상황을 가질 수 있다
        for(int i=0; i<(int) Math.pow(4, cctvs.size());i++) {
            int temp = i;
            int tempIdx = 0;
            while(tempIdx < cctvs.size()) {
                now[tempIdx++] = temp % 4;
                temp /= 4;
            }
//            System.out.println("경우의수" + Arrays.toString(now)); // 경우의 수를 보여준다
            boolean[][] visited = new boolean[N][M];
            solve(visited);
        }

        System.out.println(answer);

    }

    static void solve(boolean[][] visited) {
        // CCTV의 각 지점에서 쭉 본다
        int idx = 0;
        for(Node cctv: cctvs) {
            int type = graph[cctv.x][cctv.y]; // CCTV의 타입. 1~5
            useCctv(cctv.x, cctv.y, type, idx++, visited);
        }

        // visited하지 않은 0 공간 찾기
        int cantSeeCnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if (graph[i][j] == 0 && !visited[i][j]) {
                    cantSeeCnt++;
                }
            }
        }
        answer = Math.min(cantSeeCnt, answer);
    }

    static void useCctv(int x, int y, int type, int idx, boolean[][] visited) {
        int direction = now[idx]; // idx CCTV가 바라보는 방향
//        System.out.println("방향은 " + direction);
        if (type == 1) {
            // 한쪽 방향만 바라본다
            show(direction, x, y, visited);
        } else if (type == 2) {
            show(direction, x, y, visited);
            show((direction + 2) % 4, x, y, visited);
        } else if (type == 3) {
            show(direction, x, y, visited);
            show((direction + 3) % 4, x, y, visited);
        } else if (type == 4) {
            show(direction, x, y, visited);
            show((direction + 1) % 4, x, y, visited);
            show((direction + 3) % 4, x, y, visited);
        } else if (type == 5) {
            show(direction, x, y, visited);
            show((direction + 1) % 4, x, y, visited);
            show((direction + 2) % 4, x, y, visited);
            show((direction + 3) % 4, x, y, visited);
        } else {
            throw new RuntimeException("여기는 나올 수 없음");
        }
    }

    static void show(int direction, int x, int y, boolean[][] visited) {
        while(true) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
                return;
            }

            // 벽일 때는 CCTV가 더이상 보지 않는다
            if (graph[nx][ny] == WALL) {
                return;
            }
            // CCTV간에는 통과가 가능해서 조건을 검사하지 않는다
            visited[nx][ny] = true;
            x = nx;
            y = ny;
        }
    }

    static class Node{
        int x;
        int y;
        public Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
