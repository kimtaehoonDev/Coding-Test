package org.example.baekjoon.p7682;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

// 실패 -> 정답 참조 후 원인을 알았음(x가 정답이면 o는 1개 더 적어야 함. (xoxoxoxo.))
// 그러나 좋은 풀이는 아닌거같음. 반례찾기문제가 될 뿐이고 누가 더 엣지케이스를 생각했냐 싸움이라고 생각함(이런 문제도 있기야 하겠지만)
// 이 문제를 풀었다고 다른 문제에서 도움을 받을거라 생각하지 않음. 그래서 다른 풀이로 변경할예정
public class Main {

    static char[][] graphs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<String> answers = new ArrayList<>();
        while (!(line = br.readLine()).equals("end")) {
            answers.add(solve(line));
        }
        StringJoiner sj = new StringJoiner("\n");
        for (String answer : answers) {
            sj.add(answer);
        }
        System.out.println(sj.toString());
    }

    static int[] dx = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dy = new int[]{0, 1, 0, -1, 1, -1, 1, -1};

    static String solve(String line) {
        int oCnt = 0;
        int xCnt = 0;
        boolean xAnswer = false;
        boolean oAnswer = false;
        graphs = makeGraphs(line);
        for (char[] row : graphs) {
            for (char each : row) {
                if (each == 'O') {
                    oCnt++;
                } else if (each == 'X') {
                    xCnt++;
                }
            }

            // x의 개수는 O와 같거나, 1개 더 많다
            if (xCnt != oCnt && xCnt != oCnt + 1) {
                return "invalid";
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (graphs[i][j] == '.') {
                    continue;
                }
                boolean fillLine = dfs(i, j);
                System.out.println(i + " " + j + "Dptj " + fillLine);
                if (fillLine) {
                    if (graphs[i][j] == 'X') {
                        xAnswer = true;
                    } else {
                        oAnswer = true;
                    }
                }
            }
        }
        if (xAnswer && oAnswer) {
            return "invalid";
        }
        return "valid";
    }

    // i,j위치는 X아님 O.
    static boolean dfs(int i, int j) {
        // dfs
        List<int[]> stack = new ArrayList<>();
        for(int k = 0; k<8; k++) {
            stack.add(new int[]{i, j, k, 1}); // 위치 && 방향 && 연속횟수
        }
        char original = graphs[i][j];
        while (!stack.isEmpty()) {
            int[] removed = stack.remove(stack.size() - 1);
            if (removed[3] > 3) {
                return true;
            }

            int nx = removed[0] + dx[removed[2]];
            int ny = removed[1] + dy[removed[2]];
            if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) {
                continue;
            }
            if (original != graphs[nx][ny]) {
                continue;
            }
            stack.add(new int[]{nx, ny, removed[2], removed[3] + 1});
        }
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
