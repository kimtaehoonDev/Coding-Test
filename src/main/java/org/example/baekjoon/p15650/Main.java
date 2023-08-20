package org.example.baekjoon.p15650;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<int[]> answers = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        comb(N, M, 0, 0, new int[M]);

        StringBuilder sb = new StringBuilder();
        for(int[] answer: answers) {
            int idx = 0;
            for(int each: answer) {
                sb.append(each);

                idx++;
                if (idx == M) {
                    sb.append("\n");
                } else {
                    sb.append(" ");
                }
            }

        }
        System.out.println(sb);
    }

    public static void comb(int n, int r, int depth, int start, int[] output) {
        if (r == depth) {
            answers.add(Arrays.copyOfRange(output, 0, output.length));
            return;
        }

        for(int i = start + 1; i<n + 1;i++) {
            output[depth] = i;
            comb(n, r, depth + 1, i, output);
        }
    }
}
