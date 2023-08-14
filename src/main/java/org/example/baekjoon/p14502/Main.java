package org.example.baekjoon.p14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 성공/풀이시간: 45분
// 중간에 queue.poll 처리하지 않하 살짝 헤맨걸 빼도 35분 가까이 소요된거같음. 헤매지도 않았고 바로 문제에 접근했는데
// 구현속도가 아직 딸리는건가, 이 문제가 이정도 걸릴수밖에 없는건가 잘 모르겠다.
// 구현속도를 더 빠르게 만들 필요가 있다는 생각이 듬

// 조합, bfs가 동시에 들어가는 문제
public class Main {
    public static int WALL = 1;
    public static int VIRUS = 2;

    public static int N;
    public static int M;
    public static int[][] graph;
    public static int answer;

    public static int[] dx = new int[] {1, 0, -1, 0};
    public static int[] dy = new int[] {0, 1, 0, -1};
    public static List<int[]> cases = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];

        List<Spot> viruses = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == VIRUS) {
                    viruses.add(new Spot(i,j));
                }
            }
        }

        // 3개를 조합으로 찾는다
        comb(N*M, 3, 0, 0, new int[3]);
//        for(int[] eachCase: cases) {
//            System.out.println(Arrays.toString(eachCase));
//        }
//        System.out.println(cases.size());
        for(int[] eachCase: cases) {
            Spot first = changeSpot(eachCase[0]);
            Spot second = changeSpot(eachCase[1]);
            Spot third = changeSpot(eachCase[2]);

            // 3개 다 graph가 0이어야함
            if (graph[first.x][first.y] != 0 || graph[second.x][second.y] != 0 || graph[third.x][third.y] != 0) {
                continue;
            }
            boolean[][] visited = new boolean[N][M];
            graph[first.x][first.y] = WALL;
            graph[second.x][second.y] = WALL;
            graph[third.x][third.y] = WALL;

            for(Spot virus: viruses) {
                if (visited[virus.x][virus.y]) {
                    continue;
                }
                bfs(virus, visited);
            }
            answer = Math.max(answer, calculate(visited));

            graph[first.x][first.y] = 0;
            graph[second.x][second.y] = 0;
            graph[third.x][third.y] = 0;
        }

        System.out.println(answer);
    }

    public static int calculate(boolean[][] visited) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && graph[i][j] == 0) { // 방문한적 없고 빈칸이면
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void bfs(Spot spot, boolean[][] visited) {
        Queue<Spot> queue = new LinkedList<>();

        visited[spot.x][spot.y] = true; // 방문처리
        queue.add(spot);
        while(!queue.isEmpty()) {
            spot = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = spot.x + dx[i];
                int ny = spot.y + dy[i];
                if(nx < 0 || ny < 0 || N<=nx || M<=ny) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (graph[nx][ny] == WALL) { // 벽을 만나면 더 진행하지 못함
                    continue;
                }
                visited[nx][ny] = true;
                queue.add(new Spot(nx, ny));
            }
        }
    }
    public static Spot changeSpot(int idx) {
        return new Spot((idx - 1) /M, (idx-1) % M);
    }


    public static void comb(int n, int r, int depth, int start, int[] answer) {
        if (r == depth) {
            cases.add(Arrays.copyOfRange(answer, 0, r));
            return;
        }
        for(int i=start + 1;i<=n;i++) {
            answer[depth] = i;//선택
            comb(n, r, depth+1, i, answer);
            answer[depth] = 0;
        }
    }

    public static class Spot {
        int x;
        int y;
        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
