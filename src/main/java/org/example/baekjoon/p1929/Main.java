package org.example.baekjoon.p1929;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();

        boolean[] isVisited = new boolean[N + 1];
        isVisited[1] = true;
        for (int i = 2; i < N + 1; i++) {
            if (!isVisited[i]) {
                for(int j = i; j<N+1;j=j+i) {
                    if (j==i) {
                        continue;
                    }
                    isVisited[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=M;i<N+1;i++) {
            if (!isVisited[i]) {
                sb.append(i+"\n");
            }
        }
        System.out.println(sb);
    }
}
