package org.example.baekjoon.p17281;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static int answer = 0;

    static int[][] graph;
    static int N;

    public static void main(String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][9];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // for(int[] each: graph) {
        // System.out.println(Arrays.toString(each));
        // }

        List<List<Integer>> cases = makeOrders();
        for(List<Integer> each : cases) {
            each.add(3, 0);
            int score = simulate(each);
            answer = Math.max(score, answer);
        }
//
        System.out.println(answer);
    }

    private static List<List<Integer>> makeOrders() {
        List<List<Integer>> result = new ArrayList<>();
        // 1부터 9까지 9개 골라라
        perm(8, 0, result, new int[8], new boolean[8]);
        return result;
    }

    private static void perm(int r, int depth, List<List<Integer>> result, int[] temp, boolean[] visited) {
        if (r == depth) {
//            System.out.println(temp);
            List<Integer> eachCase = new ArrayList<>();
            for (int num : temp) {
                eachCase.add(num);
            }
            result.add(eachCase);
            return;
        }
        for(int i=0; i<8; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            temp[depth] = (i + 1);
            perm(r, depth + 1, result, temp, visited);
            visited[i] = false;
        }
    }

    // orders 기준으로 이닝이 끝날 때 점수를 계산한다
    static int simulate(List<Integer> orders) {
        int score = 0;
        boolean[] bases = new boolean[3];


        int outCount = 0;
        int now = 0; // hit first -> 0's
        for(int i=0; i<N; i++) { // N회 이닝을 진행한다
            while(outCount != 3) {
                Integer playerNum = orders.get(now);
                now = (now + 1) % 9;
                int hit = graph[i][playerNum];
//                System.out.println(playerNum + "선수 hit " + hit);
                if (hit == 0) {
                    outCount++;
                    continue;
                }
                if (hit == 1) {
                    if (bases[2]) {
                        score++;
                    }
                    bases[2] = bases[1];
                    bases[1] = bases[0];
                    bases[0] = true;
                } else if (hit == 2) {
                    for(int j=1; j<3; j++) {
                        if (bases[j]) {
                            score++;
                        }
                    }
                    bases[2] = bases[0];
                    bases[1] = true;
                    bases[0] = false;
                } else if (hit == 3) {
                    for(int j=0; j<3; j++) {
                        if (bases[j]) {
                            score++;
                        }
                    }
                    bases[2] = true;
                    bases[1] = false;
                    bases[0] = false;
                } else {
                    for(int j=0; j<3; j++) {
                        if (bases[j]) {
                            score++;
                        }
                    }
                    score++;
                    bases[2] = false;
                    bases[1] = false;
                    bases[0] = false;
                }
            }
            // end ining
            outCount = 0;
            for(int j=0; j<3; j++) {
                bases[j] = false;
            }

        }

        return score;
    }
}