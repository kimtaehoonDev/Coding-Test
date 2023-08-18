package org.example.p13023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 성공/15분

// 정답률이 왜이렇게 낮은지 의아한 문제.
// 왜 백트래킹 문제인지도 모르겠음
public class Main {
    static int N;
    static int M;
    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N];
        // 관계를 초기화한다
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        boolean hasRelation = false;
        for(int i=0; i<N; i++) {
            hasRelation = dfs(i, 1);
            if (hasRelation) {
                break;
            }
        }
        if (hasRelation) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    static boolean dfs(int start, int cnt) {
        if (cnt == 5) {
            return true;
        }

        visited[start] = true;
        List<Integer> connects = graph.get(start);
        for(int connect: connects) {
            if (visited[connect]) {
                continue;
            }
            boolean hasRelation = dfs(connect, cnt + 1);
            if (hasRelation) {
                return true;
            }
        }
        visited[start] = false;
        return false;
    }
}
