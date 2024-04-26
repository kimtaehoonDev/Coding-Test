package org.example.baekjoon.p2559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1회 실패 후 성공 / 25분 / 구현
// 음수가 나올 수 있는데, 초기 최솟값을 0으로 놓고 풀어서 틀림
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] values = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(values));

        int answer = Integer.MIN_VALUE;
        int total = 0;
        for (int i = 0; i < K; i++) {
            total += values[i];
        }
        answer = Math.max(answer, total);

        for (int i = K; i < N; i++) {
            total += (values[i] - values[i - K]);
            answer = Math.max(answer, total);
        }
        System.out.println(answer);

    }

}
