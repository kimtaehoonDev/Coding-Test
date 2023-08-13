package org.example.baekjoon.p14500;

import java.util.*;
import java.io.*;

// 1회 실패 후 성공
// 풀이시간: 1번째 풀이 40분, 2번째 풀이 15분

// 초반 문제를 풀 때 dfs로 풀수 있겠다고 생각함(아님)
// 그러나 예시 케이스를 돌려보다가 풀리지 않는 문제 하나 발견.
// 콘솔을 찍어보니 ㅗ 모양을 처리하지 못하는 걸 발견할 수 있었음
// depth가 2일 때 예외 케이스를 만들어 처리하려 했는데, 구현에서 실수가 있어 틀림

// 새롭게 다시 생각해보니, 하나의 점에서 ㅗ ㅓ ㅏ ㅜ 총 4가지 모양밖에 나오지 않아
// dfs랑 별개로 네 가지 케이스를 직접 연산함 -> edgeCaseDfs 메서드

public class Main{
    public static int answer = -1;
    public static int[][] graph;
    public static boolean[][] visited;

    public static int[] dx = new int[] {1,0,-1,0};
    public static int[] dy = new int[] {0,1,0,-1};

    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                dfs(i,j,0,0);
                edgeCaseDfs(i,j);
            }
        }

        System.out.println(answer);
    }

    public static void edgeCaseDfs(int x, int y) {
        for(int i=0; i<4; i++) {
            int sum = 0;

            int nx = x + 2 * dx[i];
            int ny = y + 2 * dy[i];
            if (nx <0 || ny < 0 || graph.length<= nx || graph[0].length <= ny) {
                continue;
            }

            for(int z=0;z<3;z++) {
                sum += graph[x + z * dx[i]][y + z * dy[i]];
            }

            nx = x + dx[i];
            ny = y + dy[i];
            for(int j=0; j<2; j++) {
                int nnx = nx + dx[(i + 2 * j + 1)%4];
                int nny = ny + dy[(i + 2 * j + 1) %4];
                if (nnx <0 || nny < 0 || graph.length<= nnx || graph[0].length <= nny) {
                    continue;
                }
                answer = Math.max(sum + graph[nnx][nny], answer);

            }
        }
    }

    public static void dfs(int x, int y, int sum, int depth) {
        if (depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }

        visited[x][y] = true;
        sum += graph[x][y];
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx <0 || ny < 0 || graph.length<= nx || graph[0].length <= ny) {
                continue;
            }
            if (!visited[nx][ny]) {
                dfs(nx, ny, sum, depth + 1);
            }
        }
        visited[x][y] = false;
    }
}