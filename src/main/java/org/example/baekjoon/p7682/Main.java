package org.example.baekjoon.p7682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

// 성공 / 1시간 20분
// dfs를 사용해서 가능한 모든 경우를 찾는다 -> 해당 값들을 Cases에 넣는다
// 입력받은 값이 가능한지 검사한다.
public class Main {

    static Set<String> cases = new HashSet<>();

    public static void main(String[] args) throws IOException {
        init();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<String> answers = new ArrayList<>();
        while (!(line = br.readLine()).equals("end")) {
            if (cases.contains(line)) {
                answers.add("valid");
            } else {
                answers.add("invalid");
            }
        }
        StringJoiner sj = new StringJoiner("\n");
        for (String answer : answers) {
            sj.add(answer);
        }
        System.out.println(sj.toString());
    }

    static int[] dx = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
    static int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};

    static void init() {
        char[][] graph = new char[3][3];
        for (char[] chars : graph) {
            Arrays.fill(chars, '.');
        }
        boolean[] visited = new boolean[9];
        dfs(graph, visited, 0);
    }

    static void dfs(char[][] graph, boolean[] visited, int seq) {
        char rock; // 말
        for (int i = 0; i < 9; i++) {
            if (visited[i]) {
                continue;
            }
            if (seq % 2 == 0) {
                rock = 'X';
            } else {
                rock = 'O';
            }
            // i에 놓는다
            visited[i] = true;
            graph[i / 3][i % 3] = rock;

            // 정답이 만들어졌으면 그만한다
            boolean isTicTacTo = checkTicTacTo(graph);
            if (isTicTacTo || isFull(visited)) {
                String line = convertLine(graph);
//                System.out.println("시작" + line);
                cases.add(line);

                visited[i] = false;
                graph[i / 3][i % 3] = '.';
                continue;
            }

            // full
            dfs(graph, visited, seq + 1);
            visited[i] = false;
            graph[i / 3][i % 3] = '.';
        }


    }

    private static boolean isFull(boolean[] visited) {
        int visitedCnt = 0;
        for (boolean b : visited) {
            if (b) {
                visitedCnt++;
            }
        }
        return visitedCnt == 9;
    }

    static boolean checkTicTacTo(char[][] graph) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (graph[i][j] == '.') {
                    continue;
                }
                for(int k = 0; k<4; k++) {
                    // i,j에서 k방향으로 이동시켜봐라
                    int x = i;
                    int y = j;
                    int connectsCnt = 0;
                    while (graph[i][j] == graph[x][y]) {
                        connectsCnt++;
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) {
                            break;
                        }
                        x = nx;
                        y = ny;
                    }

                    if (connectsCnt >= 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static String convertLine(char[][] graph) {
        StringBuilder sb = new StringBuilder();
        for (int a = 0; a < 3; a++) {
            for (int j = 0; j < 3; j++) {
                sb.append(graph[a][j]);
            }
        }
        return sb.toString();
    }

    static boolean solve(String line) {
        char[][] graph = makeGraphs(line);

        return false;
    }

    private static char[][] makeGraphs(String line) {
        char[] temp = line.toCharArray();
        char[][] inputs = new char[3][3];
        inputs[0][0] = temp[0];
        inputs[0][1] = temp[1];
        inputs[0][2] = temp[2];
        inputs[1][0] = temp[3];
        inputs[1][1] = temp[4];
        inputs[1][2] = temp[5];
        inputs[2][0] = temp[6];
        inputs[2][1] = temp[7];
        inputs[2][2] = temp[8];
        return inputs;
    }
}
