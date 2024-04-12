package org.example.baekjoon.p17142;

import java.io.*;
import java.util.*;

// 55분 / 성공 / 구현
// 문제에 반례가 친절하지 않았다면 분명 못풀었을듯..
// 초기 상태가 모두 바이러스로 가득찬 상태를 고려하지 않음
public class Main {

    static int INF = 100000000;

    static int N;
    static int M;
    static int[][] graph;
    static boolean[][] visitsOriginal;
    static List<int[]> viruses = new ArrayList<>();
    static List<int[]> cases = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]); // 10 개
        graph = new int[N][N];
        visitsOriginal = new boolean[N][N];
        int cnt = 0;
        for(int i =0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j<N; j++) {
                int x = Integer.parseInt(st.nextToken());
                graph[i][j] = x;
                if (x == 1) { // 벽
                    visitsOriginal[i][j] = true;
                    cnt++;
                }else if (x == 2) {
                    // 바이러스 생성
                    viruses.add(new int[] {i, j}); // java 8에서 가능한지?
                    cnt++;
                }
            }
        }

//        System.out.println("cnt is " + cnt);
        comb(viruses.size(), M, 0, 0, new int[M]);
        int answer = INF;
        for(int[] selected : cases) {
            int time = bfs(selected, cnt);
            if (time == INF) {
                continue;
            }
            answer = Math.min(answer, time);
        }
        if (answer == INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    // cnt : 벽, 바이러스 개수
    static int bfs(int[] selected, int cnt) {
        if (cnt == N * N) {
            return 0;
        }
        boolean[][] visited = new boolean[visitsOriginal.length][visitsOriginal[0].length];
        for(int i = 0; i<visitsOriginal.length; i++) {
            for(int j = 0; j< visitsOriginal[0].length; j++) {
                visited[i][j] = visitsOriginal[i][j];
            }
        }

        Queue<Node> heap = new LinkedList<>();
        for(int virusIdx : selected) {
            int[] virus = viruses.get(virusIdx);
            visited[virus[0]][virus[1]] = true;
            heap.add(new Node(virus[0], virus[1], 0));
        }

        while(!heap.isEmpty()) {
            Node now = heap.poll();
            // 4방향 이동
            for(int i = 0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (visited[nx][ny]) {
                    continue;
                }
                if (graph[nx][ny] == 1) { // 벽
                    continue;
                }

                // 빈공간 또는 비활성 바이러스
                visited[nx][ny] = true;
                heap.offer(new Node(nx, ny, now.time + 1));
                if (graph[nx][ny] == 0) {
                    cnt++;
                }

                // 종료 조건 확인
                if (cnt == N * N) {
                    return now.time + 1;
                }
            }
        }
        return INF;
    }

    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    static class Node {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    // 순열, 조합, 비트마스킹으로?
    static void comb(int n, int r, int depth, int start, int[] store) {
        // 종료조건
        if (r == depth) {
            cases.add(Arrays.copyOfRange(store, 0, store.length));
            return;
        }

        for(int i = start; i<n; i++) {
            store[depth] = i;
            comb(n, r, depth + 1, i + 1, store);
        }
    }

}
