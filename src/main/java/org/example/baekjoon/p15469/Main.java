package org.example.baekjoon.p15469;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static List<String[]> answers = new ArrayList<>();
    public static boolean[] visited;

    public static int N;
    public static int M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        visited = new boolean[N+1];

        solve(N, M, 0, new String[M]);

        StringJoiner sj = new StringJoiner("\n");
        for (String[] answer : answers) {
            sj.add(String.join(" ", answer));
        }
        System.out.println(sj);
    }

    // 1부터 n까지 중 r개를 뽑는다
    public static void solve(int n, int r, int depth, String[] output) {
        if (depth == r) {
            answers.add(Arrays.copyOfRange(output, 0, output.length));
            return;
        }

        for(int i=1; i<=n; i++) {
            if (!visited[i]) {
                // 숫자 i를 뽑는다
                visited[i] = true;
                output[depth] = String.valueOf(i);
                solve(n, r, depth + 1, output);
                // 자리를 비운다
                visited[i] = false;
                output[depth] = "";
            }
        }
    }
}


// 조합
//public class Main {
//    public static List<boolean[]> answers = new ArrayList<>();
//    public static int N;
//    public static int M;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        M = sc.nextInt();
//
//        solve(1,0,new boolean[N+1]);
//        for (boolean[] answer : answers) {
//            int idx = 0;
//            for (boolean b : answer) {
//                System.out.print(" " + b); // 1부터 N 인덱스까지
//            }
//
//            System.out.println("");
//        }
//    }
//
//    public static void solve(int start, int depth, boolean[] answer) {
//        if (start > N && depth < M) {
//            return;
//        }
//        if (depth == M) {
//            answers.add(Arrays.copyOfRange(answer, 0, answer.length));
//        }
//        for(int i=start; i<=N; i++) {
//            answer[i] = true;
//            solve(i+1, depth + 1, answer);
//            answer[i] = false;
//        }
//    }
//}