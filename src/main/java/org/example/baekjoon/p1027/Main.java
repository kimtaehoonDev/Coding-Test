package org.example.baekjoon.p1027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 성공 / 완탐 / 24분
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];  // 각 원소들 0부터 10억까지 가능
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] looks = new int[N]; // 각 건물에서 몇개 보이는지 기록
        for (int i = 0; i < N; i++) {
            long maxChild = -10000000000L;
            long maxParent = 1L;
            for (int j = i + 1; j < N; j++) {
                if (maxChild * (j - i) < (heights[j] - heights[i]) * (maxParent)) {
                    maxChild = (heights[j] - heights[i]);
                    maxParent = j - i;
                    looks[i]++;
                    looks[j]++;
                }
            }
        }
        long max = -1L;
//        System.out.println(Arrays.toString(looks));
        for (int look : looks) {
            max = Math.max(max, look);
        }
        System.out.println(max);
    }
}
