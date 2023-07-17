package org.example.baekjoon.p1463;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer N = sc.nextInt();
        int[] dp = new int[N+1];
        for(int i = 0; i<N+1; i++) {
            dp[i] = -1;
        }


        Queue<Integer> queue = new LinkedList<>();
        int target = N;
        dp[N] = 0;
        queue.add(target);

        while (!queue.isEmpty()) {
            target = queue.poll();
            if (target == 1) {
                break;
            }
            if (target % 3 == 0 && dp[target / 3] == -1) {
                queue.add(target / 3);
                dp[target / 3] = dp[target] + 1;
            }
            if (target % 2 == 0 && dp[target / 2] == -1) {
                queue.add(target / 2);
                dp[target / 2] = dp[target] + 1;
            }
            if (target - 1 > 0 && dp[target - 1] == -1) {
                queue.add(target - 1);
                dp[target - 1] = dp[target] + 1;
            }
        }
        System.out.println(dp[1]);
    }
}
