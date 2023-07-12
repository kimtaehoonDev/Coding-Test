package org.example.baekjoon.p11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer>[] graph = new LinkedList[N+1];
        for (int i=0; i<N+1;i++) { //TODO 성공한 뒤 여기 빼보기 아마 null 들어있을거같은데
            graph[i] = new LinkedList<>();
        }

        for(int i=0; i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        int[] parent = new int[N+1];
        boolean[] visited = new boolean[N+1];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        while(!queue.isEmpty()) {
            Integer now = queue.poll();
            List<Integer> connects = graph[now];
            for (Integer connect : connects) {
                if (!visited[connect]) {
                    visited[connect] = true;
                    parent[connect] = now;
                    queue.add(connect);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx = -1;
        for(int each: parent) {
            idx++;
            if (idx < 2) {
                continue;
            }
            sb.append(each+"\n");
        }
        System.out.println(sb);
    }
}
