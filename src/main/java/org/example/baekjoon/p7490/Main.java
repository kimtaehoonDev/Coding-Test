package org.example.baekjoon.p7490;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

// 성공 / 40분 / 브루트포스
public class Main {

    static int PLUS = 0;
    static int MINUS = 1;
    static int EMPTY = 2;
    static List<int[]> cases;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        StringJoiner sj = new StringJoiner("\n\n");
        for(int i = 0; i<T; i++) {
            int input = Integer.parseInt(sc.nextLine());
            String result = solve(input);
            sj.add(result);
        }
        System.out.println(sj.toString());
    }

    static String solve(int size) {
        cases = new ArrayList<>();
        // case 3
        find(size-1, 0,  new int[size-1]);
//        StringJoiner sj = new StringJoiner("\n");
        List<String> results = new ArrayList<>();
        for (int[] opsCase : cases) {
            List<Integer> values = new LinkedList<>();
            int acc = 1;
            int value = 1;
            for(int each : opsCase) {
                value++;
                if (each == EMPTY) {
                    acc = acc * 10 + value;
                } else {
                    values.add(acc);
                    acc = value;
                }
            }
            values.add(acc);
//            System.out.println("values = " + values);
            List<Integer> ops = new LinkedList<>();

            for (int each : opsCase) {
                if (each == EMPTY) {
                    continue;
                }
                ops.add(each);
            }
//            System.out.println("ops = " + ops);

            int result = values.get(0);
            for(int i = 0; i<ops.size(); i++) {
                if (ops.get(i) == PLUS) {
                    result += values.get(i+1);
                } else {
                    result -= values.get(i+1);
                }
            }
            if (result == 0) {
                results.add(makeStr(size, opsCase));
            }
        }
        Collections.sort(results);
        StringJoiner sj = new StringJoiner("\n");
        for (String result : results) {
            sj.add(result);
        }
        return sj.toString();
    }

    static String makeStr(int size, int[] opsCase) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        int value = 1;
        for (int op : opsCase) {
            value++;
            if (op == PLUS) {
                sb.append('+');
                sb.append(value);
            } else if (op == MINUS) {
                sb.append('-');
                sb.append(value);
            } else {
                sb.append(' ');
                sb.append(value);
            }
        }
        return sb.toString();
    }

    static void find(int N, int depth, int[] values) {
        if (N == depth) {
            cases.add(Arrays.copyOfRange(values, 0, values.length));
            return;
        }

        for(int i = 0; i<3; i++) {
            values[depth] = i;
            find(N, depth+1, values);
        }
    }
}
