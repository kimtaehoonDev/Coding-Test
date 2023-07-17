package org.example.baekjoon.p1629;

import java.util.*;
import java.math.*;

public class Main {
    static long c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        c = sc.nextLong();

        long answer = calculateMod(A, B);
        System.out.println(answer);
    }

    private static long calculateMod(long num, long exp) {
        if (exp==1) {
            return num%c;
        }

        // 코드 변경 사유
        // 예를 들어 9인 경우 calculate(5) * calculate(4) 이거나, calculate(4) ^ 2 * calculate(1) 이나 같음
        long temp = calculateMod(num, exp / 2);
        if (exp % 2 == 1) {
            return  (((temp * temp) % c) * (num%c))%c;
        }
        return (temp * temp) % c;
    }

}
