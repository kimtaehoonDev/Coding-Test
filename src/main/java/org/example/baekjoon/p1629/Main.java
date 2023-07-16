package org.example.baekjoon.p1629;

import java.util.*;
import java.math.*;

public class Main {
    public static Map<Integer, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int answer = calculateMod(A, B, C);
        System.out.println(answer);
    }

    private static int calculateMod(int a, int b, int c) {
        return calculateModHelper(a, b, c);
    }

    private static int calculateModHelper(int a, int b, int c) {
        if (b == 0) {
            return 1;
        }
        if (cache.containsKey(b)) {
            return cache.get(b);
        }

        if (b == 1) {
            cache.put(b, a%c);
            return cache.get(b);
        }
        int b1 = b / 2;
        int b2 = b - b1;
        int left = calculateModHelper(a, b1, c);
        int right = calculateModHelper(a, b2, c);

        BigInteger result = new BigInteger(String.valueOf(left))
            .multiply(new BigInteger(String.valueOf(right)))
            .mod(new BigInteger(String.valueOf(c)));
        cache.put(b, result.intValue());
        return result.intValue();
    }
}
