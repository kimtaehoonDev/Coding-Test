package org.example.baekjoon.p9020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean[] visited = findVisitsNum();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] inputs = new int[T];

        for (int i = 0; i < T; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
        }
        // primes: 소수가 들어있음
        // inputs: 입력값들이 들어있음
        int[][] answers = new int[T][2];
        int idx = 0;
        for (int input : inputs) {
            int n1 = input / 2;
            int small = n1;
            int big = n1;

            while (true) {
                if (!visited[small] && !visited[big]) {
                    answers[idx][0] = small;
                    answers[idx][1] = big;
                    idx++;
                    break;
                }
                small -= 1;
                big += 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int[] answer : answers) {
            sb.append(answer[0] + " " + answer[1] + "\n");
        }
        System.out.println(sb);
    }

    private static boolean[] findVisitsNum() {
        boolean[] visited = new boolean[10001];
        visited[1] = true;

        for (int i = 2; i <= 10000; i++) {
            if (!visited[i]) {
                for (int j = i; j <= 10000; j = j + i) {
                    if (j == i) {
                        continue;
                    }
                    visited[j] = true;
                }
            }
        }
        return visited;
    }

}


