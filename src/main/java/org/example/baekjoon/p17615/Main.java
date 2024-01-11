package org.example.baekjoon.p17615;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 성공 / 22분 / 구현
public class Main {

    public static final char BLUE = 'B';
    public static final char RED = 'R';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] balls = br.readLine().toCharArray();
        int answer = 10_000_000; // INF
//        System.out.println(balls.length);

        // 빨간공 옮기기 && RRR...BBBB...
        boolean lastBlue = false;
        int cnt = 0;
        for(char ball : balls) {
            if (ball == BLUE) {
                lastBlue = true;
                continue;
            }
            if (lastBlue) {
                cnt++;

            }
        }
//        System.out.println("빨간공 옮기기 && RRR...BBBB: " + cnt);
        answer = Math.min(answer, cnt);

        // 빨간공 옮기기 && BBBB...RRR...
        lastBlue = false;
        cnt = 0;
        for(int i = balls.length - 1; i>=0; i--) {
            char ball = balls[i];
            if (ball == BLUE) {
                lastBlue = true;
                continue;
            }
            if (lastBlue) {
                cnt++;
            }
        }
//        System.out.println("빨간공 옮기기 && BBBB...RRR : " + cnt);
        answer = Math.min(answer, cnt);

        // 파란공 옮기기 && RRR...BBBB...
        boolean lastRed = false;
        cnt = 0;
        for(char ball : balls) {
            if (ball == RED) {
                lastRed = true;
                continue;
            }
            if (lastRed) {
                cnt++;
            }
        }
//        System.out.println("파란공 옮기기 && RRRBBB: " + cnt);
        answer = Math.min(answer, cnt);

        // 파란공 옮기기 && BBBB...RRR...
        lastRed = false;
        cnt = 0;
        for(int i = balls.length - 1; i>=0; i--) {
            char ball = balls[i];
            if (ball == RED) {
                lastRed = true;
                continue;
            }
            if (lastRed) {
                cnt++;
            }
        }
//        System.out.println("파란공 옮기기 && BBBB...RRR: " + cnt);
        answer = Math.min(answer, cnt);

        // 정답출력
        System.out.println(answer);
    }
}
