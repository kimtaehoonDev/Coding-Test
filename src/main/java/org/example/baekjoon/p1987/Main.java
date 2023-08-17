package org.example.baekjoon.p1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1회 실패 -> 성공
// 풀이시간: 1회: 32분,
//         2회: + 3분
// 실패 사유: 모든 종료 조건에서 최대 길이를 계산하지 않고 누락함.
//          그래프의 범위를 벗어나거나, 이미 방문한 위치에 도달했을 때에도 해당 경우를 종료하고 동시에 연결된 최대 길이를 계산해야 했는데 생각하지 못함

// 시간이 너무 많이 걸려 다른 사람들의 풀이를 살펴봄.
// 크게 n가지 차이가 있음
// 1. 중복 알파벳 찾는 로직
//    나 같은 경우 HashSet을 사용해 중복된 알파벳을 체크하는데, boolean[26] 배열을 만들어 각 알파벳들이 나왔는지 안나왔는지 확인하는 방법이 존재함
// 2. visited 배열이 필요없음.
//    이미 visited한 노드를 확인하면 이미 중복된 알파벳이 나올 수밖에 없음. 중복으로 검사하는 불필요한 로직임
// 3. 최대 길이를 계산할 때 누적합을 사용함
//    alpha 배열을 순회할 필요 없이 누적합으로 간단하게 최대길이를 연산
// 4. DFS가 실행되었다는 건 now 노드까지는 경로에 포함된다는 의미. DFS가 실행되면 다른 조건 확인하지 않고 최대경로를 계산해도 됨

public class Main {
    static int answer = 0;
    static int[] dx = new int[] {1, 0, -1, 0};
    static int[] dy = new int[] {0, 1, 0, -1};
    static int R, C;
    static char[][] graph;
    static boolean[] alpha = new boolean[26]; // 알파벳 26개 맞나?


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                graph[i][j] = temp[j];
            }
        }


        // 첫 방문처리
        alpha[graph[0][0] - 'A'] = true;
        dfs(new Node(0, 0), 1);

        System.out.println(answer);
    }

    public static void dfs(Node now, int cnt) {
        for (int d = 0; d < 4; d++) {
            int nx = now.x + dx[d];
            int ny = now.y + dy[d];
            if (nx < 0 || ny < 0 || R <= nx || C <= ny) {
                continue;
            }

            // 같은 문자열이 나와서 해당 방향으로 더 진행 불가능
            if (alpha[graph[nx][ny] - 'A']) {
                continue;
            }

            Node next = new Node(nx, ny);
            alpha[graph[nx][ny] - 'A'] = true;
            dfs(next, cnt + 1);
            alpha[graph[nx][ny] - 'A'] = false;
        }
        answer = Math.max(answer, cnt);
        return;
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
}
