package org.example.baekjoon.p1038;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Long> nums = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if (N < 10) {
            System.out.println(N); // 0123456789
            return;
        }

        nums.add(0L);
        for (long i = 1; i<10; i++) {
            dfs(i);
        }

        Collections.sort(nums);
//        System.out.println(nums);
        if (N > nums.size()) {
            System.out.println("-1");
        } else {
            System.out.println(nums.get(N));
        }
    }

    static void dfs(long x) {
        if (x % 10 == 0) {
            nums.add(x);
            // 더한다
            return;
        }
        nums.add(x);
        for(long i = 0; i<x % 10; i++) {
            dfs(x * 10 + i);
        }
    }
}
