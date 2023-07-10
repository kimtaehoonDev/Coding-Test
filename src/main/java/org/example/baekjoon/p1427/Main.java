package org.example.baekjoon.p1427;

import java.util.Scanner;

public class Main {
    // 선택 정렬, 내림차순 큰거부터
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] inputs = sc.next().toCharArray();
        int N = inputs.length;
        int[] ary = new int[N];
        for(int i=0; i<N; i++) {
            ary[i] = Character.getNumericValue(inputs[i]);
        }

        for(int target=0; target<N; target++) {
            int max = -1;
            int maxIdx = -1;
            for(int i=target;i<N;i++) {
                if (max < ary[i]) {
                    max = ary[i];
                    maxIdx = i;
                }
            }
            // target과 maxIdx를 바꾸는 것
            int temp = ary[target];
            ary[target] = ary[maxIdx];
            ary[maxIdx] = temp;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : ary) {
            sb.append(i);
        }
        System.out.println(sb);
    }
}
