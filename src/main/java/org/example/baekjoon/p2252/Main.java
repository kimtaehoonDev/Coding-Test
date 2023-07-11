package org.example.baekjoon.p2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] isConnected = new int[N+1];
        boolean[] isVisited = new boolean[N+1];
        List<Integer>[] ary = (List<Integer>[]) new List<?>[N+1];

        for(int i=0; i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            if (ary[smaller] == null) {
                ary[smaller] = new LinkedList<>();;
            }
            ary[smaller].add(taller);
            isConnected[taller] += 1;
        }

        List<Integer> answer = new ArrayList<>(N);
        while (answer.size() != N) {
            for(int j=1; j<N+1;j++) {
                if (isConnected[j] == 0 && !isVisited[j]) {
                    answer.add(j);
                    isVisited[j] = true;
                    List<Integer> next = ary[j];
                    if (next == null) {
                        continue;
                    }
                    for (Integer each : next) {
                        isConnected[each] -= 1;
                    }
                }
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (Integer each : answer) {
            sj.add(String.valueOf(each));
        }
        System.out.println(sj);
    }
}
