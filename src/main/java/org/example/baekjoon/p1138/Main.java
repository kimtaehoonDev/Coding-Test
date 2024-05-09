package org.example.baekjoon.p1138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 성공 / 12분 / 구현
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] lines = new int[N];
        for(int i = 0; i<N; i++) {
            int left = Integer.parseInt(st.nextToken());
            int j = -1;
            int cnt = 0;
            while(true) {
                j++;
                if (lines[j] != 0) {
                    continue;
                }
                if (cnt == left) {
                    lines[j] = i + 1;
                    break;
                }
                cnt++;
            }
//            System.out.println(Arrays.toString(lines));
        }
        StringJoiner sj = new StringJoiner(" ");
        for(int each : lines) {
            sj.add(String.valueOf(each));
        }
        System.out.println(sj);
    }
}
