package org.example.baekjoon.p17608;

import java.util.*;
import java.math.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ary = new int[N];
        for( int i = 0; i<N; i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }

        int tall = -1;
        int cnt = 0;
        for(int i=N-1; i>=0;i--) {
            if (ary[i] > tall) {
                tall = ary[i];
                cnt += 1;
            }
        }
        System.out.println(cnt);
    }
}
