package org.example.baekjoon.p2750;

import java.util.Scanner;

public class Main {
    // bubble sort
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] ary = new int[N];
        for(int i=0; i<N; i++) {
            ary[i] = sc.nextInt();
        }

        for(int i=N-1; i>0; i--) { // N-1부터 1까지, [a,b] 비교 시 b 지정
            boolean isChanged = false;
            for (int j = 0; j < i; j++) {
                if (ary[j] > ary[j+1]) {
                    isChanged = true;
                    int temp = ary[j+1];
                    ary[j+1] = ary[j];
                    ary[j] = temp;
                }
            }
            if (!isChanged) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<N; i++) {
            sb.append(ary[i] + "\n");
        }
        System.out.println(sb);
    }
}
