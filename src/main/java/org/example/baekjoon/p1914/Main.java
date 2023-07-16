package org.example.baekjoon.p1914;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        if (N <= 20) {
            sb.append((long) Math.pow(2,N) - 1 + "\n");
            Deque<Moving> result = new ArrayDeque<>();
            hanoi(N, 1, 2, 3, result);
            for (Moving moving : result) {
                sb.append(moving.start + " " + moving.end + "\n");
            }
        } else {
            BigInteger result = new BigInteger("2");
            result = result.pow(N).subtract(new BigInteger("1"));
            sb.append(result);
        }
        System.out.println(sb);
    }

    private static void hanoi(int n, int now, int middle, int target, Deque<Moving> result) {
        if (n == 1) {
            result.add(new Moving(now, target));
            return;
        }
        hanoi(n-1, now, target, middle, result);
        result.add(new Moving(now, target));
        hanoi(n-1, middle, now, target, result);
    }

    static class Moving {
        int start;
        int end;
        public Moving(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
