package org.example.baekjoon.p2776;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner sj = new StringJoiner("\n");

        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++) {
            solve(br, sj);
        }
        System.out.println(sj);
    }

    public static void solve(BufferedReader br, StringJoiner sj) throws IOException {
        int N = Integer.parseInt(br.readLine()); // 적어둔거
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> store = new HashMap<>();

        for(int i=0; i<N;i++) {
            int x = Integer.parseInt(st.nextToken());
            store.put(x, x);
        }

        int M = Integer.parseInt(br.readLine()); // 현종이에게 질문한거
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M;i++) {
            int x = Integer.parseInt(st.nextToken());
            if (store.containsKey(x)) {
                sj.add("1");
            } else {
                sj.add("0");
            }
        }
    }
}
