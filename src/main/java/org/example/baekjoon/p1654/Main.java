package org.example.baekjoon.p1654;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 재료의 개수
        int N = Integer.parseInt(st.nextToken()); // 만들어야 하는 개수
        int[] lans = new int[K];
        for(int i = 0; i < K; i++) {
            lans[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lans);

        long end = lans[K-1]+1L;
        // 아하, 나는 원래 가장 짧은 랜선보다 길게 만들 수 없다 생각했는데, 아예 가장 짧은 랜선을 안쓸수도 있구나
        long start = 1;

        while(start < end) {
            long mid = (end - start) / 2 + start; // 자르는 크기
            long total = 0; // mid로 잘랐을 때 만들어진 개수
            for(int lan: lans) {
                total += (lan / mid);
            }

            if (total < N) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(start - 1); //상한선 - 1
    }
}
