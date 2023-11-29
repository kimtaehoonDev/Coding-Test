package org.example.baekjoon.p1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 성공 / 25분
public class Main2 {
    static List<boolean[]> cases = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<String> chars = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            chars.add(st.nextToken());
        }
        Collections.sort(chars);

//        System.out.println(chars);
        // chars에서 R개를 뽑는다
        comb(N, R, 0, 0, new boolean[N]);
        List<String> answers = new ArrayList<>();
        for (boolean[] each : cases) {
//            System.out.println(Arrays.toString(each));
            // each를 사용해서 chars에서 뽑는다
            if (canMake(each, chars)) {
                answers.add(make(each, chars));
            }
        }
        StringJoiner sj = new StringJoiner("\n");
        for (String answer : answers) {
            sj.add(answer);
        }
        System.out.println(sj.toString());
    }

    public static String make(boolean[] bits, List<String> chars) {
        StringBuilder sb = new StringBuilder();
        int idx = -1;
        for (boolean bit : bits) {
            idx++;
            if (bit) {
                sb.append(chars.get(idx));
            }
        }
        return sb.toString();
    }

    public static boolean canMake(boolean[] bits, List<String> chars) {
        int moemCnt = 0;
        int jaemCnt = 0;

        int idx = -1;
        for (boolean bit : bits) {
            idx++;
            if (bit) {
                String each = chars.get(idx);
                if (List.of("a", "e", "o", "u", "i").contains(each)) {
                    moemCnt++;
                } else {
                    jaemCnt++;
                }
                if (jaemCnt >= 2 && moemCnt >= 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void comb(int n, int r, int start, int depth, boolean[] bits) {
        if (r == depth) {
            cases.add(Arrays.copyOfRange(bits, 0, bits.length));
            return;
        }

        for (int i = start; i < n; i++) {
            bits[i] = true;
            comb(n, r, i + 1, depth + 1, bits);
            bits[i] = false;
        }
    }
}
