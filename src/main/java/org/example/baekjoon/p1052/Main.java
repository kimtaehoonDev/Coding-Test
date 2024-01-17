package org.example.baekjoon.p1052;

import java.util.Scanner;

// 성공 / 16분 / 비스마스킹
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int answer = 0;
        int cnt = 0;
        while(true) {
            cnt = Integer.bitCount(N);
//            System.out.println(N + " -> " + cnt);
            if (cnt <= K) {
                break;
            }
            answer++;
            N++;
        }
        System.out.println(answer);
    }
}
