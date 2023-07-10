package org.example.baekjoon.p1546;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        int[] scores = new int[N];
        for(int i = 0; i<N;i++) {
            scores[i] = Integer.parseInt(sc.next());
        }
        int maxScore = -1;
        int sum = 0;
        for(int score: scores) {
            maxScore = Math.max(maxScore, score);
            sum += score;
        }
        double result = (sum * 100.0) / (N *  maxScore);
        System.out.println(result);
    }
}
