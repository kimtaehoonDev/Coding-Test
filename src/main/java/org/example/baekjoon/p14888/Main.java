package org.example.baekjoon.p14888;

import java.util.Scanner;

public class Main {
//    N: 숫자의 개수

    public final static int INF = 1_500_000_000;
    public static int min = INF;
    public static int max = -INF;
    static int[] nums;
    static int[] operators;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        nums = new int[N];
        for(int i=0; i<N; i++) {
            nums[i] = sc.nextInt();
        }
        operators = new int[4];
        for(int i=0; i<4;i++) {
            operators[i] = sc.nextInt();
        }

        int sum = nums[0];
        int numIdx = 1;
        dfs(sum, numIdx);
        System.out.println(max + "\n" + min + "\n");
    }

    public static void dfs(int sum, int numIdx) {
        if (numIdx == nums.length) {
            min = Math.min(sum, min);
            max = Math.max(sum, max);
            return;
        }
        for(int i=0; i<4; i++) {
            if (operators[i] == 0) {
                continue;
            }
            operators[i] -= 1;
            int original = sum;
            if (i == 0) { //+
                sum += nums[numIdx];
            } else if (i == 1) { // -
                sum -= nums[numIdx];
            } else if (i == 2) { // *
                sum *= nums[numIdx];
            } else { // /
                if (sum < 0) {
                    sum = - Math.abs(sum / nums[numIdx]);
                } else {
                    sum /= nums[numIdx];
                }
            }

            dfs(sum, numIdx + 1);
            // 원래대로 돌려놓기
            sum = original;
            operators[i] += 1;
        }
    }
}
