package org.example.baekjoon.p1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 끝 값 처리를 제대로 하지 못해서 2번 실패함
public class Main {
    public static final int INF = 2_000_000_000;

    // N : ary의 크기
    // S : 기준값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] ary = new int[N];
        for (int i = 0; i < N; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(ary));

        int total = 0;
        int left = 0;
        int right = -1;
        int answer = INF;
        while (true) {
//            System.out.println(total + ", -> left,right is " + left + "/" + right);
            if (total < S) {
                if (right + 1 >= N) {
                    break;
                }
                total += ary[++right];
                if (total >= S) {
                    answer = Math.min(right - left + 1, answer);
                }
            } else {
                if (total - ary[left] < S) {
                    if (right + 1 >= N) {
                        break;
                    }
                    total += ary[++right];
                    answer = Math.min(right - left + 1, answer);
                    continue;
                }
                total -= ary[left++];
                answer = Math.min(right - left + 1, answer);
            }
        }
        if (answer == INF) {
            answer = 0;
        }
        System.out.println(answer);
    }
}
