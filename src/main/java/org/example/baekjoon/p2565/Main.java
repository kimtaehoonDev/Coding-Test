package org.example.baekjoon.p2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 실패
// 아이디어 자체가 틀림(가장 교차점이 많은 순서대로 제거)
// https://www.acmicpc.net/board/view/84972 해당 부분에 반례가 있음. 다른 아이디어를 떠올려서 풀어보기
public class Main {
    static int N;
    static int[] dp;
    static boolean[][] graph;
    static List<Line> lines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N]; // dp[i] == i번째 Line의 교차점 개수
        graph = new boolean[N][N];

        // lines를 입력받아, start 기준 ASC 정렬
        lines = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new Line(start, end));
        }
        lines.sort((l1, l2) -> l1.start - l2.start);
//        for (Line line : lines) {
//            System.out.println(line.start + "/" + line.end);
//        }

        // 각 line들간에 관계를 만든다
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (cross(i, j)) {
                    // 두 선을 연결한다
                    graph[i][j] = true;
                    graph[j][i] = true;
                    dp[i]++;
                    dp[j]++;
                }
            }
        }

//        System.out.println(Arrays.toString(dp));
//        System.out.println("==");
//        for (boolean[] booleans : graph) {
//            System.out.println(Arrays.toString(booleans));
//        }

        int answer = 0;
        while (true) {
            boolean canExit = true;
            for (int i = 0; i < N; i++) {
                if (dp[i] != 0) {
                    canExit = false;
                    break;
                }
            }
            if (canExit) {
                break;
            }
            // maxIdx를 찾는다
            int maxIdx = findMaxIdx();
            dp[maxIdx] = 0;
            boolean[] connects = graph[maxIdx];
            for (int i = 0; i < connects.length; i++) {
                if (connects[i]) {
                    graph[maxIdx][i] = false;
                    graph[i][maxIdx] = false;
                    dp[i]--;
                }
            }
            answer++;
//            System.out.println("==");
//            System.out.println(Arrays.toString(dp));
//            for (boolean[] booleans : graph) {
//                System.out.println(Arrays.toString(booleans));
//            }
//            System.out.println(answer);
        }
        System.out.println(answer);
    }

    private static int findMaxIdx() {
        int max = 0;
        int maxIdx = -1;
        for (int i = 0; i < N; i++) {
            if (max < dp[i]) {
                max = dp[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    // 작았던 애가 계속 작거나, 컸던 애가 계속 크면 교차 안함
    static boolean cross(int i, int j) {
        Line l1 = lines.get(i);
        Line l2 = lines.get(j);
        boolean s1 = l1.start < l2.start;
        boolean s2 = l1.end < l2.end;
        return s1 != s2;
    }

    static class Line {
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
