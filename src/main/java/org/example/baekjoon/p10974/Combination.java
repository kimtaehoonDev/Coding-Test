package org.example.baekjoon.p10974;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// N R 입력해라
public class Combination {

    static int N;
    static int R;
    static int[] store;
    static int[] nums;
    static List<int[]> answers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, nums 초기화
        N = sc.nextInt();
        nums = new int[N];
        for(int i = 0; i<N; i++) {
            nums[i] = i + 1;
        }

        R = sc.nextInt();
        store = new int[R];

        comb(0, 0);

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (int[] answer : answers) {
            idx++;
            for (int each : answer) {
                sb.append(each);
                sb.append(" ");
            }
            if (idx != answers.size()) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void comb(int start, int depth) {
        if (depth == store.length) {
            answers.add(Arrays.copyOfRange(store, 0, store.length));
            return;
        }
        for(int i = start; i < N; i++) {
            store[depth] = nums[i];
            comb(i + 1, depth + 1);
        }
    }
}
