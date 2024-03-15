package org.example.baekjoon.p6068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 성공 // 22분
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // ㅎㅐ야할 일 목록
        int[][] works = new int[N][2];
        for (int i = 0; i < N; i++) {
//            works[i][0], works[i][0]
            String[] input = br.readLine().split(" "); // T, S 입력
            works[i][0] = Integer.parseInt(input[0]);
            works[i][1] = Integer.parseInt(input[1]);
        }
        // S가 느린 순서대로 정렬(내림차순)
        Arrays.sort(works, (w1, w2) -> {
            return w2[1] - w1[1];
        });

        int now = works[0][1];
        System.out.println(now);
        for (int[] work : works) {
            if (now <= work[1]) {
                // 일시작 가능하면
                now -= work[0];
            } else {
                // 일 불가능하면
                now = work[1] - work[0];
            }
            System.out.println(Arrays.toString(work) + "할 때 " + now);
        }
        if (now < 0) {
            System.out.println(-1);
        } else {
            System.out.println(now);
        }

//        for (int[] work : works) {
//            System.out.println(Arrays.toString(work));
//        }
    }
}
