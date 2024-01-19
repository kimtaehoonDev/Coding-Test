package org.example.baekjoon.p18870;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 15분 / 구현
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] ary = new int[N][2];
        for(int i = 0; i<N; i++) {
            ary[i][0] = Integer.parseInt(st.nextToken());
            ary[i][1] = i;
        }
        Arrays.sort(ary, (a1, a2) -> {
            return a1[0] - a2[0];
        });
//        for (int[] ints : ary) {
//            System.out.println(Arrays.toString(ints));
//        }
        int prev = -2_000_000_000;
        int count = -1;
        int[] answer = new int[N];
        for(int i = 0; i<N; i++) {
            if (prev < ary[i][0]) {
                prev = ary[i][0];
                count++;
            }
            answer[ary[i][1]] = count;
        }
//        System.out.println(Arrays.toString(answer));
        StringBuilder sb = new StringBuilder();
        for (int each : answer) {
            sb.append(each);
            sb.append(" ");
        }
        System.out.println(sb);

    }
}
