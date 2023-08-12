package org.example.baekjoon.p14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 성공/완탐/풀이 시간 25분
public class Main {
    public static List<boolean[]> answers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 0부터 N-1까지 애들을 두 팀으로 나눈다
        comb(N, N/2, 0, 0, new boolean[N]);


        int gap = 1000000000;
        for(boolean[] answer: answers) {
            int teamAStat = 0;
            int teamBStat = 0;
            for(int i=0; i<answer.length; i++) {
                for(int j=0; j<answer.length; j++) {
                    // i랑 j가 같은 팀이면 스스슥
                    if (i == j) {
                        continue;
                    }

                    if (answer[i] && answer[j]) {
                        teamAStat = teamAStat + graph[i][j];
                    } else if (!answer[i] && !answer[j]) {
                        teamBStat = teamBStat + graph[i][j];
                    }
                }
                // ateam, bteam 점수 차 계산
            }
            gap = Math.min(gap, Math.abs(teamAStat - teamBStat));
            // 차이를 계산
        }
        System.out.println(gap);

    }

    public static void comb(int n, int r, int depth, int start, boolean[] answer) {
        if (depth == r) {
            answers.add(Arrays.copyOfRange(answer, 0, answer.length));
            return;
        }
        if (start >= n) {
            return;
        }

        for(int i=start; i<n;i++) {
            answer[i] = true;
            comb(n,r,depth+1, i+1, answer);
            answer[i] = false;
        }
    }
}
