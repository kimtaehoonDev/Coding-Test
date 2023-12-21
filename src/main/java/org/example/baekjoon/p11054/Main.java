package org.example.baekjoon.p11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 성공/dp/35분
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N];
        int[] asc = new int[N];
        int[] desc = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
            asc[i] = -1;
            desc[i] = -1;
        }
//        System.out.println(Arrays.toString(ary));
        for (int i = 0; i < N; i++) {
            int top = ary[i];
            int max = -1;
            for (int j = 0; j < i; j++) {
                if (top <= ary[j]) {
                    continue;
                }
                max = Math.max(max, asc[j]);
            }
            asc[i] = max + 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            int top = ary[i];
            int max = -1;
            for (int j = N - 1; j > i; j--) {
                if (top <= ary[j]) {
                    continue;
                }
                max = Math.max(max, desc[j]);
            }
            desc[i] = max + 1;
        }
//        System.out.println(Arrays.toString(asc));
//        System.out.println(Arrays.toString(desc));

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, asc[i] + desc[i] + 1);
//            System.out.println(i + "위치에서 " + answer);
        }
        System.out.println(answer);
    }
}
