package org.example.baekjoon.p11659;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] nums = new int[N+1];
        int[] sumOfNums = new int[N+1];

        nums[0] = 0;
        sumOfNums[0] = 0;
        for(int i=1;i<N+1;i++) {
            nums[i] = sc.nextInt();
            sumOfNums[i] = sumOfNums[i-1] + nums[i];
        }

        for(int i=0; i<M;i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int result = sumOfNums[end] - sumOfNums[start - 1];
            System.out.println(result);
        }
    }
}
