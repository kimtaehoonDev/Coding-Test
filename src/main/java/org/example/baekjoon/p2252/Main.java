package org.example.baekjoon.p2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
        List<List<Integer>> ary = new ArrayList<>(N+1);
        for(int i=0; i<N+1; i++) {
            ary.add(new LinkedList<>());
        }

        for(int i=0; i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int smaller = Integer.parseInt(st.nextToken());
            int taller = Integer.parseInt(st.nextToken());
            ary.get(smaller).add(taller);
            isConnected[taller] += 1;
        }

        List<Integer> answer = new ArrayList<>(N);
        Queue<Integer> queue = new LinkedList<>();

        // 큐에 차수0인거 먼저 넣는다
        for(int i=1; i<N+1; i++) {
            if (isConnected[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            Integer target = queue.poll();
            answer.add(target);
            isVisited[target] = true;

            List<Integer> next = ary.get(target);
            for (Integer each : next) {
                isConnected[each] -= 1;
                if (isConnected[each] == 0) {
                    queue.add(each);
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
