package org.example.baekjoon.p1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ary);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean[] answer = new boolean[M];
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());
            int result = find(ary, x);
            if (result == -1) {
                answer[i] = false;
            } else {
                answer[i] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (boolean b : answer) {
            if (b) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.println(sb);
    }

    public static int find(int[] ary, int x) {
        int last = ary.length - 1;
        int middle = last / 2;
        int first = 0;

        if (ary[middle] == x) {
            return middle;
        }
        if (ary[middle] < x) {
            first = middle + 1;
            return findHelper(first, last, ary, x);
        } else {
            last = middle - 1;
            return findHelper(first, last, ary, x);
        }
    }

    public static int findHelper(int first, int last, int[] ary, int x) {
        if (first > last) {
            return -1;
        }
        int middle = (first + last) / 2;

        if (ary[middle] == x) {
            return middle;
        }
        if (ary[middle] < x) {
            first = middle + 1;
            return findHelper(first, last, ary, x);
        } else {
            last = middle - 1;
            return findHelper(first, last, ary, x);
        }
    }
}
