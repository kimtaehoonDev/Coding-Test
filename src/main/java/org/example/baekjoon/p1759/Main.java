package org.example.baekjoon.p1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    static char[] chars;
    static List<String> answers = new ArrayList<>();
    static Set<Character> moem;

    public static void main(String[] args) throws IOException {
        moem = new HashSet<>();
        moem.add('a');
        moem.add('e');
        moem.add('i');
        moem.add('o');
        moem.add('u');

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        chars = new char[C];

        // chars에 가능한 문자들을 저장한다
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++) {
            String temp = st.nextToken();
            chars[i] = temp.charAt(0);
        }
        Arrays.sort(chars);
//        System.out.println(chars);


        Collections.sort(answers);
        comb(C, L, 0, 0, new char[L]);
        StringJoiner sj = new StringJoiner("\n");
        for (String answer : answers) {
            sj.add(answer);
        }
        System.out.println(sj);
    }

    static void comb(int N, int R, int start, int depth, char[] output) {
        if (depth == R) {
            int moemCnt = 0;
            int jaemCnt = 0;
            for(char each : output) {
                if (moem.contains(each)) {
                    moemCnt ++;
                } else {
                    jaemCnt ++;
                }
            }
            if (moemCnt < 1 || jaemCnt < 2) {
                return;
            }
            answers.add(new String(output));
            return;
        }
        for(int i=start; i<N; i++) {
            output[depth] = chars[i];
            comb(N, R, i + 1, depth + 1, output);
        }
    }
}
