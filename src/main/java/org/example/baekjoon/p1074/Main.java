package org.example.baekjoon.p1074;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();

        System.out.println(solve(0, N, r, c));
    }

    public static int solve(int prevCnt, int chasu, int row, int col) {
        int length = (int) Math.pow(2, chasu);
        if (chasu == 1) {
            return prevCnt + row * 2 + col;
        }
        int location = findLocation(length, row, col);

        if (location == 1 || location == 3) {
            col -= length/2;
        }
        if (location == 2 || location == 3) {
            row -= length/2;
        }
        prevCnt += location * (int) Math.pow(2, 2 * chasu - 2);

        return solve(prevCnt, chasu - 1, row, col);
    }

    private static int findLocation(int length, int row, int col) {

        int middle = length / 2;
        if (row < middle) {
            if (col < middle) {
                return 0;
            }
            return 1;
        } else {
            if (col < middle) {
                return 2;
            }
            return 3;
        }
    }
}
