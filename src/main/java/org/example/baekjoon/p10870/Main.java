package org.example.baekjoon.p10870;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] ary = new int[N+1];
        ary[0] = 0;
        if (N != 0) {
            ary[1] = 1;
        }
        for(int i = 2; i<N+1; i++) {
            ary[i] = ary[i-1] + ary[i-2];
        }
        System.out.println(ary[N]);
    }
}
