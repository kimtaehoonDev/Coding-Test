package org.example.baekjoon.p1484;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

public class Main {
    public static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();
        for(int i=1; i<=G; i++) {
            if (G % i != 0) {
                continue;
            }
            if (i >= G / i) {
                break;
            }
            if ((i + G/i) % 2 == 0) {
                answer.add((i + (G / i)) / 2);
            }
        }

        if (answer.isEmpty()) {
            System.out.println("-1");
            return;
        }
        StringJoiner sj = new StringJoiner("\n");
        for (int i = answer.size() - 1; i >= 0; i--) {
//        for (Integer each : answer) {
            sj.add(String.valueOf(answer.get(i)));
        }
        System.out.println(sj);
    }
}
//        for(int now = 1; now<=100_000; now++) {
//            if (!squares.contains(now)) {
//                continue;
//            }
//            int temp = now + G;
//            if (!squares.contains(temp)) {
//                continue;
//            }
//
//            answer.add((int) Math.sqrt(temp));
//        }