package org.example.baekjoon.p11720;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        int answer = 0;
        String[] strs = sc.next().split("");
        for(String each : strs) {
            answer += Integer.parseInt(each);
        }
        System.out.println(answer);
    }
}
