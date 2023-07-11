package org.example.baekjoon.p1717;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //집합의 끝
        int M = Integer.parseInt(st.nextToken()); //연산의 개수
        int[] ary = new int[N+1];
        for(int i=0; i<ary.length; i++) {
            ary[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int method = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (method == 1) {
                boolean result = find(a,b, ary);
                if (result) {
                    sb.append("YES" + "\n");
                } else {
                    sb.append("NO" + "\n");
                }
            } else {
                union(a,b, ary);
            }
        }
        System.out.println(sb);
    }

    public static boolean find(int a, int b, int[] ary) {
        // a의 대표 찾기
        // b의 대표 찾기
        a = findParent(a, ary);
        b = findParent(b, ary);
        if (a==b) {
            return true;
        }
        return false;
    }

    public static void union(int a, int b, int[] ary) {
        a = findParent(a, ary);
        b = findParent(b, ary);
        int min = Math.min(a,b);
        ary[a] = min;
        ary[b] = min;
    }

    public static int findParent(int a, int[] ary) {
        if (ary[a] == a) {
            return a;
        }
        int parent = ary[a];
        parent = findParent(parent, ary);
        ary[a] = parent;
        return ary[a];
    }
}
